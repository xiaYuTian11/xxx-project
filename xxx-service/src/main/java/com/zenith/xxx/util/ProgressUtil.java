package com.zenith.xxx.util;


import com.efficient.cache.api.CacheUtil;
import com.efficient.common.constant.ProgressStatus;
import com.efficient.common.vo.DataProgressVO;
import com.zenith.xxx.model.constant.CacheConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.NumberFormat;

/**
 * 进度条工具类
 *
 * @author TMW
 * @since 2022/6/13 15:22
 */
@Component
public class ProgressUtil {
    @Autowired
    private CacheUtil cacheUtil;

    private static ProgressUtil progressUtil;

    @PostConstruct
    public void init() {
        progressUtil = this;
        progressUtil.cacheUtil = this.cacheUtil;
    }

    /**
     * 获取当前
     *
     * @param methodName 方法名称
     * @param token      token
     * @return 进度条标识
     */
    public static String getCurrKey(String methodName, String token) {
        return methodName + "-" + token + System.currentTimeMillis();
    }

    /**
     * @param currDataName 当前进度描述
     * @param ratio        当前进度
     * @param key          唯一标识
     */
    public static void running(String currDataName, double ratio, String key) {
        String result = getNumFormat().format(ratio);
        DataProgressVO progressVo = new DataProgressVO();
        progressVo.setCurrDataName(currDataName);
        progressVo.setRatio(result);

        progressVo.setCode(ProgressStatus.RUNNING.getCode());
        progressUtil.cacheUtil.put(CacheConstant.CACHE_PROGRESS_BAR, key, progressVo);
    }

    public static NumberFormat getNumFormat() {
        NumberFormat num = NumberFormat.getInstance();
        num.setMaximumFractionDigits(2);
        return num;
    }

    public static void success(String currDataName, String key, Object object) {
        String result = getNumFormat().format(100);
        DataProgressVO progressVo = new DataProgressVO();
        progressVo.setCurrDataName(currDataName);
        progressVo.setRatio(result);
        if (object != null) {
            progressVo.setData(object);
        }
        progressVo.setCode(ProgressStatus.SUCCESS.getCode());
        progressUtil.cacheUtil.put(CacheConstant.CACHE_PROGRESS_BAR, key, progressVo);
    }

    public static void fail(String currDataName, String key, Object object) {
        String result = getNumFormat().format(100);
        DataProgressVO progressVo = new DataProgressVO();
        progressVo.setCurrDataName(currDataName);
        progressVo.setRatio(result);
        if (object != null) {
            progressVo.setData(object);
        }
        progressVo.setCode(ProgressStatus.FAIL.getCode());
        progressUtil.cacheUtil.put(CacheConstant.CACHE_PROGRESS_BAR, key, progressVo);
    }
}
