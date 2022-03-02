package top.tanmw.generator.db;

/**
 * @author TMW
 * @since 2022/3/2 10:18
 */
public class OracleQuery extends DbQueryAbst {

    public OracleQuery(String dbName) {
        this.dbName = dbName;
    }

    @Override
    public String getShowTablesSql() {
        return null;
    }

    @Override
    public String getShowTablesCommentSql() {
        return null;
    }
}
