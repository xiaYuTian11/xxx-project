package com.zenith.xxx.interceptor;

import com.sjr.common.entity.Result;
import com.sjr.common.entity.ResultEnum;
import com.sjr.common.util.JackSonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 权限拦截器
 *
 * @author TMW
 * @date 2021/3/4 21:19
 */
@Component
@Slf4j
public class PermissionInterceptor implements HandlerInterceptor {

    private final String login = "/login";

    /***
     * 当前线程所保留的用户信息
     * */
    public static final InheritableThreadLocal<Object> USER_CONTEXT = new InheritableThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    /**
     * 返回前端信息
     *
     * @param response
     * @param resultEnum
     * @throws IOException
     */
    private void returnJson(HttpServletResponse response, ResultEnum resultEnum) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.append(JackSonUtil.toJson(Result.build(resultEnum)));
        out.close();
    }

}
