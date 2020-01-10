package com.huayu.ssm.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.huayu.ssm.bean.Dept;
import com.huayu.ssm.bean.Emp;
import com.huayu.ssm.mapper.EmpDeptMapper;
import com.huayu.ssm.mapper.EmpMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpService {
    @Autowired
    private EmpDeptMapper mapper;

    @Autowired
    private EmpMapper empMapper;

    /*查询全部*/
    public List<Emp> selectEmp(Emp emp, @Param("page") int page,@Param("pageSize") int pageSize){

        Page pages= PageHelper.startPage(page,pageSize);
        List<Emp> list=  mapper.select_All(emp);
        return list;
    }

    /*根据id删除*/
    public void delete_By_Id(Integer id){
        empMapper.deleteById(id);
    }

    /*批量删除*/
    public void delete_By_SomeId(String str){
        mapper.delete_By_SomeId(str);
    }

    /*查询全部dept部门*/
    public List<Dept>  select_Dept(){
        return mapper.select_Dept();
    }

    /*插入一条*/
    public void inser_Emp(Emp emp){
        empMapper.insert(emp);
    }

    /*根据id查一条*/
    public Emp select_Emp_By_Id(Integer id){
        return empMapper.selectById(id);
    }

    /*修改*/
    public void update(Emp emp) {
        mapper.update(emp);
    }
}
