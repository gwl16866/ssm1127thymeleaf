package com.huayu.redis.dao;

import com.huayu.ssm.bean.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class RedisDao {
    @Autowired
    private RedisTemplate template;

    public void setStr(String k, Emp v){
        template.opsForValue().set(k,v);
    }

    public void setList(String k, String v){
        template.opsForList().rightPush(k,v);
    }

    public void setSet(String k, String v){
        template.opsForSet().add(k,v);
    }

    public void setZSet(String k, ZSetOperations.TypedTuple v){
        Set<ZSetOperations.TypedTuple<String>> tuples = new HashSet<>();
        //ZSetOperations.TypedTuple<String> tuple2 = new DefaultTypedTuple<>("aaa", 5200d);
        tuples.add(v);
        template.opsForZSet().add(k,tuples);
    }

    public Object queryHash(String name){
        return template.opsForValue().get(name);
    }
}
