package com.huayu.redis.service;

import com.huayu.ssm.bean.Emp;
import org.springframework.data.redis.core.ZSetOperations;

public interface IRedisService {

    public void setStr(String k, Emp v);
    public Object getHash(String name);
    public void setSet(String k, String v);
    public void setZSet(String k, ZSetOperations.TypedTuple v);


}
