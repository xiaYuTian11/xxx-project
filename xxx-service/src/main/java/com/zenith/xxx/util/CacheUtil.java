// package com.zenith.xxx.util;
//
// import cn.hutool.system.UserInfo;
// import com.efficient.common.entity.UserTicket;
// import com.zenith.xxx.model.constant.CacheConstant;
// import net.sf.ehcache.Cache;
// import net.sf.ehcache.CacheManager;
// import net.sf.ehcache.Element;
// import org.springframework.cache.ehcache.EhCacheCacheManager;
//
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Objects;
//
// /**
//  * 缓存相关工具类
//  *
//  * @author TMW
//  * @date 2021/3/4 21:19
//  */
// public class CacheUtil {
//
//     private static final CacheManager cacheManager = ApplicationContextUtils.APPLICATION_CONTEXT.getBean(EhCacheCacheManager.class).getCacheManager();
//
//     /**
//      * 获取用户信息
//      *
//      * @param token
//      * @return
//      */
//     public static UserTicket getUserCache(String token) {
//         Element element = cacheManager.getCache(CacheConstant.CACHE_TOKEN).get(token);
//         if (element == null) {
//             return null;
//         }
//         return (UserTicket) element.getObjectValue();
//     }
//
//     /**
//      * 获取在线用户
//      *
//      * @return 在线用户集合
//      */
//     public static List<UserTicket> getAllUserCache() {
//         List keys = cacheManager.getCache(CacheConstant.CACHE_TOKEN).getKeys();
//         List<UserTicket> userInfoList = new ArrayList<>();
//         for (Object token : keys) {
//             userInfoList.add(getUserCache(String.valueOf(token)));
//         }
//         return userInfoList;
//     }
//
//     /**
//      * 刷新用户缓存
//      *
//      * @param token
//      */
//     public static void refreshUserCache(String token) {
//         cacheManager.getCache(CacheConstant.CACHE_TOKEN).get(token).setTimeToIdle(60 * 50);
//     }
//
//     /**
//      * 移除缓存中的用户token缓存
//      */
//     public static void removeUserCache(String token) {
//         cacheManager.getCache(CacheConstant.CACHE_TOKEN).remove(token);
//     }
//
//     /**
//      * 保存用户信息
//      *
//      * @param token
//      * @param userTicket
//      */
//     public static void putUserCache(String token, UserInfo userTicket) {
//         cacheManager.getCache(CacheConstant.CACHE_TOKEN).put(new Element(token, userTicket));
//     }
//
//     /**
//      * 保存缓存
//      */
//     public static void put(String cacheKey, String key, Object obj) {
//         cacheManager.getCache(cacheKey).put(new Element(key, obj));
//     }
//
//     /**
//      * 获取缓存
//      */
//     public static <T> T get(String cacheKey, String key) {
//         final Element element = getCache(cacheKey).get(key);
//         if (Objects.isNull(element)) {
//             return null;
//         }
//         return (T) element.getObjectValue();
//     }
//
//     private static Cache getCache(String cacheKey) {
//         Cache cache = cacheManager.getCache(cacheKey);
//         if (cache == null) {
//             cacheManager.addCacheIfAbsent(cacheKey);
//         }
//         return cacheManager.getCache(cacheKey);
//     }
//
//     /**
//      * 移除缓存
//      */
//     public static void removeCache(String cacheKey, String key) {
//         final Cache cache = cacheManager.getCache(cacheKey);
//         if (Objects.nonNull(cache)) {
//             cache.remove(key);
//         }
//     }
//
//     /**
//      * 移除缓存
//      */
//     public static void removeDictCache(String type) {
//         removeCache(CacheConstant.CACHE_DICT, type);
//     }
// }
