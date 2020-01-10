package com.huayu.ssm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huayu.ssm.bean.Emp;
import com.huayu.ssm.provider.SqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
//@CacheNamespace(implementation= RedisCache.class)
public interface EmpMapper extends BaseMapper<Emp> {

    /*
     * 查全部  动态sql
     * */
    @Results({
            @Result(column = "deptno",property = "deptObject.deptNo"),
            @Result(column = "deptname",property = "deptObject.deptName")
    })

    @SelectProvider(type = SqlProvider.class,method = "select_emp_like")
    public IPage<Emp> select_All(@Param("ip") Page<Emp> ip,@Param("emp") Emp emp);

    @Results({
            @Result(column = "deptno",property = "deptObject.deptNo"),
            @Result(column = "deptname",property = "deptObject.deptName")
    })
    @SelectProvider(type = SqlProvider.class,method = "select_emp_export")
    public List<Emp> select_All_export(@Param("emp") Emp emp);


    /*
     *
     * 批量删除
     * */

    @DeleteProvider(type = SqlProvider.class,method = "delete_By_SomeId")
    public void delete_By_SomeId(String str);



    @Select("select * from emp where empno=${value}")
    public Emp select_Emp_By_id(Integer id);


}
