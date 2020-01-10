package com.huayu.ssm.test;

import com.huayu.ssm.bean.Emp;
import com.huayu.ssm.mapper.EmpMapper;
import com.huayu.ssm.service.IEmpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml","classpath:springmvc.xml"})
public class TestSsm {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private IEmpService iEmpService;

    @Test
    public void test1(){

        Emp emp1=new Emp();
        emp1.setEmpName("第一");
        emp1.setDeptNo(5);
        Emp emp2=new Emp();
        emp2.setEmpName("第2");
        emp2.setDeptNo(6);
        List<Emp> list=new ArrayList<>();
        list.add(emp1);
        list.add(emp2);
        iEmpService.saveOrUpdateBatch(list);



    }




}
