package top.tanmw.generator.db;

/**
 * @author TMW
 * @since 2022/3/2 9:55
 */
public interface DbQuery {

    String getShowTablesSql();

    String getShowTablesCommentSql();
}
