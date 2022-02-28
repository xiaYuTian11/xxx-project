package ${package_name};

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sjr.common.entity.Result;
import com.sjr.common.entity.ResultEnum;
import ${api_package_name}.${table_name}Service;
import ${converter_package_name}.${table_name}Converter;
import ${dao_package_name}.${table_name}Mapper;
import ${dto_package_name}.${table_name}DTO;
import ${dto_package_name}.${table_name}ListDTO;
import ${entity_package_name}.${table_name};
import ${vo_package_name}.${table_name}VO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
* @author ${author}
* @date ${date}
*/
@Service
public class ${table_name}ServiceImpl extends ServiceImpl<${table_name}Mapper, ${table_name}> implements ${table_name}Service {

    @Resource
    private ${table_name}Converter ${lower_table_name}Converter;
    @Resource
    private ${table_name}Mapper ${lower_table_name}Mapper;

    @Override
    public Result save(${table_name}DTO dto) {
        ${table_name} entity = ${lower_table_name}Converter.dto2Entity(dto);
        boolean flag = this.save(entity);
        return flag ? Result.ok(entity.getId()) : Result.fail();
    }

    @Override
    public Result findById(String id) {
        ${table_name} entity = this.getById(id);
        return Objects.isNull(entity) ? Result.build(ResultEnum.DATA_NOT_EXIST) : Result.ok(${lower_table_name}Converter.entity2Vo(entity));
    }

    @Override
    public Result update(${table_name}DTO dto) {
        final boolean flag = this.updateById(${lower_table_name}Converter.dto2Entity(dto));
        return flag ? Result.ok() : Result.fail();
    }

    @Override
    public Result delete(String id) {
        final boolean flag = this.removeById(id);
        return flag ? Result.ok() : Result.fail();
    }

    @Override
    public Result list(${table_name}ListDTO dto) {
        final Page<${table_name}> page = ${lower_table_name}Mapper.selectPage(new Page<>(dto.getPageNum(), dto.getPageSize()), new QueryWrapper<>());
        return Result.ok(page);
    }
}
