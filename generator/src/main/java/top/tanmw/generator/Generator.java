package top.tanmw.generator;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author TMW
 * @since 2022/2/28 15:15
 */
public class Generator {

    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();
        InputStream in = Generator.class.getClassLoader().getResourceAsStream("generator.txt");
        properties.load(in);

        GeneratorModel model = new GeneratorModel();
        model.setUrl(properties.getProperty("url"));
        model.setDriver(properties.getProperty("driver"));
        model.setUser(properties.getProperty("user"));
        model.setPassword(properties.getProperty("password"));
        model.setDbName(properties.getProperty("dbName"));
        model.setShowTablesSql(properties.getProperty("showTablesSql"));
        model.setShowTablesCommentSql(properties.getProperty("showTablesCommentSql"));
        model.setBasePath(properties.getProperty("basePath"));
        model.setProjectName(properties.getProperty("projectName"));
        model.setPattern(properties.getProperty("pattern"));
        model.setIncludeSet(properties.getProperty("includeSet"));
        model.setExcludeSet(properties.getProperty("excludeSet"));
        CodeGenerateUtils codeGenerateUtils = new CodeGenerateUtils();
        codeGenerateUtils.init(model);
        codeGenerateUtils.generate();
    }
}
