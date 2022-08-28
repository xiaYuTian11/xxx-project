package com.zenith.xxx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动类
 *
 * @author TMW
 * @since 2022/1/26 16:36
 */
@SpringBootApplication
@EnableCaching
@ComponentScan(basePackages = {"com.efficient", "com.zenith"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
