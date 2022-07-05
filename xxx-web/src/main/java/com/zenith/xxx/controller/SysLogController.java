package com.zenith.xxx.controller;

import com.sjr.common.entity.Result;
import com.sjr.common.log.Log;
import com.sjr.common.log.OptTypeEnum;
import com.sjr.common.permission.Permission;
import com.zenith.xxx.api.SysLogService;
import com.zenith.xxx.model.dto.SysLogDTO;
import com.zenith.xxx.model.dto.SysLogListDTO;
import com.zenith.xxx.model.entity.SysLog;
import com.zenith.xxx.model.vo.SysLogVO;
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
    private SysLogService sysLogService;

    /**
    * 新增
    */
    @Log(optType = OptTypeEnum.INSERT)
    @PostMapping("/save")
    @ApiOperation(value = "保存", response = Result.class)
    public Result save(@Validated @RequestBody SysLogDTO dto) {
        SysLog entity = sysLogService.save(dto);
        return Result.ok(entity);
    }

    /**
    * 详情
    */
    @Log(optType = OptTypeEnum.QUERY)
    @GetMapping("/find")
    @ApiOperation(value = "详情", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "数据唯一标识", required = true)
    })
    public Result find(@NotBlank(message = "id 不能为空") @RequestParam(name="id") String id) {
        SysLogVO entity = sysLogService.findById(id);
        return Result.ok(entity);
    }

    /**
    * 修改
    */
    @Log(optType = OptTypeEnum.UPDATE)
    @PostMapping("/update")
    @ApiOperation(value = "修改", response = Result.class)
    public Result update(@Validated @RequestBody SysLogDTO dto) {
        boolean flag = sysLogService.update(dto);
        return flag ? Result.ok() : Result.fail();
    }

    /**
    * 删除
    */
    @Log(optType = OptTypeEnum.DELETE)
    @GetMapping("/delete")
    @ApiOperation(value = "删除", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "数据唯一标识", required = true)
    })
    public Result delete(@NotBlank(message = "id 不能为空") @RequestParam(name="id") String id) {
        boolean flag = sysLogService.delete(id);
        return flag ? Result.ok() : Result.fail();
    }

    /**
    * 列表
    */
    @Log(optType = OptTypeEnum.PAGE)
    @PostMapping("/list")
    @ApiOperation(value = "列表", response = Result.class)
    public Result list(@Validated @RequestBody SysLogListDTO dto) {
        return Result.ok(sysLogService.list(dto));
    }
}
