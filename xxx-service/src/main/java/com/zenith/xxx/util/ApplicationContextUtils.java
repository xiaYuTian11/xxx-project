package com.zenith.xxx.util;

import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 缓存配置
 *
 * @author TMW
 * @date 2021/3/4 21:19
 */
@Component
@EnableAutoConfiguration
public class ApplicationContextUtils implements ApplicationContextAware {
    /**
     * 可写成单利模式，这里为了方便
     */
    public static ApplicationContext APPLICATION_CONTEXT = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (APPLICATION_CONTEXT == null) {
            APPLICATION_CONTEXT = applicationContext;
        }
    }
}
