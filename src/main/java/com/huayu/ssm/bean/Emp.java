package com.huayu.ssm.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@TableName("emp")
public class Emp implements Serializable {

    @TableId(value = "empno",type = IdType.AUTO)
    private int empNo;

    @TableField(value = "empname")
    private String empName;

    @TableField(value = "empsex")
    private int empSex;

    @TableField(value = "deptno")
    private int deptNo;

    @TableField(value = "phone")
    private String phone;

    @TableField(value = "img")
    private String img;

    @TableField(value = "jobtime")
    private Date jobtime;


    @TableField(exist = false)
    private Dept deptObject;

    @TableField(exist = false)
    private List<Emp> empList;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<Emp> getEmpList() {
        return empList;
    }

    public void setEmpList(List<Emp> empList) {
        this.empList = empList;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public int getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(int deptNo) {
        this.deptNo = deptNo;
    }

    public Dept getDeptObject() {
        return deptObject;
    }

    public void setDeptObject(Dept deptObject) {
        this.deptObject = deptObject;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getEmpSex() {
        return empSex;
    }

    public void setEmpSex(int empSex) {
        this.empSex = empSex;
    }

    public Date getJobtime() {
        return jobtime;
    }

    public void setJobtime(Date jobtime) {
        this.jobtime = jobtime;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "empNo=" + empNo +
                ", empName='" + empName + '\'' +
                ", empSex=" + empSex +
                ", deptNo=" + deptNo +
                ", phone='" + phone + '\'' +
                ", img='" + img + '\'' +
                ", date=" + jobtime +
                ", deptObject=" + deptObject +
                ", empList=" + empList +
                '}';
    }
}
