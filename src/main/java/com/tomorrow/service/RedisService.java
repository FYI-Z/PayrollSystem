package com.tomorrow.service;

import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
public interface RedisService {
    public Jedis getJedis();
    public int setString(String key,String value);
    public int flushExpire(String key, int redisExpireTime);
    public String getString(String key);
    public int delString(String key);
}
