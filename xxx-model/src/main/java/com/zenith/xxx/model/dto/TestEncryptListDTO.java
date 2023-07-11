package com.zenith.xxx.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
* <p>
* test_encrypt 列表查询DTO
* </p>
*
* @author TMW
* @date 2023-07-10 17:05:28
*/
@Data
@ApiModel("test_encrypt 列表查询-TestEncryptListDTO")
public class TestEncryptListDTO {
    private static final long serialVersionUID = 565652532581741253L;
    @NotNull(message = "pageNum 不能为空")
    private Integer pageNum;
    @NotNull(message = "pageSize 不能为空")
    private Integer pageSize;
}
