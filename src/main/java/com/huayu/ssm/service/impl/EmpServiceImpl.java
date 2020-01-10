package com.huayu.ssm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huayu.ssm.bean.Emp;
import com.huayu.ssm.mapper.EmpMapper;
import com.huayu.ssm.service.IEmpService;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@EnableCaching
@CacheConfig(cacheNames = "emp")
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements IEmpService {

    @Autowired
    private EmpMapper empMapper;



    /*查询全部*/
    public IPage<Emp> selectEmp( @Param("ip") Page ip,@Param("emp") Emp emp){
        IPage<Emp> list=  empMapper.select_All(ip,emp);
        return list;
    }
    /*查询全部*/
    @Override
    public List<Emp> selectEmp_export(@Param("emp") Emp emp){
        List<Emp> list=  empMapper.select_All_export(emp);
        return list;
    }


    /*根据id删除*/
    @CacheEvict(key = "'empId'+#id")
    public void delete_By_Id(Integer id){
        empMapper.deleteById(id);
    }

    /*批量删除*/
    public void delete_By_SomeId(String str){
        empMapper.delete_By_SomeId(str);
    }


    /*插入一条*/
    public void inser_Emp(Emp emp){
        empMapper.insert(emp);
    }

    /*根据id查一条*/
    @Cacheable(key = "'empId'+#id")
    public Emp select_Emp_By_Id(Integer id){
        return empMapper.select_Emp_By_id(id);
    }

    /*修改*/
    @CachePut(key = "'empId'+#emp.getEmpNo()")
    public Emp update(Emp emp) {
        int i=empMapper.updateById(emp);
        if(i==1){
            return emp;
        }else{
            return empMapper.selectById(emp.getEmpNo());
        }
    }

    /*导入excel*/
    @Override
    public List<Emp> importExcel(MultipartFile excelFile) {
        List<Emp> list=new ArrayList<>();
        try {
            //将文件转换为字节流文件
            InputStream in=excelFile.getInputStream();
            //工具类workbook读取文件
            Workbook workbook= WorkbookFactory.create(in);
            //获取到第一页sheet
            Sheet sheet=workbook.getSheetAt(0);
            //获取第一sheet页 有多少行
            int rowNum=sheet.getLastRowNum();
            //遍历行，从第二行开始，第1行不要
            for(int i=1;i<=rowNum;i++){
                //获取每一行
                Row row=sheet.getRow(i);
                //获取当前行有多少列
                int cellNum=row.getLastCellNum();
                //每一行就是一个emp对象
                Emp emp=new Emp();
                //遍历当前行的全部列
                for(int j=0;j<cellNum;j++){
                    //获取当前行的某一列对象
                    Cell cell=row.getCell(j);
                    if(j==0){//第一列
                        emp.setEmpName(cell.getStringCellValue());
                    }else if(j==1){//第2列
                        String sex=String.valueOf(cell.getNumericCellValue());
                        String realSex=sex.substring(0,sex.indexOf("."));
                        emp.setEmpSex(Integer.parseInt(realSex));
                    }else if(j==2){//第3列
                        String deptNo=String.valueOf(cell.getNumericCellValue());
                        String realDeptNo=deptNo.substring(0,deptNo.indexOf("."));
                        emp.setDeptNo(Integer.parseInt(realDeptNo));
                    }
                }

                list.add(emp);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /*导出Excel*/
    @Override
    public Workbook createWorkBook(List<Emp> list) {
        Workbook workbook=new HSSFWorkbook();
        //第一sheet页起名
        Sheet sheet=workbook.createSheet("员工信息");
        //第一行
        Row row=sheet.createRow(0);

        Cell cell=row.createCell(0);
        cell.setCellValue("姓名");
        Cell cell1=row.createCell(1);
        cell1.setCellValue("性别");
        Cell cell2=row.createCell(2);
        cell2.setCellValue("部门");

        for (int i = 0; i < list.size(); i++) {
            Row row1=sheet.createRow(i+1);
                 for(int j=0;j<3;j++){
                        if(j==0){
                            row1.createCell(j).setCellValue(list.get(i).getEmpName());
                        }else if(j==1){
                            row1.createCell(j).setCellValue(list.get(i).getEmpSex()==1 ? "男":"女");
                        }else if(j==2){
                            row1.createCell(j).setCellValue(list.get(i).getDeptObject().getDeptName());
                        }
                }

        }

        return workbook;
    }


}
