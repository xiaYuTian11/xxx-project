package com.zenith.xxx.config;

import com.efficient.file.properties.FileProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author TMW
 * @since 2022/3/2 17:38
 */
@Component
public class StartedEvent implements ApplicationRunner {
    @Autowired
    private FileProperties fileProperties;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(fileProperties);
    }
}
