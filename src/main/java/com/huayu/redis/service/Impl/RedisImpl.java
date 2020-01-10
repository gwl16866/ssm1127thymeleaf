package com.huayu.redis.service.Impl;

import com.huayu.redis.dao.RedisDao;
import com.huayu.redis.service.IRedisService;
import com.huayu.ssm.bean.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisImpl implements IRedisService {
    @Autowired
    private RedisDao dao;


    @Override
    public void setStr(String k, Emp v) {
        dao.setStr(k,v);
    }

    @Override
    public Object getHash(String name) {
        return dao.queryHash(name);
    }

    @Override
    public void setSet(String k, String v) {
        dao.setSet(k,v);
    }

    @Override
    public void setZSet(String k, ZSetOperations.TypedTuple v) {
        dao.setZSet(k,v);
    }




}
