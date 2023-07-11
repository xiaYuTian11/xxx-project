package com.zenith.xxx.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zenith.xxx.model.dto.TestEncryptDTO;
import com.zenith.xxx.model.dto.TestEncryptListDTO;
import com.zenith.xxx.model.entity.TestEncrypt;
import com.zenith.xxx.model.vo.TestEncryptVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* <p>
* test_encrypt 服务Api
* </p>
*
* @author TMW
* @date 2023-07-10 17:05:28
*/
public interface TestEncryptService extends IService<TestEncrypt> {
    /***
    * 新增
    */
    TestEncrypt save(TestEncryptDTO dto);

    /**
    * 详情
    */
    TestEncryptVO findById(String id);

    /**
    * 修改
    */
    Boolean update(TestEncryptDTO dto);

    /**
    * 删除
    */
    Boolean delete(String id);

    /**
    * 列表查询
    */
    Page<TestEncrypt> list(TestEncryptListDTO dto);
}
