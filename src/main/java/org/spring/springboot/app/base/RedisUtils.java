package org.spring.springboot.app.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {
    @Value("${spring.redis.key-prefix}")
    private String prefix;

    // key生成器,redis生成key必须用
    @Autowired
    private RedisUtils redisUtils;

    // 序列化对象redis处理方法
    @Autowired
    private RedisTemplate redisTemplate;

    // 字符串redis处理方法
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public <T> void set(String key, T value, long times, TimeUnit timeUtnit) {
        ValueOperations<String, T> operations = redisTemplate.opsForValue();
        operations.set(getKey(key), value, times, timeUtnit);
    }

    public <T> T get(String key) {
        if (redisTemplate.hasKey(getKey(key))) {
            ValueOperations operations = redisTemplate.opsForValue();
            return (T) operations.get(getKey(key));
        }
        return null;
    }

    public void setString(String key, String value, long times, TimeUnit timeUtnit) {
        ValueOperations operations = stringRedisTemplate.opsForValue();
        operations.set(getKey(key), value, times, timeUtnit);
    }


    public String getString(String key) {
        if (stringRedisTemplate.hasKey(getKey(key))) {
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            return operations.get(getKey(key));
        }
        return null;
    }

    public void delete(String key) {
        redisTemplate.delete(getKey(key));
    }

    public boolean hasKey(String key) {
        return redisTemplate.hasKey(getKey(key));
    }

    public boolean hasStringKey(String key) {
        return stringRedisTemplate.hasKey(this.getKey(key));
    }

    public void deleteString(String key) {
        stringRedisTemplate.delete(getKey(key));
    }

    private String getKey(String key) {
        return prefix + "_" + key;
    }
}
