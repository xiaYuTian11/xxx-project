package com.zenith.xxx.controller;

import com.efficient.auth.api.LoginService;
import com.efficient.auth.model.dto.LoginInfo;
import com.efficient.common.result.Result;
import com.efficient.common.util.JackSonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TMW
 * @since 2023/3/21 11:27
 */
// @RestController
// @RequestMapping("/")
public class LoginController {
    @Autowired
    private LoginService loginService;

    public Result login() {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setAccount("tmw");
        loginInfo.setPassword("123456");
        Result login = loginService.login(loginInfo);
        System.out.println(JackSonUtil.toJson(login));
        return login;
    }
}
