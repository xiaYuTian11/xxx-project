package com.zenith.xxx.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
* <p>
* 日志记录 DTO
* </p>
*
* @author code generator
* @date 2022-07-05 09:48:59
*/
@Data
@ApiModel("日志记录 请求实体-SysLogDTO")
public class SysLogDTO {
    private static final long serialVersionUID = 995239935926487591L;

    /**
    *主键
    */
    @ApiModelProperty(value = "主键")
    private String id;
    /**
    *用户账号
    */
    @ApiModelProperty(value = "用户账号")
    private String account;
    /**
    *ip地址
    */
    @ApiModelProperty(value = "ip地址")
    private String ip;
    /**
    *访问URL
    */
    @ApiModelProperty(value = "访问URL")
    private String url;
    /**
    *请求方法
    */
    @ApiModelProperty(value = "请求方法")
    private String method;
    /**
    *请求参数
    */
    @ApiModelProperty(value = "请求参数")
    private String params;
    /**
    *返回值
    */
    @ApiModelProperty(value = "返回值")
    private String response;
    /**
    *返回cde
    */
    @ApiModelProperty(value = "返回cde")
    private String resultCode;
    /**
    *操作类型
    */
    @ApiModelProperty(value = "操作类型")
    private String operType;
    /**
    *操作描述
    */
    @ApiModelProperty(value = "操作描述")
    private String operDesc;
    /**
    *操作模块
    */
    @ApiModelProperty(value = "操作模块")
    private String module;
    /**
    *异常信息
    */
    @ApiModelProperty(value = "异常信息")
    private String exception;
    /**
    *创建时间
    */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}

