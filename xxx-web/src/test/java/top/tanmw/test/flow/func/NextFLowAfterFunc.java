package top.tanmw.test.flow.func;

/**
 * 流程流转后函数接口
 *
 * @author TMW
 * @since 2023/5/31 11:23
 */
@FunctionalInterface
public interface NextFLowAfterFunc<T> {

    void accept(T t);

}
