package com.zenith.xxx.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.sjr.common.entity.UserTicket;
import com.zenith.xxx.constant.CacheConstant;
import lombok.extern.slf4j.Slf4j;

/**
 * token 生成校验工具
 *
 * @author TMW
 * @date 2021/3/4 22:04
 */
@Slf4j
public class TokenUtil {
    private static final String SEPARATOR = "@";

    /***
     * 创建token
     * */
    public static String createToken(String userId, long createTime) {
        String data = userId + SEPARATOR + createTime;
        return CacheConstant.CACHE_TOKEN + DigestUtil.md5Hex(data);
    }

    /***
     * 校验token 是否合法
     * */
    public static boolean verify(String userId, String token) {
        try {
            UserTicket ticket = CacheUtil.getUserCache(token);
            if (ticket == null) {
                return false;
            }
            token = token.replaceAll(CacheConstant.CACHE_TOKEN, "");
            String data = userId + SEPARATOR + ticket.getCreateTime();
            return StrUtil.equals(token, DigestUtil.md5Hex(data));
        } catch (Exception e) {
            log.error("校验token异常：", e);
            return false;
        }
    }
}
