package org.ccccye.weather.api.source.redis;

import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class CustomizeRedisCache extends RedisCache {

    private RedisCacheWriter redisCacheWriter;
    private RedisCacheConfiguration configuration;
    //校验规则：获取时间
    String REGEX_STR = ".*\\#\\d+$";

    private static final String Splitter="#";
    /**
     * Create new {@link RedisCache}.
     *
     * @param name        must not be {@literal null}.
     * @param cacheWriter must not be {@literal null}.
     * @param cacheConfig must not be {@literal null}.
     */
    protected CustomizeRedisCache(String name, RedisCacheWriter cacheWriter, RedisCacheConfiguration cacheConfig) {
        super(name, cacheWriter, cacheConfig);
        redisCacheWriter =cacheWriter;
        configuration =cacheConfig;
    }


    /**
     * 重写cache put 逻辑，引入自定义TTL 实现
     * 实现逻辑:
     * 1.通过获取@Cacheable 中的value ,然后根据约定好的特殊字符进行分割
     * 2.从分割结果集中获取设置的TTL 时间并将value 中的，然后给当前缓存设置TTL
     *
     * @param key
     * @param value
     */
    @Override
    public void put(Object key, Object value) {


        String name = super.getName();
        System.out.println("未命中缓存#name:"+name+"#key:"+key.toString());

        //是否按照指定的格式
        if (Pattern.matches(REGEX_STR, name)) {
            List<String> keyList = Arrays.asList(name.split(Splitter));
            //获取键值
            String finalName = keyList.get(0);
            //获取TTL 执行时间
            Long ttl = Long.valueOf(keyList.get(1));

            //获取缓存value
            Object cacheValue = preProcessCacheValue(value);
            //获取value 为null 时，抛出异常
            if (!isAllowNullValues() && cacheValue == null) {
                throw new IllegalArgumentException(String.format(
                        "Cache '%s' does not allow 'null' values. Avoid storing null via '@Cacheable(unless=\"#result == null\")' or configure RedisCache to allow 'null' via RedisCacheConfiguration.",
                        name));
            }
            //插入时添加时间
            redisCacheWriter.put(finalName, serializeCacheKey(createCacheKey(key)), serializeCacheValue(cacheValue), Duration.ofSeconds(ttl));
        } else {
            //原来逻辑处理
            super.put(key, value);
        }
    }

    /**
     *@描述  现有key 值格式为  key#ttl ;改方法将key 值后边的#ttl 去掉 ；例如test# 10；改方法处理后为test
     *@参数
     *@返回值
     *@创建人  zj
     *@创建时间  2020/4/8
     */
    protected String createCacheKey(Object key) {
        String convertedKey = convertKey(key);
        if (!configuration.usePrefix()) {
            return convertedKey;
        }
        return prefixCacheKey(convertedKey);
    }


    private String prefixCacheKey(String key) {
        String name = super.getName();
        if (Pattern.matches(REGEX_STR, name)) {
            List<String> keyList = Arrays.asList(name.split(Splitter));
            String finalName = keyList.get(0);
            return configuration.getKeyPrefixFor(finalName) + key;
        }
        // allow contextual cache names by computing the key prefix on every call.
        return configuration.getKeyPrefixFor(name) + key;
    }

}
