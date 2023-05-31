package top.tanmw.test.flow.func;


/**
 * 流程结束前函数接口
 *
 * @author TMW
 * @since 2023/5/31 11:32
 */
@FunctionalInterface
public interface EndFlowBeforeFunc<T> {
    void accept(T t);
}
