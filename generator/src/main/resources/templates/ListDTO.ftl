package ${package_name};

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
* @author ${author}
* @date ${date}
*/
@Data
public class ${table_name}ListDTO {
    private static final long serialVersionUID = ${serialVersionUID};
    private Integer pageNum;
    private Integer pageSize;
}
