package com.zenith.xxx.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.efficient.logs.api.SysLogService;
import com.efficient.logs.dao.SysLogMapper;
import com.efficient.logs.model.converter.SysLogConverter;
import com.efficient.logs.model.dto.SysLogDTO;
import com.efficient.logs.model.entity.SysLog;
import com.efficient.logs.model.vo.SysLogVO;
import com.zenith.xxx.api.SysLogServiceApi;
import com.zenith.xxx.model.dto.SysLogListDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 日志记录 服务实现类
 * </p>
 *
 * @author code generator
 * @date 2022-07-05 09:48:59
 */
@Service
public class SysLogServiceApiImpl implements SysLogServiceApi {

    @Autowired
    private SysLogConverter sysLogConverter;
    @Autowired
    private SysLogService sysLogService;
    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public SysLog save(SysLogDTO dto) {
        SysLog entity = sysLogConverter.dto2Entity(dto);
        boolean flag = sysLogService.save(entity);
        return entity;
    }

    @Override
    public SysLogVO findById(String id) {
        SysLog entity = sysLogService.getById(id);
        return sysLogConverter.entity2Vo(entity);
    }

    @Override
    public Boolean update(SysLogDTO dto) {
        return sysLogService.updateById(sysLogConverter.dto2Entity(dto));
    }

    @Override
    public Boolean delete(String id) {
        return sysLogService.removeById(id);
    }

    @Override
    public Page<SysLog> list(SysLogListDTO dto) {
        final Page<SysLog> page = sysLogMapper.selectPage(new Page<>(dto.getPageNum(), dto.getPageSize()), new QueryWrapper<>());
        return page;
    }
}
