package com.zenith.xxx.controller;

import com.efficient.common.result.Result;
import com.efficient.auth.permission.Permission;
import com.efficient.logs.annotation.Log;
import com.efficient.logs.constant.LogEnum;
import com.zenith.xxx.api.TestEncryptService;
import com.zenith.xxx.model.dto.TestEncryptDTO;
import com.zenith.xxx.model.dto.TestEncryptListDTO;
import com.zenith.xxx.model.entity.TestEncrypt;
import com.zenith.xxx.model.vo.TestEncryptVO;
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
* test_encrypt controller 层
* </p>
*
* @author TMW
* @date 2023-07-10 17:05:28
*/
@RestController
@RequestMapping("/testEncrypt")
@Validated
@Api(tags = "test_encrypt")
// @Permission
public class TestEncryptController {

    @Autowired
    private TestEncryptService testEncryptService;

    /**
    * 新增
    */
    @Log(logOpt = LogEnum.SAVE)
    @PostMapping("/save")
    @ApiOperation(value = "保存", response = Result.class)
    public Result save(@Validated @RequestBody TestEncryptDTO dto) {
        TestEncrypt entity = testEncryptService.save(dto);
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
    public Result find(@NotBlank(message = "id 不能为空") @RequestParam(name="id") String id) {
        TestEncryptVO entity = testEncryptService.findById(id);
        return Result.ok(entity);
    }

    /**
    * 修改
    */
    @Log(logOpt = LogEnum.UPDATE)
    @PostMapping("/update")
    @ApiOperation(value = "修改", response = Result.class)
    public Result update(@Validated @RequestBody TestEncryptDTO dto) {
        boolean flag = testEncryptService.update(dto);
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
    public Result delete(@NotBlank(message = "id 不能为空") @RequestParam(name="id") String id) {
        boolean flag = testEncryptService.delete(id);
        return flag ? Result.ok() : Result.fail();
    }

    /**
    * 列表
    */
    @Log(logOpt = LogEnum.PAGE)
    @PostMapping("/list")
    @ApiOperation(value = "列表", response = Result.class)
    public Result list(@Validated @RequestBody TestEncryptListDTO dto) {
        return Result.ok(testEncryptService.list(dto));
    }
}
