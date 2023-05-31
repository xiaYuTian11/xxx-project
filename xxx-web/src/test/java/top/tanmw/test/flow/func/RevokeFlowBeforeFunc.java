package top.tanmw.test.flow.func;

/**
 * 流程撤销前后函数接口
 *
 * @author TMW
 * @since 2023/5/31 11:32
 */
@FunctionalInterface
public interface RevokeFlowBeforeFunc<T> {
    void accept(T t);
}
