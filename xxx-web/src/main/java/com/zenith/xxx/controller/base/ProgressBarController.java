package com.zenith.xxx.controller.base;

import com.efficient.cache.api.CacheUtil;
import com.efficient.cache.vo.DataProgressVO;
import com.efficient.common.result.Result;
import com.zenith.xxx.model.constant.CacheConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @author TMW
 * @since 2021/5/7 17:49
 */
@RestController
@RequestMapping("/progressBar")
@Slf4j
@Validated
@Api(tags = "进度条")
public class ProgressBarController {
    @Autowired
    private CacheUtil cacheUtil;

    @GetMapping("/get")
    @ApiOperation(value = "获取当前进度", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "进度条标识", required = true)
    })
    public Result getProgressBar(@NotNull(message = "key 不能为空") @RequestParam("key") String key) {
        DataProgressVO progressVo = new DataProgressVO();
        try {
            // 文件上传进度
            progressVo = cacheUtil.get(CacheConstant.CACHE_PROGRESS_BAR, key);
            if (progressVo != null && progressVo.getCode() == null) {
                progressVo.setCode("1");
            }
        } catch (Exception e) {
            log.error("获取进度条异常：", e);
        }
        if (progressVo == null) {
            progressVo = new DataProgressVO();
            progressVo.setCurrDataName("初始化");
            progressVo.setRatio("0");
        }
        return Result.ok(progressVo);
    }
}
