package top.tanmw.test.flow.func;

/**
 * 流程结束后函数接口
 *
 * @author TMW
 * @since 2023/5/31 11:32
 */
@FunctionalInterface
public interface EndFlowAfterFunc<T> {
    void accept(T t);
}
