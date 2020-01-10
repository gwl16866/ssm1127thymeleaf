package com.huayu.ssm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huayu.ssm.bean.Dept;
import com.huayu.ssm.mapper.DeptMapper;
import com.huayu.ssm.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableCaching
@CacheConfig(cacheNames = "dept")
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    @Cacheable(key = "'depts'")
    public List<Dept> select_Dept() {
        return deptMapper.select_Dept();
    }
}
