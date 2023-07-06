package com.zenith.xxx.controller;

import com.efficient.common.result.Result;
import com.efficient.common.util.IdUtil;
import com.zenith.xxx.model.dto.UserTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @GetMapping("/sensitive")
    @ResponseBody
    public Result sensitive() {
        UserTest userTest = new UserTest();
        userTest.setId(IdUtil.uuid());
        userTest.setName("八十六");
        // userTest.setIdCard("500231111111114321");
        // userTest.setFixedPhone("023-33334444");
        userTest.setMobilePhone("13992212");
        // userTest.setAddress("重庆市九龙坡区石桥铺渝高大厦");
        // userTest.setEmail("2998662089@qq.com");
        userTest.setPassword("19@123");
        // userTest.setCarLicense("渝A98765");
        // userTest.setBankCard("60023111111234899");

        return Result.ok(userTest);
    }
}
