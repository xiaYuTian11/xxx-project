package top.tanmw.generator.db;

/**
 * @author TMW
 * @since 2022/3/2 10:20
 */
public class DmQuery extends DbQueryAbst {

    public DmQuery(String dbName) {
        this.dbName = dbName;
    }

    @Override
    public String getShowTablesSql() {
        return "select table_name  from dba_tables  where owner='" + dbName + "';";
    }

    @Override
    public String getShowTablesCommentSql() {
        return "select tvname as tableName,comment$ as tableComment from SYSTABLECOMMENTS where SCHNAME='" + dbName + "' ;";
    }
}
