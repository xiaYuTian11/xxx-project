package ${package_name};

import com.sjr.common.entity.Result;
import ${api_package_name}.${table_name}Service;
import ${dto_package_name}.${table_name}DTO;
import ${dto_package_name}.${table_name}ListDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
* <p>
* ${table_describe} controller 层
* </p>
*
* @author ${author}
* @date ${date}
*/
@RestController
@RequestMapping("/${lower_table_name}")
@Validated
@Api(tags = "${table_describe}")
public class ${table_name}Controller {

    @Autowired
    private ${table_name}Service ${lower_table_name}Service;

    /**
    * 新增
    */
    @PostMapping("/save")
    @ApiOperation(value = "保存", response = Result.class)
    public Result save(@Validated @RequestBody ${table_name}DTO dto) {
        return ${lower_table_name}Service.save(dto);
    }

    /**
    * 详情
    */
    @GetMapping("/find")
    @ApiOperation(value = "详情", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "数据唯一标识", required = true)
    })
    public Result find(@NotNull(message = "id 不能为空") @RequestParam(name="id") String id) {
        return ${lower_table_name}Service.findById(id);
    }

    /**
    * 修改
    */
    @PostMapping("/update")
    @ApiOperation(value = "修改", response = Result.class)
    public Result update(@Validated @RequestBody ${table_name}DTO dto) {
        return ${lower_table_name}Service.update(dto);
    }

    /**
    * 删除
    */
    @GetMapping("/delete")
    @ApiOperation(value = "删除", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "数据唯一标识", required = true)
    })
    public Result delete(@NotNull(message = "id 不能为空") @RequestParam(name="id") String id) {
        return ${lower_table_name}Service.delete(id);
    }

    /**
    * 列表
    */
    @PostMapping("/list")
    @ApiOperation(value = "列表", response = Result.class)
    public Result list(@Validated @RequestBody ${table_name}ListDTO dto) {
        return ${lower_table_name}Service.list(dto);
    }
}
