package com.huayu.ssm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huayu.ssm.bean.Emp;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IEmpService extends IService<Emp> {
    public IPage<Emp> selectEmp(@Param("ip") Page ip,@Param("emp") Emp emp);
    /*根据id删除*/
    public void delete_By_Id(Integer id);

    /*批量删除*/
    public void delete_By_SomeId(String str);


    /*插入一条*/
    public void inser_Emp(Emp emp);

    /*根据id查一条*/
    public Emp select_Emp_By_Id(Integer id);

    /*修改*/
    public Emp update(Emp emp);

    /*导入excel*/
    public List<Emp> importExcel(MultipartFile file);

    /*创建workbook*/
    public Workbook createWorkBook(List<Emp> list);

    public List<Emp> selectEmp_export(@Param("emp") Emp emp);

}
