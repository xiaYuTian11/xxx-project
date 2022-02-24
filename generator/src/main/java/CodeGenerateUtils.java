import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import freemarker.template.Template;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.Date;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 代码生成器启动类，Generate.java暂时未作使用
 *
 * @author TMW
 * @date 2021/2/25 17:45
 */
public class CodeGenerateUtils {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/front_0910?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";
    private final String showTablesSql = "SHOW TABLES;";

    private final String basePath = "D:\\java\\ideaWorkingSpace\\work\\zenith\\zenith-front";
    private final String basePackageName = "com.zenith.front";
    private final String AUTHOR = "code generator";
    private final String baseMapperXmlPath = "zenith-front-service/src/main/resources/";
    private final String mapperXmlPath = "mapper/revision";
    private final String baseDaoPath = "zenith-front-service/src/main/java/com/zenith/front/";
    private final String daoPath = "dao/revision";
    private final String baseDtoPath = "zenith-front-common/src/main/java/com/zenith/front/";
    private final String dtoPath = "domain/revision/dto";
    private final String baseVoPath = "zenith-front-common/src/main/java/com/zenith/front/";
    private final String voPath = "domain/revision/vo";
    private final String baseEntityPath = "zenith-front-common/src/main/java/com/zenith/front/";
    private final String entityPath = "domain/revision/entity";
    private final String baseServicePath = "zenith-front-api/src/main/java/com/zenith/front/";
    private final String servicePath = "api/revision";
    private final String baseServiceImplPath = "zenith-front-service/src/main/java/com/zenith/front/";
    private final String serviceImplPath = "service/revision";
    private final String baseControllerPath = "zenith-front-web/src/main/java/com/zenith/front/";
    private final String controllerPath = "controller/common/revision";
    private final String baseConverterPath = "zenith-front-service/src/main/java/com/zenith/front/";
    private final String converterPath = "converter/revision";
    // private final String modelPath = "domain/entity";
    // 优先
    private final Set<String> includeSet = new HashSet<String>() {{
        add("b01");
        add("a01");
        add("a02");
    }};
    private final Set<String> excludeSet = new HashSet<String>() {{
        // add("String");
    }};

    private final List<ColumnClass> columnClassList = new ArrayList<>();
    /**
     * 表名
     */
    private String tableName;
    /**
     * 主键名称
     */
    private String primaryKeyColumnName;
    /**
     * 主键字段
     */
    private String primaryKeyFieldName;
    /**
     * 表对应的类名，表名转驼峰首字母大写
     */
    private String changeTableName;

    public static void main(String[] args) throws Exception {
        CodeGenerateUtils codeGenerateUtils = new CodeGenerateUtils();
        codeGenerateUtils.generate();
    }

