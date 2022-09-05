package com.zenith.xxx.util;

import cn.hutool.core.util.StrUtil;
import com.efficient.common.util.JackSonUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

/**
 * @author TMW
 * @since 2022/6/29 16:04
 */
public class RequestUtil {
    public static String getPara(HttpServletRequest request) throws Exception {
        if(StrUtil.contains(request.getContentType(), "multipart/form-data")){
            return null;
        }
        if (StrUtil.endWithIgnoreCase(request.getMethod(), "post")) {
            return request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        } else if (StrUtil.endWithIgnoreCase(request.getMethod(), "get")) {
            return JackSonUtil.toJson(request.getParameterMap());
        }
        return null;
    }
}
