package ${package_name};

import com.sjr.common.entity.Result;
import ${dto_package_name}.${table_name}DTO;
import ${dto_package_name}.${table_name}ListDTO;
import ${entity_package_name}.${table_name};
import ${vo_package_name}.${table_name}VO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* <p>
* ${table_describe} 服务Api
* </p>
*
* @author ${author}
* @date ${date}
*/
public interface ${table_name}Service extends IService<${table_name}> {
    /***
    * 新增
    */
    Result save(${table_name}DTO dto);

    /**
    * 详情
    */
    Result findById(String id);

    /**
    * 修改
    */
    Result update(${table_name}DTO dto);

    /**
    * 删除
    */
    Result delete(String id);

    /**
    * 列表查询
    */
    Result list(${table_name}ListDTO dto);
}
