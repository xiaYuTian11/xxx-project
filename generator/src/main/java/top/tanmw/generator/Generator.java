package top.tanmw.generator;

import cn.hutool.core.util.StrUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import static top.tanmw.generator.ProjectPattern.MULTI;

/**
 * @author TMW
 * @since 2022/2/28 15:15
 */
public class Generator {

    public static void main(String[] args) throws Exception {
        final String url = Generator.class.getClassLoader().getResource("generator.txt").getPath().toString();
        run(url);
    }

    public static void run(String url) throws Exception {
        final Properties properties = getProperties(url);
        GeneratorModel model = new GeneratorModel();
        model.setUrl(properties.getProperty("url"));
        model.setDriver(properties.getProperty("driver"));
        model.setUser(properties.getProperty("user"));
        model.setPassword(properties.getProperty("password"));
        model.setDbName(properties.getProperty("dbName"));
        model.setShowTablesSql(properties.getProperty("showTablesSql"));
        model.setShowTablesCommentSql(properties.getProperty("showTablesCommentSql"));
        model.setBasePath(properties.getProperty("basePath"));
        if (StrUtil.isBlank(model.getBasePath())) {
            String sp1 = System.getProperty("user.dir");
            model.setBasePath(sp1);
        }
        model.setProjectName(properties.getProperty("projectName"));
        if (StrUtil.isBlank(model.getProjectName())) {
            model.setProjectName("zenith");
        }
        model.setPattern(properties.getProperty("pattern"));
        if (StrUtil.isBlank(model.getPattern())) {
            model.setPattern(MULTI.getDesc());
        }
        model.setIncludeSet(properties.getProperty("includeSet"));
        model.setExcludeSet(properties.getProperty("excludeSet"));
        CodeGenerateUtils codeGenerateUtils = new CodeGenerateUtils();
        codeGenerateUtils.init(model);
        codeGenerateUtils.generate();
    }

    public static Properties getProperties(String url) throws Exception {
        Properties properties = new Properties();
        File file = new File(url);
        InputStream in = new FileInputStream(file);
        properties.load(in);
        return properties;
    }

}
