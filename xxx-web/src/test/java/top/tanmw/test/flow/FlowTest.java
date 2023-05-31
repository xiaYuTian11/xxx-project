package top.tanmw.test.flow;

import cn.hutool.core.util.ReflectUtil;
import com.efficient.logs.model.entity.SysLog;
import org.junit.jupiter.api.Test;

import java.util.Objects;

/**
 * @author TMW
 * @since 2023/5/31 14:37
 */
public class FlowTest {


    @Test
    public void test01() {
        String className = "top.tanmw.test.flow.impl.NextFlowBefore2FuncImpl";
//        String className = "top.tanmw.test.flow.impl.NextFlowBeforeFuncImpl";
//        String className = "top.tanmw.test.flow.impl.RevokeFlowBeforeFuncImpl";
        Object newInstance = ReflectUtil.newInstance(className);
        if (Objects.isNull(newInstance)) {
            return;
        }
        SysLog sysLog = new SysLog();
        sysLog.setId("123");
        sysLog.setModule("123");
        sysLog.setUserId("ada");


        ReflectUtil.invoke(newInstance, "accept", sysLog);

    }


    @Test
    public void test02() {
//        String className = "top.tanmw.test.flow.impl.NextFlowBefore2FuncImpl";
        String className = "top.tanmw.test.flow.impl.NextFlowBeforeFuncImpl";
//        String className = "top.tanmw.test.flow.impl.RevokeFlowBeforeFuncImpl";
        Object newInstance = ReflectUtil.newInstance(className);
        if (Objects.isNull(newInstance)) {
            return;
        }
        SysLog sysLog = new SysLog();
        sysLog.setId("tanmw");
        sysLog.setModule("2");
        sysLog.setUserId("ad333a");


        ReflectUtil.invoke(newInstance, "accept", sysLog);
    }

    @Test
    public void test03() {
//        String className = "top.tanmw.test.flow.impl.NextFlowBefore2FuncImpl";
//        String className = "top.tanmw.test.flow.impl.NextFlowBeforeFuncImpl";
        String className = "top.tanmw.test.flow.impl.RevokeFlowBeforeFuncImpl";
        Object newInstance = ReflectUtil.newInstance(className);
        if (Objects.isNull(newInstance)) {
            return;
        }
        SysLog sysLog = new SysLog();
        sysLog.setId("123");
        sysLog.setModule("123");
        sysLog.setUserId("ada");


        ReflectUtil.invoke(newInstance, "accept", "tanmw");
    }
}
