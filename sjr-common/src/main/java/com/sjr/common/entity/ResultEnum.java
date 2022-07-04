package com.sjr.common.entity;

/**
 * @author TMW
 * @since 2022/2/24 17:13
 */
public enum ResultEnum {

    FAILED(-1, "操作失败"),
    SUCCESS(200, "操作成功"),

    NOT_LOGIN(9994, "用户未登录"),
    REQUEST_PATH_ERROR(9995, "请求路径错误"),
    NOT_PERMISSION(9996, "权限不足"),
    PARA_ERROR(9997, "参数错误"),
    DATA_NOT_EXIST(9998, "数据不存在"),
    ERROR(9999, "系统繁忙");

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
