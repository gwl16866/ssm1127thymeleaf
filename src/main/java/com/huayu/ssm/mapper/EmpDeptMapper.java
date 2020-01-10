package com.huayu.ssm.mapper;

import com.huayu.ssm.bean.Dept;
import com.huayu.ssm.bean.Emp;
import com.huayu.ssm.provider.SqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmpDeptMapper {



    /*
    * 查全部嵌套查询
    * */
   /* @Results({
      @Result(column = "empno",property = "empNo"),
      @Result(column = "empsex",property = "empSex"),
      @Result(column = "phone",property = "phone"),
      @Result(column = "deptno",property = "deptObject",one = @One(select ="select_Dept_By_Id"))
    })
    @Select("select * from emp")
    public List<Emp> select_All();

    @Select("select * from dept where deptno=#{value}")
    public Dept select_Dept_By_Id(Integer deptNo);*/

    /*
     * 查全部  动态sql
     * */
    @Results({
            @Result(column = "deptno",property = "deptObject.deptNo"),
            @Result(column = "deptname",property = "deptObject.deptName")
    })
    @SelectProvider(type = SqlProvider.class,method = "select_emp_like")
    public List<Emp> select_All(Emp emp);



    /*
    *
    * 删除
    * */
    @Delete("delete from emp where empno=#{value}")
    public void delete_By_Id(Integer id);

    /*
     *
     * 批量删除
     * */

    @DeleteProvider(type = SqlProvider.class,method = "delete_By_SomeId")
    public void delete_By_SomeId(String str);


    /*
    * 查询全部部门及id
    *
    * */
    @Select("select * from dept")
    public List<Dept> select_Dept();

    /*
    * 添加一个小人儿
    * */
    @Insert("insert into emp(empname,empsex,phone,deptno)values(#{empName},#{empSex},#{phone},#{deptNo})")
    public void insert_emp(Emp emp);

    /*
    *
    * 根据id查一个
    * */

    @Results({
            @Result(column = "empno",property = "empNo"),
            @Result(column = "empsex",property = "empSex"),
            @Result(column = "phone",property = "phone"),
            @Result(column = "deptno",property = "deptNo")
    })
    @Select("select * from emp where empno=#{value}")
    public Emp select_Emp_By_Id(Integer id);


    /*
    * 修改一个人信息
    * */
    @Update("update emp set empname=#{empName},empsex=#{empSex},phone=#{phone},deptno=#{deptNo} where empno=#{empNo}")
    public void update(Emp emp);



}
