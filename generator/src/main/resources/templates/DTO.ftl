package ${package_name};

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
* <p>
* ${table_describe} DTO
* </p>
*
* @author ${author}
* @date ${date}
*/
@Data
@ApiModel("${table_describe}")
public class ${table_name}DTO {
    private static final long serialVersionUID = ${serialVersionUID};

<#if model_column??>
<#list model_column as model>
    /**
    *${model.columnComment!}
    */
    @ApiModelProperty(value = "${model.columnComment!}")
    <#if (model.columnType = 'BIGINT' || model.columnType = 'int8')>
    private Long ${model.changeColumnName?uncap_first};
    </#if>
    <#if (model.columnType = 'varchar' || model.columnType = 'text')>
    private String ${model.changeColumnName?uncap_first};
    </#if>
    <#if model.columnType = 'timestamp' >
    private Date ${model.changeColumnName?uncap_first};
    </#if>
    <#if model.columnType = 'smallint' || model.columnType = 'int'  || model.columnType = 'int2'
    || model.columnType = 'int4' || model.columnType = 'Integer' || model.columnType = 'bit'>
    private Integer ${model.changeColumnName?uncap_first};
    </#if>
    <#if model.columnType = 'numeric'>
    private java.math.BigDecimal ${model.changeColumnName?uncap_first};
    </#if>
</#list>
</#if>

}

