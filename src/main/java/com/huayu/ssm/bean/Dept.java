package com.huayu.ssm.bean;

import java.io.Serializable;
import java.util.List;

public class Dept implements Serializable {
    private int deptNo;
    private String deptName;
    private List<Emp> listEmp;

    public List<Emp> getListEmp() {
        return listEmp;
    }

    public void setListEmp(List<Emp> listEmp) {
        this.listEmp = listEmp;
    }

    public int getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(int deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "deptNo=" + deptNo +
                ", deptName='" + deptName + '\'' +
                ", listEmp=" + listEmp +
                '}';
    }
}
