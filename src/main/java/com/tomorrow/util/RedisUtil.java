package com.tomorrow.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    //    添加key-value键值对
    public void setKey(String key, String value){
        redisTemplate.boundValueOps(key).set(value);
    }

    //    删除key
    public void deleteKey(String key){
        redisTemplate.delete(key);
    }

    //    删除多个key
    public void deleteKeys (String ...keys){
        redisTemplate.delete(keys);
    }

    //    指定key的失效时间
    public void expire(String key,long time){
        redisTemplate.expire(key,time, TimeUnit.MINUTES);
    }

    //    根据key获取过期时间
    public long getExpire(String key){
        Long expire = redisTemplate.getExpire(key);
        return expire;
    }

    //    判断key是否存在
    public boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }

}
