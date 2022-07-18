package com.zenith.xxx.model.constant;

import com.sjr.common.result.ResultConstant;

/**
 * 自定义返回枚举
 *
 * @author TMW
 * @since 2022/7/5 14:48
 */
public enum CustomResultEnum implements ResultConstant {
    ;

    CustomResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;

    private String msg;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
