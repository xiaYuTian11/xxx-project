package com.zenith.xxx.config;

import cn.hutool.core.thread.ThreadUtil;
import com.efficient.cache.api.CacheUtil;
import com.efficient.logs.model.entity.SysLog;
import com.efficient.task.api.SysTaskService;
import com.efficient.task.properties.TaskProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author TMW
 * @since 2022/3/2 17:38
 */
@Component
public class StartedEvent implements ApplicationRunner {

    @Autowired
    private TaskProperties taskProperties;
    @Autowired
    private SysTaskService sysTaskService;
    @Autowired
    private CacheUtil cacheUtil;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(taskProperties);
        System.out.println(sysTaskService.findAll());
        String cacheName = "system";
        String key = "top";
        SysLog sysLog = new SysLog();
        sysLog.setUserId("admin");
        sysLog.setLogTime(new Date());
        cacheUtil.put(cacheName, key, sysLog);
        SysLog data = cacheUtil.get(cacheName, key);
        System.out.println(data);
        cacheUtil.refresh(cacheName, key, 10);

        ThreadUtil.safeSleep(1000 * 11);
        // System.out.println(cacheUtil.get(cacheName, key).toString());
        cacheUtil.put(cacheName, key, sysLog);
        System.out.println(cacheUtil.get(cacheName, key).toString());
        cacheUtil.removeCache(cacheName, key);
        // System.out.println(cacheUtil.get(cacheName, key).toString());
        cacheUtil.removeCache(cacheName);
        // System.out.println(cacheUtil.get(cacheName, key).toString());
        // System.out.println(1/0);
    }
}
