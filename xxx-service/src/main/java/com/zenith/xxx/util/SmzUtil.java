package com.zenith.xxx.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

import java.util.Date;

/**
 * @author TMW
 * @since 2022/6/23 9:44
 */
public class SmzUtil {
    /**
     * 获取查询区划代码
     *
     * @param geocode
     * @param selectSub
     * @return
     */
    public static String getDepCode(String geocode, boolean selectSub) {
        if (StrUtil.isBlank(geocode)) {
            return geocode;
        }
        String result = geocode;
        if (selectSub) {
            if (StrUtil.endWith(geocode, "0000")) {
                result = geocode.substring(0, geocode.length() - 4);
            } else if (StrUtil.endWith(geocode, "00")) {
                result = geocode.substring(0, geocode.length() - 2);
            }
        }
        return result;
    }

    /**
     * 获取查询区划代码
     *
     * @param geocode
     * @return
     */
    public static String getSelectSubDepCode(String geocode) {
        if (StrUtil.isBlank(geocode)) {
            return geocode;
        }
        String result = geocode;

        if (StrUtil.endWith(geocode, "0000")) {
            result = geocode.substring(0, geocode.length() - 4);
        } else if (StrUtil.endWith(geocode, "00")) {
            result = geocode.substring(0, geocode.length() - 2);
        }

        return result;
    }

    public static String getNowStr() {
        return DateUtil.format(new Date(), "yyyyMMddHHmmss");
    }

    public static String getDateStr(Date date) {
        return DateUtil.format(date, "yyyyMMddHHmmss");
    }
}
