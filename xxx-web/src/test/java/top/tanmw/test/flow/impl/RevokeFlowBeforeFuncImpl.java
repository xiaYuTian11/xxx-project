package top.tanmw.test.flow.impl;

import top.tanmw.test.flow.func.NextFLowBeforeFunc;
import top.tanmw.test.flow.func.RevokeFlowBeforeFunc;

/**
 * @author TMW
 * @since 2023/5/31 14:37
 */
public class RevokeFlowBeforeFuncImpl implements RevokeFlowBeforeFunc {
    @Override
    public void accept(Object o) {
        System.out.println("撤销流程执行之前方法RevokeFlowBeforeFuncImpl");
        System.out.println(o);
    }
}
