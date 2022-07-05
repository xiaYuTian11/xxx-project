package com.zenith.xxx.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sjr.common.entity.Result;
import com.sjr.common.entity.ResultEnum;
import com.sjr.common.entity.UserTicket;
import com.sjr.common.log.OptTypeEnum;
import com.sjr.common.util.JackSonUtil;
import com.sjr.common.util.RequestHolder;
import com.sjr.common.util.ThreadUtil;
import com.zenith.xxx.api.SysLogService;
import com.zenith.xxx.model.converter.SysLogConverter;
import com.zenith.xxx.dao.SysLogMapper;
import com.zenith.xxx.model.dto.SysLogDTO;
import com.zenith.xxx.model.dto.SysLogListDTO;
import com.zenith.xxx.model.entity.SysLog;
import com.zenith.xxx.model.vo.SysLogVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.Objects;

/**
 * <p>
 * 日志记录 服务实现类
 * </p>
 *
 * @author code generator
 * @date 2022-07-05 09:48:59
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

    @Autowired
    private SysLogConverter sysLogConverter;
    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public SysLog save(SysLogDTO dto) {
        SysLog entity = sysLogConverter.dto2Entity(dto);
        boolean flag = this.save(entity);
        return entity;
    }

    @Override
    public void saveLog(OptTypeEnum optTypeEnum, String module, String desc, Object requestParams, Result responseParams, Exception e) {
        this.saveLogNotJoin(optTypeEnum, module, optTypeEnum.getText() + "了" + desc, requestParams, responseParams, e);
    }

    @Override
    public void saveLogNotJoin(OptTypeEnum optTypeEnum, String module, String desc, Object requestParams, Result responseParams, Exception e) {
        ThreadUtil.EXECUTOR_SERVICE.execute(() -> {
            UserTicket currUser = RequestHolder.getCurrUser();
            HttpServletRequest currRequest = RequestHolder.getCurrRequest();
            if (Objects.isNull(currUser)) {
                currUser = new UserTicket();
            }
            SysLog sysLog = new SysLog();
            sysLog.setAccount(currUser.getAccount());
            sysLog.setIp(currUser.getLoginIp());
            sysLog.setUrl(currRequest.getRequestURI());
            sysLog.setMethod(currRequest.getMethod());
            sysLog.setParams(JackSonUtil.toJson(requestParams));
            sysLog.setResponse(JackSonUtil.toJson(responseParams));
            sysLog.setResultCode(String.valueOf(responseParams.getCode()));
            sysLog.setOperType(optTypeEnum.getText());
            sysLog.setOperDesc(currUser.getUsername() + desc);
            sysLog.setModule(module);
            sysLog.setException(null);
            sysLog.setCreateTime(new Date());
            if (Objects.nonNull(e)) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                pw.close();
                sysLog.setResultCode(String.valueOf(ResultEnum.ERROR.getCode()));
                sysLog.setException(sw.toString());
            }
            this.save(sysLog);
        });
    }

    @Override
    public SysLogVO findById(String id) {
        SysLog entity = this.getById(id);
        return sysLogConverter.entity2Vo(entity);
    }

    @Override
    public Boolean update(SysLogDTO dto) {
        return this.updateById(sysLogConverter.dto2Entity(dto));
    }

    @Override
    public Boolean delete(String id) {
        return this.removeById(id);
    }

    @Override
    public Page<SysLog> list(SysLogListDTO dto) {
        final Page<SysLog> page = sysLogMapper.selectPage(new Page<>(dto.getPageNum(), dto.getPageSize()), new QueryWrapper<>());
        return page;
    }
}
