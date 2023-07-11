package com.zenith.xxx.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
* <p>
* test_encrypt DTO
* </p>
*
* @author TMW
* @date 2023-07-10 17:05:28
*/
@Data
@ApiModel("test_encrypt 请求实体-TestEncryptDTO")
public class TestEncryptDTO {
    private static final long serialVersionUID = 8270818900029358133L;

    /**
    *id
    */
    @ApiModelProperty(value = "id")
    private String id;
    /**
    *
    */
    @ApiModelProperty(value = "")
    private String name;
    /**
    *
    */
    @ApiModelProperty(value = "")
    private String idCard;
    /**
    *
    */
    @ApiModelProperty(value = "")
    private String address;

}

