package com.sjr.common.permission;

/**
 * 菜单配置
 *
 * @author TMW
 * @since 2022/4/28 9:29
 */
public enum MenuEnum {

    IS_NULL(null, "暂未配置模块", "暂未配置模块");

    private String code;
    private String name;
    private String desc;

    MenuEnum(String code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
