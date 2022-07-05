package com.zenith.xxx.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
* <p>
* 日志记录 列表查询DTO
* </p>
*
* @author code generator
* @date 2022-07-05 09:48:59
*/
@Data
@ApiModel("日志记录 列表查询-SysLogListDTO")
public class SysLogListDTO {
    private static final long serialVersionUID = 322764680306735961L;
    @NotNull(message = "pageNum 不能为空")
    private Integer pageNum;
    @NotNull(message = "pageSize 不能为空")
    private Integer pageSize;
}
