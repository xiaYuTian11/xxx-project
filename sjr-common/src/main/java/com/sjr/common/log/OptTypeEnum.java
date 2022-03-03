package com.sjr.common.log;

/**
 * 操作类型
 *
 * @author TMW
 * @since 2022/3/2 17:43
 */
public enum OptTypeEnum {

    QUERY(1, "查询"),
    PAGE(1, "查询列表"),
    INSERT(2, "新增"),
    UPDATE(3, "修改"),
    DELETE(4, "删除"),
    EXPORT(7, "导出"),
    IMPORT(8, "导入"),
    LOGIN(11, "登录"),
    LOGINOUT(12, "退出"),
    DOWNLOAD(14, "下载"),
    OTHER(0, "其他");

    private Integer value;
    private String text;

    private OptTypeEnum(Integer value, String text) {
        this.value = value;
        this.text = text;
    }

    public Integer getValue() {
        return this.value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
