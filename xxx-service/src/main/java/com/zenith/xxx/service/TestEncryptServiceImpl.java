package com.zenith.xxx.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zenith.xxx.api.TestEncryptService;
import com.zenith.xxx.model.converter.TestEncryptConverter;
import com.zenith.xxx.dao.TestEncryptMapper;
import com.zenith.xxx.model.dto.TestEncryptDTO;
import com.zenith.xxx.model.dto.TestEncryptListDTO;
import com.zenith.xxx.model.entity.TestEncrypt;
import com.zenith.xxx.model.vo.TestEncryptVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
* <p>
* test_encrypt 服务实现类
* </p>
*
* @author TMW
* @date 2023-07-10 17:05:28
*/
@Service
public class TestEncryptServiceImpl extends ServiceImpl<TestEncryptMapper, TestEncrypt> implements TestEncryptService {

    @Autowired
    private TestEncryptConverter testEncryptConverter;
    @Autowired
    private TestEncryptMapper testEncryptMapper;

    @Override
    public TestEncrypt save(TestEncryptDTO dto) {
        TestEncrypt entity = testEncryptConverter.dto2Entity(dto);
        boolean flag = this.save(entity);
        return entity;
    }

    @Override
    public TestEncryptVO findById(String id) {
        LambdaQueryWrapper<TestEncrypt> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(TestEncrypt::getName, "谭明武");
        queryWrapper.last(" limit 1");
        TestEncrypt entity = this.getOne(queryWrapper);

        // TestEncrypt entity = this.getById(id);
        return testEncryptConverter.entity2Vo(entity);
    }

    @Override
    public Boolean update(TestEncryptDTO dto) {
        return this.updateById(testEncryptConverter.dto2Entity(dto));
    }

    @Override
    public Boolean delete(String id) {
        return this.removeById(id);
    }

    @Override
    public Page<TestEncrypt> list(TestEncryptListDTO dto) {
        final Page<TestEncrypt> page = testEncryptMapper.selectPage(new Page<>(dto.getPageNum(), dto.getPageSize()), new QueryWrapper<>());
        return page;
    }
}
