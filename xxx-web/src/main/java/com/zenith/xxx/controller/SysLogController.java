package com.zenith.xxx.controller;

import com.efficient.auth.permission.Permission;
import com.efficient.common.result.Result;
import com.efficient.logs.annotation.Log;
import com.efficient.logs.constant.LogEnum;
import com.efficient.logs.model.dto.SysLogDTO;
import com.efficient.logs.model.entity.SysLog;
import com.efficient.logs.model.vo.SysLogVO;
import com.efficient.rate.annotation.RateLimit;
import com.zenith.xxx.api.SysLogServiceApi;
import com.zenith.xxx.model.dto.SysLogListDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 日志记录 controller 层
 * </p>
 *
 * @author code generator
 * @date 2022-07-05 09:48:59
 */
@RestController
@RequestMapping("/sysLog")
@Validated
@Api(tags = "日志记录")
@Permission
public class SysLogController {

    @Autowired
    private SysLogServiceApi sysLogService;

    /**
     * 新增
     */
    @Log(logOpt = LogEnum.INSERT)
    @PostMapping("/save")
    @ApiOperation(value = "保存", response = Result.class)
    public Result save(@Validated @RequestBody SysLogDTO dto) {
        SysLog entity = sysLogService.save(dto);
        return Result.ok(entity);
    }

    /**
     * 详情
     */
    @Log(logOpt = LogEnum.QUERY)
    @GetMapping("/find")
    @ApiOperation(value = "详情", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "数据唯一标识", required = true)
    })
    @RateLimit
    public Result find(@NotBlank(message = "id 不能为空") @RequestParam(name = "id") String id) {
        SysLogVO entity = sysLogService.findById(id);
        return Result.ok(entity);
    }

    /**
     * 修改
     */
    @Log(logOpt = LogEnum.UPDATE)
    @PostMapping("/update")
    @ApiOperation(value = "修改", response = Result.class)
    public Result update(@Validated @RequestBody SysLogDTO dto) {
        boolean flag = sysLogService.update(dto);
        return flag ? Result.ok() : Result.fail();
    }

    /**
     * 删除
     */
    @Log(logOpt = LogEnum.DELETE)
    @GetMapping("/delete")
    @ApiOperation(value = "删除", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "数据唯一标识", required = true)
    })
    public Result delete(@NotBlank(message = "id 不能为空") @RequestParam(name = "id") String id) {
        boolean flag = sysLogService.delete(id);
        return flag ? Result.ok() : Result.fail();
    }

    /**
     * 列表
     */
    @Log(logOpt = LogEnum.QUERY)
    @PostMapping("/list")
    @ApiOperation(value = "列表", response = Result.class)
    @RateLimit
    public Result list(@Validated @RequestBody SysLogListDTO dto) {
        return Result.ok(sysLogService.list(dto));
    }
}
