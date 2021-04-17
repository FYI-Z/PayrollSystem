package com.tomorrow.service.imp;

import com.tomorrow.service.RedisService;
import com.tomorrow.util.Constant;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
public class RedisServiceImp implements RedisService {

    @Override
    public Jedis getJedis() {
        JedisPool jedisPool = null;
        Jedis jedis = null;
        try{
            if(jedisPool==null){
                try{
                    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
                    jedisPoolConfig.setMaxTotal(Constant.REDIS_MAX_ACTIVE); //最大连接数
                    jedisPoolConfig.setMaxIdle(Constant.REDIS_MAX_IDLE); //最大空闲连接数
                    jedisPoolConfig.setMaxWaitMillis(Constant.REDIS_MAX_WAIT);//获取可用连接的最大等待时间
                    jedisPool = new JedisPool(jedisPoolConfig,Constant.REDIS_HOST,Constant.REDIS_PORT);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            jedis = jedisPool.getResource();
            return jedis;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("初始化jedis实例失败！");
            return null;
        }
    }

    @Override
    public int setString(String key, String value) {
        if(key==null)
            return 0;
        Jedis jedis = getJedis();
        jedis.set(key,value);
        jedis.expire(key,Constant.REDIS_EXPIRE_TIME);
        jedis.close();
        return 1;
    }

    @Override
    public int flushExpire(String key, int redisExpireTime) {
        if(key==null)
            return 0;
        Jedis jedis = getJedis();
        jedis.expire(key,Constant.REDIS_EXPIRE_TIME);
        jedis.close();
        return 1;
    }

    @Override
    public String getString(String key) {
        if(key==null)
            return null;
        try {
            Jedis jedis = getJedis();
            String value = jedis.get(key);
            jedis.close();
            return value;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int delString(String key) {
        if(key==null)
            return 0;
        Jedis jedis = getJedis();
        jedis.del(key);
        jedis.close();
        return 1;
    }
}
