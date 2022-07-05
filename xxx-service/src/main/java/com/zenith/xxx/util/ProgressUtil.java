package com.zenith.xxx.util;


import com.sjr.common.constant.ProgressStatus;
import com.sjr.common.entity.DataProgressVo;
import com.zenith.xxx.model.constant.CacheConstant;

import java.text.NumberFormat;

/**
 * 进度条工具类
 *
 * @author TMW
 * @since 2022/6/13 15:22
 */
public class ProgressUtil {
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
        DataProgressVo progressVo = new DataProgressVo();
        progressVo.setCurrDataName(currDataName);
        progressVo.setRatio(result);

        progressVo.setCode(ProgressStatus.RUNNING.getCode());
        CacheUtil.put(CacheConstant.CACHE_PROGRESS_BAR, key, progressVo);
    }

    public static NumberFormat getNumFormat() {
        NumberFormat num = NumberFormat.getInstance();
        num.setMaximumFractionDigits(2);
        return num;
    }

    public static void success(String currDataName, String key, Object object) {
        String result = getNumFormat().format(100);
        DataProgressVo progressVo = new DataProgressVo();
        progressVo.setCurrDataName(currDataName);
        progressVo.setRatio(result);
        if (object != null) {
            progressVo.setData(object);
        }
        progressVo.setCode(ProgressStatus.SUCCESS.getCode());
        CacheUtil.put(CacheConstant.CACHE_PROGRESS_BAR, key, progressVo);
    }

    public static void fail(String currDataName, String key, Object object) {
        String result = getNumFormat().format(100);
        DataProgressVo progressVo = new DataProgressVo();
        progressVo.setCurrDataName(currDataName);
        progressVo.setRatio(result);
        if (object != null) {
            progressVo.setData(object);
        }
        progressVo.setCode(ProgressStatus.FAIL.getCode());
        CacheUtil.put(CacheConstant.CACHE_PROGRESS_BAR, key, progressVo);
    }
}
