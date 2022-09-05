package com.zenith.xxx.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.efficient.common.result.Result;
import com.efficient.common.log.OptTypeEnum;
import com.zenith.xxx.model.dto.SysLogDTO;
import com.zenith.xxx.model.dto.SysLogListDTO;
import com.zenith.xxx.model.entity.SysLog;
import com.zenith.xxx.model.vo.SysLogVO;

/**
* <p>
* 日志记录 服务Api
* </p>
 *
 * @author code generator
 * @date 2022-07-05 09:48:59
 */
public interface SysLogService extends IService<SysLog> {
    /***
     * 新增
     */
    SysLog save(SysLogDTO dto);

    /**
     * 拼接日志保存
     *
     * @param optTypeEnum    操作类型枚举
     * @param module         模块
     * @param desc           描述
     * @param requestParams  请求参数
     * @param responseParams 返回值
     * @param e              异常
     */
    void saveLog(OptTypeEnum optTypeEnum, String module, String desc, Object requestParams, Result responseParams, Exception e);

    /**
     * 不拼接日志保存
     *
     * @param optTypeEnum    操作类型枚举
     * @param module         模块
     * @param desc           描述
     * @param requestParams  请求参数
     * @param responseParams 返回值
     * @param e              异常
     */
    void saveLogNotJoin(OptTypeEnum optTypeEnum, String module, String desc, Object requestParams, Result responseParams, Exception e);

    /**
     * 详情
     */
    SysLogVO findById(String id);

    /**
     * 修改
     */
    Boolean update(SysLogDTO dto);

    /**
    * 删除
    */
    Boolean delete(String id);

    /**
    * 列表查询
    */
    Page<SysLog> list(SysLogListDTO dto);
}
