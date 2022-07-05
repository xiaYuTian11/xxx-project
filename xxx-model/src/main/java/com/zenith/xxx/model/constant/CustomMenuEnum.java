package com.zenith.xxx.model.constant;

import com.sjr.common.permission.MenuConstants;

/**
 * 菜单配置
 *
 * @author TMW
 * @since 2022/7/5 15:33
 */
public enum CustomMenuEnum implements MenuConstants {
    ;

    private String code;
    private String name;
    private String desc;

    CustomMenuEnum(String code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public void setDesc(String desc) {
        this.desc = desc;
    }
}
