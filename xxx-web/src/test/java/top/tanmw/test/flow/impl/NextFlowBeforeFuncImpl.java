package top.tanmw.test.flow.impl;

import top.tanmw.test.flow.func.NextFLowBeforeFunc;

/**
 * @author TMW
 * @since 2023/5/31 14:37
 */
public class NextFlowBeforeFuncImpl<T> implements NextFLowBeforeFunc<T> {
    @Override
    public void accept(Object o) {
        System.out.println("流程执行之前方法NextFlowBeforeFuncImpl");
        System.out.println(o);
    }
}
