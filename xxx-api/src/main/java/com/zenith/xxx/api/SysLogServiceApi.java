package com.zenith.xxx.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.efficient.logs.model.dto.SysLogDTO;
import com.efficient.logs.model.entity.SysLog;
import com.efficient.logs.model.vo.SysLogVO;
import com.zenith.xxx.model.dto.SysLogListDTO;

/**
 * <p>
 * 日志记录 服务Api
 * </p>
 *
 * @author code generator
 * @date 2022-07-05 09:48:59
 */
public interface SysLogServiceApi {
    /***
     * 新增
     */
    SysLog save(SysLogDTO dto);

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
