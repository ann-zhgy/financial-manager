package cn.ann.financial.manager.commons.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-31:21:44
 */
@Component
public class RedisUtils {
    @Resource(name = "customerRedisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 通过 key 获取值
     * @param key key
     * @return 值 {@link Object}
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 保存 键值对 到 redis 数据库中
     * @param key key
     * @param value value
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 保存 键值对 到 redis 数据库中，并设置存在时间
     * @param key key
     * @param value value
     * @param timeout 存在时间，单位 秒
     */
    public void set(String key, Object value, Long timeout) {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 保存 键值对 到 redis 数据库中，并设置过期时间
     * @param key key
     * @param value value
     * @param date 过期时间 {@link Date}
     */
    public void set(String key, Object value, Date date) {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expireAt(key, date);
    }

    /**
     * 保存 键值对 到 redis 数据库中，并设置保存时间，自己设置时间单位
     * @param key key
     * @param value value
     * @param timeout 存储时间
     * @param unit 存储时间单位
     */
    public void set(String key, Object value, Long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 删除 key
     * @param key key
     * @return {@link Boolean}
     */
    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 更新 key 的存在时间，默认为秒
     * @param key key
     * @param timeout 存在时间
     * @return {@link Boolean}
     */
    public Boolean expire(String key, Long timeout) {
        return redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 更新 key 的存在时间
     * @param key key
     * @param timeout 存在时间
     * @param unit 时间单位
     * @return {@link Boolean}
     */
    public Boolean expire(String key, Long timeout, TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }
}
