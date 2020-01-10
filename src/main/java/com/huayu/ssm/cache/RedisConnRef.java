package com.huayu.ssm.cache;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

public class RedisConnRef {
    private JedisConnectionFactory jedisConnectionFactory;

    public void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
        RedisCache.setJedisConnectionFactory(jedisConnectionFactory);
    }
}
