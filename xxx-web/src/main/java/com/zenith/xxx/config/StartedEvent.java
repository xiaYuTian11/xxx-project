package com.zenith.xxx.config;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.efficient.task.api.SysTaskService;
import com.efficient.task.model.entity.SysTask;
import com.efficient.task.properties.TaskProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(taskProperties);
        System.out.println(sysTaskService.findAll());
    }
}
