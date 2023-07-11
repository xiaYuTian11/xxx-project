package com.zenith.xxx.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.efficient.data.security.annotation.DbEncrypted;
import com.efficient.data.security.annotation.DbFieldEncrypted;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* <p>
* test_encrypt 实体类
* </p>
*
* @author TMW
* @date 2023-07-10 17:05:28
*/
@Data
@TableName("test_encrypt")
@ApiModel("test_encrypt")
@DbEncrypted
public class TestEncrypt implements Serializable {

    private static final long serialVersionUID = 114799311725116448L;

    /**
    *id
    */
    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;
    /**
    *
    */
    @ApiModelProperty(value = "")
    @TableField(value = "name",typeHandler = com.efficient.data.security.db.typehandler.EncryptTypeHandler.class)
    @DbFieldEncrypted
    private String name;
    /**
    *
    */
    @ApiModelProperty(value = "")
    @TableField(value = "id_card",typeHandler = com.efficient.data.security.db.typehandler.EncryptTypeHandler.class)
    @DbFieldEncrypted
    private String idCard;
    /**
    *
    */
    @ApiModelProperty(value = "")
    @TableField(value = "address",typeHandler = com.efficient.data.security.db.typehandler.EncryptTypeHandler.class)
    @DbFieldEncrypted
    private String address;
}
