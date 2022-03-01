package top.tanmw.generator;

/**
 * 代码生成器模型
 *
 * @author TMW
 * @since 2022/3/1 16:07
 */
public class GeneratorModel {
    /**
     * 数据库 url
     */
    private String url;
    /**
     * 驱动
     */
    private String driver;
    /**
     * 用户
     */
    private String user;
    /**
     * 密码
     */
    private String password;
    /**
     * 数据库名称
     */
    private String dbName;
    /**
     * 查询表sql
     */
    private String showTablesSql;
    /**
     * 表注释sql
     */
    private String showTablesCommentSql;
    /**
     * 基础路径
     */
    private String basePath;
    /**
     * 工程名称
     */
    private String projectName;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getShowTablesSql() {
        return showTablesSql;
    }

    public void setShowTablesSql(String showTablesSql) {
        this.showTablesSql = showTablesSql;
    }

    public String getShowTablesCommentSql() {
        return showTablesCommentSql;
    }

    public void setShowTablesCommentSql(String showTablesCommentSql) {
        this.showTablesCommentSql = showTablesCommentSql;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
