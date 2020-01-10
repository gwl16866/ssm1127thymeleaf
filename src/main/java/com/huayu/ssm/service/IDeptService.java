package com.huayu.ssm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huayu.ssm.bean.Dept;

import java.util.List;

public interface IDeptService extends IService<Dept> {

    /*查询全部dept部门*/
    public List<Dept>  select_Dept();

}
