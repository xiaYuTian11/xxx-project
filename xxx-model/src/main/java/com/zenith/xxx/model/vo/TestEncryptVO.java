package com.zenith.xxx.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
* <p>
* test_encrypt VO
* </p>
*
* @author TMW
* @date 2023-07-10 17:05:28
*/
@Data
@ApiModel("test_encrypt 返回实体-TestEncryptVO")
public class TestEncryptVO {

    private static final long serialVersionUID = 7540245746881479107L;

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