    public Connection getConnection() throws Exception {
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void generate() throws Exception {
        Connection connection = null;
        try {
            connection = getConnection();
            Set<String> tables = findTables(connection);
            final Set<String> lowerCaseSet = includeSet.stream().map(String::toLowerCase).collect(Collectors.toSet());
            if (lowerCaseSet.size() > 0) {
                tables = tables.stream().filter(lowerCaseSet::contains).collect(Collectors.toSet());
            }
            if (excludeSet.size() > 0) {
                tables = tables.stream().filter(tableNameStr -> !excludeSet.contains(tableNameStr)).collect(Collectors.toSet());
            }
            if (tables.size() < 1) {
                throw new RuntimeException("未发现可生成表");
            }
            for (String tableNameStr : tables) {
                tableName = tableNameStr;
                changeTableName = replaceUnderLineAndUpperCase(tableName) + "Revision";
                columnClassList.clear();
                DatabaseMetaData databaseMetaData = connection.getMetaData();
                ResultSet resultSet = databaseMetaData.getColumns(null, "%", tableName, "%");
                final ResultSet primaryKeys = databaseMetaData.getPrimaryKeys(null, null, tableName);
                while (primaryKeys.next()) {
                    primaryKeyColumnName = primaryKeys.getString("COLUMN_NAME");
                    primaryKeyFieldName = replaceUnderLineAndUpperCase(primaryKeyColumnName);
                }
                //生成Mapper文件
                generateMapperFile(resultSet);
                //生成Dao文件
                generateDaoFile(resultSet);
                //生成Repository文件
                // generateRepositoryFile(resultSet);
                //生成服务层接口文件
                generateServiceInterfaceFile(resultSet);
                //生成服务实现层文件
                generateServiceImplFile(resultSet);
                //生成Controller层文件
                generateControllerFile(resultSet);
                //生成DTO文件
                generateDTOFile(resultSet);
                //生成ListDTO文件
                generateListDTOFile(resultSet);
                //生成VO文件
                generateVOFile(resultSet);
                //生成Model文件
                generateModelFile(resultSet);
                // 生成Converter文件
                generateConverterFile(resultSet);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public Set<String> findTables(Connection connection) throws SQLException {
        Set<String> set = new HashSet<>();
        PreparedStatement ps = connection.prepareStatement(showTablesSql);
        final ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            final String tableName = resultSet.getString(1);
            set.add(tableName);
        }
        if (set.size() <= 0) {
            throw new RuntimeException("未在指定数据库中发现可用表！");
        }
        return set;
    }

    private String getCreatePath(String baseFilePath, String filePath, String suffix) {
        return basePath + File.separator + baseFilePath + File.separator + filePath + File.separator + changeTableName + suffix;
    }

    private String getSuffixPackageName(String packagePath) {
        if (packagePath == null) {
            return basePackageName;
        }
        StringBuilder sb = new StringBuilder();
        if (packagePath.contains("/")) {
            final String[] split = packagePath.split("/");
            for (String str : split) {
                sb.append(str).append(".");
            }
            return basePackageName + "." + sb.substring(0, sb.length() - 1);
        }
        return basePackageName + "." + packagePath;
    }

    private void generateModelFile(ResultSet resultSet) throws Exception {
        final String suffix = ".java";
        String path = getCreatePath(baseEntityPath, entityPath, suffix);
        final String templateName = "Model.ftl";
        File mapperFile = new File(path);
        this.checkFilePath(mapperFile);
        generateModelAndDTOAndVoFile(resultSet, templateName, mapperFile, entityPath);
        System.out.println("<<<<<<<<<<<< 生成 " + changeTableName + ".java 完成 >>>>>>>>>>>");
    }

    private void generateModelAndDTOAndVoFile(ResultSet resultSet, String templateName, File createFile, String packageName) throws Exception {
        if (columnClassList.size() < 1) {
            ColumnClass columnClass = null;
            while (resultSet.next()) {
                //id字段略过
                // if (StrUtil.equalsAny(templateName, "Model.ftl")) {
                //     if (StrUtil.equalsAny(resultSet.getString("COLUMN_NAME"), "id", "is_delete")) {
                //         continue;
                //     }
                // }
                columnClass = new ColumnClass();
                //获取字段名称
                final String columnName = resultSet.getString("COLUMN_NAME");
                columnClass.setColumnName(columnName);
                //获取字段类型
                columnClass.setColumnType(resultSet.getString("TYPE_NAME").toLowerCase());
                //转换字段名称，如 sys_name 变成 SysName
                columnClass.setChangeColumnName(replaceUnderLineAndUpperCase(columnName));
                //字段在数据库的注释
                columnClass.setColumnComment(resultSet.getString("REMARKS"));
                columnClass.setPrimaryKey(StrUtil.equals(columnName, primaryKeyColumnName));
                columnClassList.add(columnClass);
            }
        }
        List<ColumnClass> relColumnClassList;
        if (StrUtil.equalsAny(templateName, "Model.ftl")) {
            relColumnClassList = columnClassList.stream().filter(entity -> !StrUtil.equalsAny(entity.getColumnName(), "is_delete"))
                    .collect(Collectors.toList());
        } else {
            relColumnClassList = new ArrayList<>(columnClassList);
        }
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("model_column", relColumnClassList);
        generateFileByTemplate(templateName, packageName, createFile, dataMap);
    }

    private void generateListDTOFile(ResultSet resultSet) throws Exception {
        final String suffix = "ListDTO.java";
        String path = getCreatePath(baseDtoPath, dtoPath, suffix);
        final String templateName = "ListDTO.ftl";
        File mapperFile = new File(path);
        this.checkFilePath(mapperFile);
        generateModelAndDTOAndVoFile(resultSet, templateName, mapperFile, dtoPath);
        System.out.println("<<<<<<<<<<<< 生成 " + changeTableName + "ListDTO.java 完成 >>>>>>>>>>>");
    }

    private void generateVOFile(ResultSet resultSet) throws Exception {
        final String suffix = "VO.java";
        String path = getCreatePath(baseVoPath, voPath, suffix);
        final String templateName = "VO.ftl";
        File mapperFile = new File(path);
        this.checkFilePath(mapperFile);
        generateModelAndDTOAndVoFile(resultSet, templateName, mapperFile, voPath);
        System.out.println("<<<<<<<<<<<< 生成 " + changeTableName + "VO.java 完成 >>>>>>>>>>>");
    }

    private void generateDTOFile(ResultSet resultSet) throws Exception {
        final String suffix = "DTO.java";
        String path = getCreatePath(baseDtoPath, dtoPath, suffix);
        final String templateName = "DTO.ftl";
        File mapperFile = new File(path);
        this.checkFilePath(mapperFile);
        generateModelAndDTOAndVoFile(resultSet, templateName, mapperFile, dtoPath);
        System.out.println("<<<<<<<<<<<< 生成 " + changeTableName + "DTO.java 完成 >>>>>>>>>>>");
    }

    private void generateConverterFile(ResultSet resultSet) throws Exception {
        final String suffix = "Converter.java";
        String path = getCreatePath(baseConverterPath, converterPath, suffix);
        final String templateName = "Converter.ftl";
        File mapperFile = new File(path);
        checkFilePath(mapperFile);
        Map<String, Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName, converterPath, mapperFile, dataMap);
        System.out.println("<<<<<<<<<<<< 生成 " + changeTableName + "Converter.java 完成 >>>>>>>>>>>");
    }

    private void generateControllerFile(ResultSet resultSet) throws Exception {
        final String suffix = "Controller.java";
        String path = getCreatePath(baseControllerPath, controllerPath, suffix);
        final String templateName = "Controller.ftl";
        File mapperFile = new File(path);
        checkFilePath(mapperFile);
        Map<String, Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName, controllerPath, mapperFile, dataMap);
        System.out.println("<<<<<<<<<<<< 生成 " + changeTableName + "Controller.java 完成 >>>>>>>>>>>");
    }

    private void generateServiceImplFile(ResultSet resultSet) throws Exception {
        final String suffix = "ServiceImpl.java";
        String path = getCreatePath(baseServiceImplPath, serviceImplPath, suffix);
        final String templateName = "ServiceImpl.ftl";
        File mapperFile = new File(path);
        checkFilePath(mapperFile);
        Map<String, Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName, serviceImplPath, mapperFile, dataMap);
        System.out.println("<<<<<<<<<<<< 生成 " + changeTableName + "ServiceImpl.java 完成 >>>>>>>>>>>");
    }

