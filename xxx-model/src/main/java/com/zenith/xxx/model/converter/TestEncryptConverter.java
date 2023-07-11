package com.zenith.xxx.model.converter;

import com.zenith.xxx.model.dto.TestEncryptDTO;
import com.zenith.xxx.model.entity.TestEncrypt;
import com.zenith.xxx.model.vo.TestEncryptVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
* <p>
* test_encrypt 模型转换器
* </p>
*
* @author TMW
* @date 2023-07-10 17:05:28
*/
@Mapper(componentModel = "spring")
public interface TestEncryptConverter {

    TestEncryptConverter INSTANCE = Mappers.getMapper(TestEncryptConverter.class);

    @Mappings({})
    TestEncrypt dto2Entity(TestEncryptDTO dto);

    @Mappings({})
    TestEncryptVO entity2Vo(TestEncrypt entity);

}
