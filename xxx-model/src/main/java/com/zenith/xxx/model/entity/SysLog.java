package com.zenith.xxx.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* <p>
* 日志记录 实体类
* </p>
*
* @author code generator
* @date 2022-07-05 09:48:59
*/
@Data
@TableName("sys_log")
@ApiModel("日志记录")
public class SysLog implements Serializable {

    private static final long serialVersionUID = 3551202774597876464L;

    /**
    *主键
    */
    @ApiModelProperty(value = "主键")
    @TableId("id")
    private String id;
    /**
    *用户账号
    */
    @ApiModelProperty(value = "用户账号")
    @TableField("account")
    private String account;
    /**
    *ip地址
    */
    @ApiModelProperty(value = "ip地址")
    @TableField("ip")
    private String ip;
    /**
    *访问URL
    */
    @ApiModelProperty(value = "访问URL")
    @TableField("url")
    private String url;
    /**
    *请求方法
    */
    @ApiModelProperty(value = "请求方法")
    @TableField("method")
    private String method;
    /**
    *请求参数
    */
    @ApiModelProperty(value = "请求参数")
    @TableField("params")
    private String params;
    /**
    *返回值
    */
    @ApiModelProperty(value = "返回值")
    @TableField("response")
    private String response;
    /**
    *返回cde
    */
    @ApiModelProperty(value = "返回cde")
    @TableField("result_code")
    private String resultCode;
    /**
    *操作类型
    */
    @ApiModelProperty(value = "操作类型")
    @TableField("oper_type")
    private String operType;
    /**
    *操作描述
    */
    @ApiModelProperty(value = "操作描述")
    @TableField("oper_desc")
    private String operDesc;
    /**
    *操作模块
    */
    @ApiModelProperty(value = "操作模块")
    @TableField("module")
    private String module;
    /**
    *异常信息
    */
    @ApiModelProperty(value = "异常信息")
    @TableField("exception")
    private String exception;
    /**
    *创建时间
    */
    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;
}
