package com.zenith.xxx.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TMW
 * @since 2023/5/9 9:55
 */
@RestController
@RequestMapping("/")
public class IndexController {
    @GetMapping("/index")
    public String index() {
        return "测试成功！";
    }
}
