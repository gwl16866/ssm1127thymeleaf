package com.huayu.ssm.provider;

import com.huayu.ssm.bean.Emp;
import com.mysql.cj.core.util.StringUtils;
import org.apache.ibatis.annotations.Param;

public class SqlProvider {

    /*
    *
    * 模糊查询   拼 sql语句
    *
    * */
    public String select_emp_like(@Param("emp") Emp emp) {

        StringBuffer buffer = new StringBuffer("select * from emp e,dept d where e.deptno=d.deptno ");
        if (!StringUtils.isNullOrEmpty(emp.getEmpName())) {
            buffer.append(" and e.empname like '%" + emp.getEmpName() + "%'");
        }
        if (emp.getEmpSex() !=0) {
            buffer.append(" and e.empsex="+emp.getEmpSex()+"");
        }
        if (!StringUtils.isNullOrEmpty(emp.getPhone())) {
            buffer.append(" and e.phone like '%" +emp.getPhone() + "%'");
        }
        if(emp.getDeptNo()!=0){
            buffer.append(" and e.deptno = " +emp.getDeptNo() + "");
        }

        return buffer.toString();
    }


    public String delete_By_SomeId(String str) {
        StringBuffer buffer = new StringBuffer("delete from emp where empno in ");
        buffer.append(" ("+str+")");
        return buffer.toString();
    }

    public String select_emp_export(@Param("emp") Emp emp) {
        return select_emp_like(emp);
    }
}
