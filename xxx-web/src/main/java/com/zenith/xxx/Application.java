package com.zenith.xxx;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * 启动类
 *
 * @author TMW
 * @since 2022/1/26 16:36
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.efficient", "com.zenith"})
@Slf4j
@MapperScan(basePackages = {"com.zenith.xxx.dao"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("系统启动成功！");
    }
}
