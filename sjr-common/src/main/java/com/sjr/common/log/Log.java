package com.sjr.common.log;

import java.lang.annotation.*;

/**
 * 日志注解
 *
 * @author TMW
 * @since 2022/3/2 17:43
 */
@Target(ElementType.METHOD) //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented
public @interface Log {

    /**
     * 操作类型
     */
    OptTypeEnum optType() default OptTypeEnum.OTHER;

    /**
     * 操作说明
     */
    String desc() default "";

    /**
     * 是否详情页
     */
    boolean isInfo() default false;

}
