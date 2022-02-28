package ${package_name};

import com.sjr.common.entity.Result;
import ${api_package_name}.${table_name}Service;
import ${dto_package_name}.${table_name}DTO;
import ${dto_package_name}.${table_name}ListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
* @author ${author}
* @date ${date}
*/
@RestController
@RequestMapping("/${lower_table_name}")
@Validated
public class ${table_name}Controller {

    @Autowired
    private ${table_name}Service ${lower_table_name}Service;

    /**
    * 新增
    */
    @PostMapping("/save")
    public Result save(@Validated @RequestBody ${table_name}DTO dto) {
        return ${lower_table_name}Service.save(dto);
    }

    /**
    * 详情
    */
    @PostMapping("/find/{id}")
    public Result find(@NotNull(message = "id 不能为空") @PathVariable String id) {
        return ${lower_table_name}Service.findById(id);
    }

    /**
    * 修改
    */
    @PostMapping("/update")
    public Result update(@Validated @RequestBody ${table_name}DTO dto) {
        return ${lower_table_name}Service.update(dto);
    }

    /**
    * 删除
    */
    @PostMapping("/delete/{id}")
    public Result delete(@NotNull(message = "id 不能为空") @PathVariable String id) {
        return ${lower_table_name}Service.delete(id);
    }

    /**
    * 列表
    */
    @PostMapping("/list")
    public Result list(@Validated @RequestBody ${table_name}ListDTO dto) {
        return ${lower_table_name}Service.list(dto);
    }
}
