package com.huayu.ssm.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huayu.ssm.Data.LayUiData;
import com.huayu.ssm.Data.UploadData;
import com.huayu.ssm.bean.Dept;
import com.huayu.ssm.bean.Emp;
import com.huayu.ssm.service.IDeptService;
import com.huayu.ssm.service.IEmpService;
import io.swagger.annotations.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("test")
@Api(value = "员工管理")
public class EmpController {
    @Autowired
   private IEmpService empService;

    @Autowired
    private IDeptService deptService;

    /*查询全部并导出到excel*/
    @RequestMapping("/queryAll_ExportExcel.do")
    public void queryAll_ExportExcel(HttpServletResponse response,Emp emp,HttpSession session) throws Exception{
        response.setContentType("application/xls;charset=utf-8");
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
        String da=simpleDateFormat.format(date);
        response.setHeader("content-disposition", "attachment;filename="+da+".xls");
        List<Emp> list=empService.selectEmp_export(emp);
        Workbook workbook=empService.createWorkBook(list);
        workbook.write(response.getOutputStream());
    }


    //查询全部
    @RequestMapping(value = "/queryAll.do")
    @ResponseBody
    @ApiOperation(httpMethod = "post",value = "模糊查询学生信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "查询成功",response = Emp.class),
            @ApiResponse(code = 500,message = "查询错误")
    })
    public LayUiData queryAll(Model model,@ApiParam(required = false,name = "emp",value = "模糊查询学生信息") Emp emp,
    @ApiParam(required = true,name = "page",value = "第几页")@RequestParam(value = "page",required = true,defaultValue = "1") int page,
    @ApiParam(required = true,name = "limit",value = "一页放几条")@RequestParam(value = "limit",required = true,defaultValue = "3") int pageSize,
    HttpSession session)
    {
        Page<Emp> ip=new Page<Emp>(page,pageSize);
        IPage<Emp> empList=empService.selectEmp(ip,emp);

        LayUiData layUiData=new LayUiData();
        layUiData.setCode(0);
        layUiData.setMsg("");
        layUiData.setCount(Integer.parseInt(String.valueOf(empList.getTotal())));
        layUiData.setData(empList.getRecords());
        System.out.println(empList.getRecords());
        /*导出所以查全部*/
        List<Emp> list=empService.selectEmp_export(emp);
        session.setAttribute("list",list);
       return layUiData;
    }

    //删除
    @RequestMapping(value = "/delete.do")
    @ResponseBody
    @ApiOperation(httpMethod = "post",value = "根据id删除学生信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "删除成功",response = String.class),
            @ApiResponse(code = 500,message = "删除失败")
    })
    public String delete(@ApiParam(name = "id",required = true,value = "学生的id") Integer id){
        String i="0";
        try{
            empService.delete_By_Id(id);
        }catch (Exception e){
            i="1";
        }
        return i;
    }


    //批量删除
    @RequestMapping("/deleteSome.do")
    @ResponseBody
    public String deleteSome(@RequestParam("ids") String str){
        //empService.delete_By_SomeId(str);
        String i="0";
        try{
            empService.removeByIds(Arrays.asList(str.split(",")));
        }catch (Exception e){
            i="1";
        }
        return i;
    }

    //异步查询部门
    @RequestMapping("/selectDept.do")
    @ResponseBody
    public List<Dept> selectDept(){
       return deptService.select_Dept();
    }



    //根据 id 查信息进行展示 以 修改
    @RequestMapping("/selectEmpById.do")
    public String selectEmpById(@RequestParam("id") Integer id,Model model){
        model.addAttribute("emp",empService.select_Emp_By_Id(id));
        model.addAttribute("deptList",deptService.select_Dept());
        return "toUpdate";
    }

    //修改
    @RequestMapping("/update.do")
    @ResponseBody
    public String update(Emp emp){
        String i="0";
        try{
            empService.update(emp);
        }catch (Exception e){
            i="1";
        }
        return i;
    }

    //添加一个员工
    @RequestMapping("/insert.do")
    @ResponseBody
    public String insert_emp(Emp emp){
        String i="0";
        try{
            empService.inser_Emp(emp);
        }catch (Exception e){
            i="1";
        }
        return i;
    }


    //上传图片
    @RequestMapping("/upLoad.do")
    @ResponseBody
    public UploadData upLoad(@RequestParam("file") MultipartFile pictureFile, HttpServletRequest httpServletRequest){
        UploadData uploadData=new UploadData();
        uploadData.setCode(0);
        try {
            // 图片上传
// 设置图片名称，不能重复，可以使用uuid
            String picName = UUID.randomUUID().toString();
// 获取文件名
            String oriName = pictureFile.getOriginalFilename();
// 获取图片后缀
            String extName = oriName.substring(oriName.lastIndexOf("."));
// 开始上传
            String path=httpServletRequest.getSession().getServletContext().getRealPath("/");
            //String subPath=path.substring(0,path.indexOf("ssm"));
            pictureFile.transferTo(new File(path +"/image/"+ picName + extName));
            uploadData.setFileName(picName+extName);
        } catch (Exception e) {
            uploadData.setCode(1);
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return uploadData;
    }

    //导入Excel
    @RequestMapping("/importExcel.do")
    @ResponseBody
    public String importExcel(@RequestParam("file") MultipartFile excelFile){
        List<Emp> SomeEmp=empService.importExcel(excelFile);
        String i="0";
        try{
            empService.saveBatch(SomeEmp);
        }catch (Exception e){
            i="1";
        }
        return i;

    }

    /*导出Excel*/
    @RequestMapping("/exportExcel.do")
    public void exportExcel(HttpServletResponse response,Emp emp,HttpSession session) throws  Exception{
        response.setContentType("application/xls;charset=utf-8");
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
        String da=simpleDateFormat.format(date);
        response.setHeader("content-disposition", "attachment;filename="+da+".xls");
        List<Emp> list=(List<Emp>)session.getAttribute("list");
        Workbook workbook=empService.createWorkBook(list);
        workbook.write(response.getOutputStream());

    }



}
