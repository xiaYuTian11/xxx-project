// package com.zenith.xxx.interceptor;
//
// import cn.hutool.core.collection.CollUtil;
// import cn.hutool.core.thread.ThreadUtil;
// import cn.hutool.core.util.StrUtil;
// import com.efficient.auth.permission.Permission;
// import com.efficient.cache.api.CacheUtil;
// import com.efficient.common.auth.RequestHolder;
// import com.efficient.common.auth.UserTicket;
// import com.efficient.common.constant.MenuRelation;
// import com.efficient.common.result.Result;
// import com.efficient.common.result.ResultEnum;
// import com.efficient.common.util.JackSonUtil;
// import com.zenith.xxx.model.constant.GlobalConstant;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.method.HandlerMethod;
// import org.springframework.web.servlet.HandlerInterceptor;
//
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import java.io.IOException;
// import java.io.PrintWriter;
// import java.util.Arrays;
// import java.util.List;
// import java.util.Objects;
//
// /**
//  * 权限拦截器
//  *
//  * @author TMW
//  * @date 2021/3/4 21:19
//  */
// // @Component
// @Slf4j
// public class PermissionInterceptor implements HandlerInterceptor {
//     @Autowired
//     private CacheUtil cacheUtil;
//
//     @Override
//     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//         HandlerMethod method;
//         RequestHolder.set(request);
//         try {
//             method = (HandlerMethod) handler;
//         } catch (ClassCastException e) {
//             log.error(e.getMessage(), e);
//             this.returnJson(response, ResultEnum.ERROR_PATH);
//             return false;
//         }
//         String a = "1";
//         String b = "1";
//         if (Objects.equals(a, b)) {
//             return true;
//         }
//         // 用户权限注解
//         Permission permission = method.getMethodAnnotation(Permission.class);
//         if (Objects.isNull(permission)) {
//             permission = method.getBeanType().getAnnotation(Permission.class);
//         }
//
//         if (Objects.isNull(permission)) {
//             return true;
//         }
//         String token = request.getHeader(GlobalConstant.TOKEN);
//         // header中没有token
//         if (StrUtil.isBlank(token)) {
//             this.returnJson(response, ResultEnum.NOT_LOGIN);
//             return false;
//         }
//         // 根据token查询
//         UserTicket userTicket = cacheUtil.get("user-cache", token);
//         if (userTicket == null) {
//             this.returnJson(response, ResultEnum.NOT_LOGIN);
//             return false;
//         }
//         // 刷新用户信息保留时间
//         cacheUtil.refresh("user-cache", token, 60 * 15);
//         // 权限校验
//         final boolean checkPermission = checkPermission(permission, userTicket.getPermissionList());
//         if (!checkPermission) {
//             this.returnJson(response, ResultEnum.NOT_PERMISSION);
//             return false;
//         }
//         // 放入上下文
//         RequestHolder.set(userTicket);
//         RequestHolder.set(request);
//
//         return true;
//     }
//
//     private boolean checkPermission(Permission permission, List<String> currMenus) {
//         final String[] menuEnums = permission.value();
//         if (menuEnums.length <= 0) {
//             return true;
//         }
//         if (CollUtil.isEmpty(currMenus)) {
//             return false;
//         }
//         final MenuRelation relation = permission.relation();
//         if (Objects.equals(relation, MenuRelation.OR)) {
//             return Arrays.stream(menuEnums).anyMatch(currMenus::contains);
//         } else {
//             return Arrays.stream(menuEnums).allMatch(currMenus::contains);
//         }
//     }
//
//     /**
//      * 返回前端信息
//      *
//      * @param response
//      * @param resultEnum
//      * @throws IOException
//      */
//     private void returnJson(HttpServletResponse response, ResultEnum resultEnum) throws IOException {
//         response.setCharacterEncoding("UTF-8");
//         response.setContentType("application/json; charset=utf-8");
//         PrintWriter out = response.getWriter();
//         out.append(JackSonUtil.toJson(Result.build(resultEnum)));
//         out.close();
//     }
//
//     @Override
//     public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//         RequestHolder.remove();
//         HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//     }
//
//     public static void main(String[] args) {
//         System.out.println(System.currentTimeMillis());
//         System.out.println(System.nanoTime());
//         ThreadUtil.sleep(1000);
//         System.out.println(System.currentTimeMillis());
//         System.out.println(System.nanoTime());
//     }
// }
