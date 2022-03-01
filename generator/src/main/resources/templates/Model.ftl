package ${package_name};

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* <p>
* ${table_describe} 实体类
* </p>
*
* @author ${author}
* @date ${date}
*/
@Data
@TableName("${table_name_small}")
@ApiModel("${table_describe}")
public class ${table_name} implements Serializable {

    private static final long serialVersionUID = ${serialVersionUID};

<#if model_column??>
<#list model_column as model>
    /**
    *${model.columnComment!}
    */
    @ApiModelProperty(value = "${model.columnComment!}")
    <#if (model.primaryKey = true)>
    @TableId("${model.columnName}")
    private String ${model.changeColumnName?uncap_first};

    <#elseif (model.columnName = 'is_delete')>
    @TableLogic
    @TableField(value = "isDelete", fill = FieldFill.INSERT)
    private Integer isDelete;
    <#elseif (model.columnName = 'create_time')>
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    <#elseif (model.columnName = 'create_user')>
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private String createUser;
    <#elseif (model.columnName = 'update_time')>
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    <#elseif (model.columnName = 'update_user')>
    @TableField(value = "update_user", fill = FieldFill.INSERT_UPDATE)
    private String updateUser;
    <#else>
    @TableField("${model.columnName}")
    <#if (model.columnType = 'varchar' || model.columnType = 'text')>
    private String ${model.changeColumnName?uncap_first};
    </#if>
    <#if model.columnType = 'timestamp' ||  model.columnType = 'date' >
    private Date ${model.changeColumnName?uncap_first};
    </#if>
    <#if model.columnType = 'smallint' || model.columnType = 'int'  || model.columnType = 'int2'
    || model.columnType = 'int4' || model.columnType = 'Integer' || model.columnType = 'bit'>
    private Integer ${model.changeColumnName?uncap_first};
    </#if>
    <#if (model.columnType = 'bigint' || model.columnType = 'int8')>
    private Long ${model.changeColumnName?uncap_first};
    </#if>
    <#if model.columnType = 'numeric' ||  model.columnType = 'decimal'>
    private java.math.BigDecimal ${model.changeColumnName?uncap_first};
    </#if>
    </#if>
</#list>
</#if>
}
