package ${package_name};

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

/**
* <p>
* ${table_describe} 列表查询DTO
* </p>
*
* @author ${author}
* @date ${date}
*/
@Data
@ApiModel("${table_describe} 列表查询")
public class ${table_name}ListDTO {
    private static final long serialVersionUID = ${serialVersionUID};
    private Integer pageNum;
    private Integer pageSize;
}
