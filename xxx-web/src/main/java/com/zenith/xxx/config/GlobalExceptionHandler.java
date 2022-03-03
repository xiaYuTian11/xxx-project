package com.zenith.xxx.config;

import com.sjr.common.entity.Result;
import com.sjr.common.entity.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.stream.Collectors;

/**
 * 扩展全局异常
 * <p>
 * 针对服务器异常，返回具体异常信息
 *
 * @author TMW
 * @date 2021/2/24 11:17
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    public static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({RuntimeException.class})
    public Result<?> handler(RuntimeException e) {
        LOGGER.error("error: {}", e.getMessage(), e);
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return Result.build(ResultEnum.ERROR, sw.toString());
    }

    @ExceptionHandler({Throwable.class})
    public Result<?> handler(Throwable e) {
        LOGGER.error("error: {}", e.getMessage(), e);
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return Result.build(ResultEnum.ERROR, sw.toString());
    }

    /**
     * 单个参数异常处理
     * 处理请求参数格式错误 @RequestParam上validate失败后抛出的异常
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Result<?> handler(ConstraintViolationException ex) {
        String message = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
        return validateResultFormat(message, ex);
    }

    /**
     * 一般参数校验绑定异常处理
     * 处理Get请求中 使用@Valid 验证路径中请求实体校验失败后抛出的异常
     */
    @ExceptionHandler(value = BindException.class)
    public Result<?> handler(BindException ex) {
        String message = ex.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return validateResultFormat(message, ex);
    }

    /**
     * JSON参数校验绑定异常处理
     * 处理请求参数格式错误 @RequestBody上validate失败后抛出的异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result<?> handler(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return validateResultFormat(message, ex);
    }

    /**
     * Http消息不可读异常返回特定的信息
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<?> httpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return Result.build(ResultEnum.ERROR, String.format("参数格式无效:%s", ex));
    }

    private <T extends Throwable> Result<?> validateResultFormat(String message, T ex) {
        return Result.build(HttpStatus.BAD_REQUEST.value(), message, ex);
    }

}