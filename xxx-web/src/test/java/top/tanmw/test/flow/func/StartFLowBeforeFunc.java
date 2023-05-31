package top.tanmw.test.flow.func;

/**
 * 开始流程前函数接口
 *
 * @author TMW
 * @since 2023/5/31 11:23
 */
@FunctionalInterface
public interface StartFLowBeforeFunc<T> {

    void accept(T t);

}
