package com.huayu.ssm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huayu.ssm.bean.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
//@CacheNamespace(implementation= RedisCache.class)
public interface DeptMapper extends BaseMapper<Dept> {
    /*
     * 查询全部部门及id
     *
     * */
    @Select("select * from dept")
    public List<Dept> select_Dept();
}
