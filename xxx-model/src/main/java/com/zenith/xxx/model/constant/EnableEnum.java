package com.zenith.xxx.model.constant;

/**
 * @author TMW
 * @since 2022/5/11 11:22
 */
public enum EnableEnum {
    /**
     *
     */
    START(1, "启用"),
    STOP(2, "停用");
    private Integer code;
    private String name;

    EnableEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
