package com.zenith.xxx.controller.base;

import com.sjr.common.entity.DataProgressVo;
import com.sjr.common.entity.Result;
import com.zenith.xxx.model.constant.CacheConstant;
import com.zenith.xxx.util.CacheUtil;
import lombok.extern.slf4j.Slf4j;
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
public class ProgressBarController {

    @GetMapping("/get")
    public Result getProgressBar(@NotNull(message = "key 不能为空") @RequestParam("key") String key) {
        DataProgressVo progressVo = new DataProgressVo();
        try {
            // 文件上传进度
            progressVo = CacheUtil.get(CacheConstant.CACHE_PROGRESS_BAR, key);
            if (progressVo != null && progressVo.getCode() == null) {
                progressVo.setCode("1");
            }
        } catch (Exception e) {
            log.error("获取进度条异常：", e);
        }
        if (progressVo == null) {
            progressVo = new DataProgressVo();
            progressVo.setCurrDataName("初始化");
            progressVo.setRatio("0");
        }
        return Result.ok(progressVo);
    }
}
