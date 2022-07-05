package com.zenith.xxx.model.converter;

import com.zenith.xxx.model.dto.SysLogDTO;
import com.zenith.xxx.model.entity.SysLog;
import com.zenith.xxx.model.vo.SysLogVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
* <p>
* 日志记录 模型转换器
* </p>
*
* @author code generator
* @date 2022-07-05 09:48:59
*/
@Mapper(componentModel = "spring")
public interface SysLogConverter {

    SysLogConverter INSTANCE = Mappers.getMapper(SysLogConverter.class);

    @Mappings({})
    SysLog dto2Entity(SysLogDTO dto);

    @Mappings({})
    SysLogVO entity2Vo(SysLog entity);

}
