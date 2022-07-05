package com.zenith.xxx.config;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.sjr.common.entity.Result;
import com.sjr.common.entity.ResultEnum;
import com.sjr.common.entity.UserTicket;
import com.sjr.common.log.Log;
import com.sjr.common.log.OptTypeEnum;
import com.sjr.common.util.JackSonUtil;
import com.sjr.common.util.RequestHolder;
import com.sjr.common.util.WebUtil;
import com.zenith.xxx.api.SysLogService;
import com.zenith.xxx.constant.GlobalConstant;
import com.zenith.xxx.model.entity.SysLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Objects;

/**
 * log apo
 *
 * @author TMW
 * @since 2022/3/2 17:43
 */
@Aspect
@Component
public class LogAop {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAop.class);

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysLogService sysLogService;
    public static final TransmittableThreadLocal<Long> START_TIME = new TransmittableThreadLocal<>();
    private static final String REQUEST_NORMAL_FORMAT = "\n=============\nurl:%s\ncontroller:%s\nmethodName:%s\nargs:%s\ntoken:%s\nreturn:%s\ntime:%d\n请求成功\n=============";
    private static final String REQUEST_ERROR_FORMAT = "\n=============\nurl:%s\ncontroller:%s\nmethodName:%s\nargs:%s\ntoken:%s\nreturn:%s\nerror:%s\ntime:%d\n请求失败\n=============";

    @Pointcut("@annotation(com.sjr.common.log.Log)")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void doBefore() {
        START_TIME.set(System.currentTimeMillis());
    }

    /**
     * 正常返回通知，拦截用户操作日志，连接点正常执行完成后执行，如果连接点抛出异常，则不会执行
     * 设置操作日志切入点 记录操作日志 在注解的位置切入代码
     *
     * @param joinPoint 切入点
     * @param keys      返回结果
     */
    @AfterReturning(value = "pointCut()", returning = "keys")
    public void doAfterLog(JoinPoint joinPoint, Object keys) {
        recordLog(joinPoint, keys, null);
    }

    @AfterThrowing(value = "pointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        recordLog(joinPoint, null, e);
    }

    private void recordLog(JoinPoint joinPoint, Object keys, Exception exception) {
        final String ip = WebUtil.getIP(request);
        final String requestUrl = request.getRequestURI();
        final String token = request.getHeader(GlobalConstant.TOKEN);
        com.sjr.common.util.ThreadUtil.EXECUTOR_SERVICE.execute(() -> {
            try {
                // 从切面织入点处通过反射机制获取织入点处的方法
                MethodSignature signature = (MethodSignature) joinPoint.getSignature();
                // 获取请求的类名
                String className = joinPoint.getTarget().getClass().getName();
                // 获取切入点所在的方法
                Method method = signature.getMethod();
                Object[] args = joinPoint.getArgs();
                String argsStr = JackSonUtil.toJson(args);
                // 获取请求的方法名
                String methodName = method.getName();
                // 获取操作
                Log opLog = method.getAnnotation(Log.class);
                if (Objects.isNull(opLog)) {
                    return;
                }
                String returnValue = JackSonUtil.toJson(keys);
                long endTime = System.currentTimeMillis();
                SysLog sysLog = new SysLog();
                if (Objects.nonNull(exception)) {
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    exception.printStackTrace(pw);
                    pw.close();
                    LOGGER.error(String.format(REQUEST_ERROR_FORMAT, ip, requestUrl, className, argsStr, token, "无", sw, 0L), exception);
                    sysLog.setResultCode(String.valueOf(ResultEnum.ERROR.getCode()));
                    sysLog.setException(sw.toString());
                } else {
                    Result result = (Result) keys;
                    sysLog.setResultCode(String.valueOf(result.getCode()));
                    LOGGER.info(String.format(REQUEST_NORMAL_FORMAT, ip, requestUrl, className, argsStr, token, returnValue, (endTime - START_TIME.get())));
                }

                UserTicket currUser = RequestHolder.getCurrUser();
                if (Objects.isNull(currUser)) {
                    currUser = new UserTicket();
                }
                sysLog.setAccount(currUser.getAccount());
                sysLog.setIp(ip);
                sysLog.setUrl(requestUrl);
                sysLog.setMethod(className + GlobalConstant.POINT + methodName);
                sysLog.setParams(argsStr);
                sysLog.setResponse(returnValue);
                sysLog.setOperType(opLog.optType().getText());
                StringBuffer sb = new StringBuffer();
                sb.append(currUser.getUsername());
                if (!Objects.equals(opLog.optType(), OptTypeEnum.NULL)) {
                    sb.append(opLog.optType().getText()).append("了").append(opLog.desc());
                } else {
                    sb.append(opLog.desc());
                }
                sysLog.setOperDesc(sb.toString());
                sysLog.setModule(opLog.module());
                sysLog.setCreateTime(new Date());
                sysLogService.save(sysLog);
            } catch (Exception e) {
                LOGGER.error("日志记录异常：", e);
            }
        });
    }

    /**
     * 打印异常日志
     *
     * @param ip
     * @param requestUrl
     * @param token
     * @param className
     * @param argsStr
     * @param e
     */
    private void printErrorLog(String ip, String requestUrl, String token, String className, String argsStr, Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        pw.close();
        LOGGER.error(String.format(REQUEST_ERROR_FORMAT, ip, requestUrl, className, argsStr, token, "无", sw, 0L), e);
    }

}
