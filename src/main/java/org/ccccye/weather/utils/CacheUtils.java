package org.ccccye.weather.utils;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.util.Strings;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CacheUtils {
    private final static Log logger = LogFactory.getLog(CacheUtils.class);

    //缓存有效时间
    private static int duration = 12;

    private static LoadingCache<String, String> localCache = CacheBuilder.newBuilder()
            .initialCapacity(1000)  //初始容量
            .maximumSize(10000)
            .expireAfterAccess(duration, TimeUnit.HOURS)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    return Strings.EMPTY;
                }
            });

    /**
     * 设置缓存
     * @param key
     * @param value
     */
    public static void setKey(String key, String value) {

        localCache.put(key, value);
    }

    /**
     * 读取缓存
     * @param key
     * @return
     */
    public static String getKey(String key) {
        String value = null;
        try {
            value = localCache.get(key);
            if (Strings.isBlank(value)){
                value = null;
            }
        } catch (ExecutionException e) {
            logger.error("localcache get error");
        }
        return value;
    }
}
