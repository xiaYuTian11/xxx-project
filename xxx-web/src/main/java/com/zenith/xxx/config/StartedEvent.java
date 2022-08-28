package com.zenith.xxx.config;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.efficient.file.api.FileService;
import com.efficient.file.dao.SysFileInfoMapper;
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
    // @Autowired
    // private FileProperties fileProperties;
    // @Autowired
    // private FileService fileService;
    // @Autowired
    // private SysFileInfoMapper sysFileInfoMapper;
    @Autowired
    private FileService fileService;
    @Autowired
    private SysFileInfoMapper sysFileInfoMapper;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // System.out.println(fileProperties);
        // System.out.println(fileService);
        // System.out.println(sysFileInfoMapper);
        // final List<SysFileInfo> sysFileInfos = sysFileInfoMapper.selectList(new QueryWrapper<>());
        // System.out.println(sysFileInfos);
        // System.out.println(fileService.list());
        System.out.println(sysFileInfoMapper.selectList(new QueryWrapper<>()));
        System.out.println(fileService.list());
    }
}
