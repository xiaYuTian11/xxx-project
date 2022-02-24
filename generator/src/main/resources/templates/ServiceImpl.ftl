package ${package_name};

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sjr.common.entity.Result;
import ${api_package_name}.${table_name}Service;
import ${converter_package_name}.${table_name}Converter;
import ${dao_package_name}.${table_name}Mapper;
import ${dto_package_name}.${table_name}DTO;
import ${dto_package_name}.${table_name}ListDTO;
import ${entity_package_name}.${table_name};
import com.zenith.front.domain.vo.PageVOExt;
import ${vo_package_name}.${table_name}VO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    public String save(${table_name}DTO dto) {
        ${table_name} entity = ${lower_table_name}Converter.dto2Entity(dto);
        boolean save = this.save(entity);
        return save ? entity.get${primary_key_field}() : null;
    }

    @Override
    public ${table_name}VO findById(String id) {
        ${table_name} entity = this.getById(id);
        return Objects.isNull(entity) ? null : ${lower_table_name}Converter.entity2Vo(entity);
    }

    @Override
    public boolean update(${table_name}DTO dto) {
        return this.updateById(${lower_table_name}Converter.dto2Entity(dto));
    }

    @Override
    public boolean delete(String id) {
        return this.removeById(id);
    }

    @Override
    public PageVO<?> list(${table_name}ListDTO dto) {
    final Page<${table_name}> page = ${lower_table_name}Mapper.selectPage(new Page<>(dto.getCurrPage(), dto.getPageSize()), new QueryWrapper<>());
        return page;
    }
}
