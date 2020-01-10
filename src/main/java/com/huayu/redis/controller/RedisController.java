package com.huayu.redis.controller;

import com.huayu.redis.service.IRedisService;
import com.huayu.ssm.bean.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class RedisController {
    @Autowired
    private IRedisService service;

    @RequestMapping("/set.do")
    @ResponseBody
    public void set(String k,String v){

        Emp emp=new Emp();
        emp.setEmpName("高伟立");
        service.setStr(k,emp);
    }

    @RequestMapping("/getHash.do")
    @ResponseBody
    public Object getHash(String name){
       return  service.getHash("emp");
    }

    @RequestMapping("/setZSet.do")
    @ResponseBody
    public void setZSet(String n,String v,Integer s){
        double d=s;
        ZSetOperations.TypedTuple<String> tuple2 = new DefaultTypedTuple<>(v,d);
        service.setZSet(n,tuple2);
    }

    @RequestMapping("/setSet.do")
    @ResponseBody
    public void setSet(String n,String v){
        service.setSet(n,v);
    }



}