    private void generateServiceInterfaceFile(ResultSet resultSet) throws Exception {
        final String suffix = "Service.java";
        String path = getCreatePath(baseServicePath, servicePath, suffix);
        final String templateName = "Service.ftl";
        File mapperFile = new File(path);
        checkFilePath(mapperFile);
        Map<String, Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName, servicePath, mapperFile, dataMap);
    }

    private void generateRepositoryFile(ResultSet resultSet) throws Exception {
        final String suffix = "Repository.java";
        final String path = basePath + changeTableName + suffix;
        final String templateName = "Repository.ftl";
        File mapperFile = new File(path);
        Map<String, Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName, mapperFile, dataMap);
    }

    /**
     * Mapper.java
     */
    private void generateDaoFile(ResultSet resultSet) throws Exception {
        final String suffix = "Mapper.java";
        String path = getCreatePath(baseDaoPath, daoPath, suffix);
        final String templateName = "Mapper.ftl";
        File mapperFile = new File(path);
        checkFilePath(mapperFile);
        Map<String, Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName, daoPath, mapperFile, dataMap);
        System.out.println("<<<<<<<<<<<< 生成 " + changeTableName + "Mapper.java 完成 >>>>>>>>>>>");
    }

    /**
     * Mapper.xml
     */
    private void generateMapperFile(ResultSet resultSet) throws Exception {
        final String suffix = "Mapper.xml";
        String path = getCreatePath(baseMapperXmlPath, mapperXmlPath, suffix);
        final String templateName = "Mapper.xml.ftl";
        File mapperFile = new File(path);
        checkFilePath(mapperFile);
        Map<String, Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName, mapperFile, dataMap);
        System.out.println("<<<<<<<<<<<< 生成 " + changeTableName + "Mapper.xml 完成 >>>>>>>>>>>");
    }

    private void generateFileByTemplate(final String templateName, File file, Map<String, Object> dataMap) throws Exception {
        this.generateFileByTemplate(templateName, null, file, dataMap);
    }

    private void generateFileByTemplate(final String templateName, String packagePath, File file, Map<String, Object> dataMap) throws Exception {
        Template template = FreeMarkerTemplateUtils.getTemplate(templateName);
        FileOutputStream fos = new FileOutputStream(file);
        dataMap.put("serialVersionUID", getSerialVersionUID());
        dataMap.put("table_name_small", tableName);
        dataMap.put("table_name", changeTableName);
        dataMap.put("lower_table_name", StrUtil.lowerFirst(changeTableName));
        dataMap.put("author", AUTHOR);
        dataMap.put("date", DateUtil.formatDateTime(new Date()));
        dataMap.put("primary_key_field", primaryKeyFieldName);
        dataMap.put("dto_package_name", getSuffixPackageName(dtoPath));
        dataMap.put("vo_package_name", getSuffixPackageName(voPath));
        dataMap.put("entity_package_name", getSuffixPackageName(entityPath));
        dataMap.put("package_name", getSuffixPackageName(packagePath));
        dataMap.put("api_package_name", getSuffixPackageName(servicePath));
        dataMap.put("service_package_name", getSuffixPackageName(serviceImplPath));
        dataMap.put("converter_package_name", getSuffixPackageName(converterPath));
        dataMap.put("dao_package_name", getSuffixPackageName(daoPath));
        // dataMap.put("table_annotation", tableAnnotation);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, StandardCharsets.UTF_8), 10240);
        template.process(dataMap, out);
    }

    private void checkFilePath(File file) {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
    }

    /**
     * 生成serialVersionUID
     */
    protected String getSerialVersionUID() {
        return String.valueOf(Math.abs(UUID.randomUUID().getMostSignificantBits())) + "L";
    }

    public String replaceUnderLineAndUpperCase(String str) {
        return StrUtil.upperFirst(StrUtil.toCamelCase(str));
    }
}
