<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2019/11/28
  Time: 14:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="mypage" uri="http://qq/ww/ee.com" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/jjss/jquery-3.3.1.js"></script>
   <script type="text/javascript">

       function cha(){
           var num=$("#showNumSize").val();

           location.href="http://localhost:8080/test/queryAll.do?page=1&pageSize="+num;
       }

       function allchange(e){
           var some=document.getElementsByName("ids");
           //var some=$(".name").Array;
           if(e.checked){
               for(var i=0;i<some.length;i++){
                   some[i].checked=true;
               }
           }else{
               for(var i=0;i<some.length;i++){
                   some[i].checked=false;
               }
           }
       }

       function shan() {
           var yes=document.getElementsByName("ids");
           var flag=false;
           for(var i=0;i<yes.length;i++){
               if(yes[i].checked){
                   flag=true;
               }
           }
           if(flag==true){
               deletes();
           }else{
               alert("请选择你要删除的哐哐");
           }

       }


       function deletes(){
           //var somes=document.getElementsByName("ids");
           var array=new Array();
           $(":checkbox[name='ids']").each(function () {
               if(this.checked){
                   array.push($(this).val());
               }
           });
           location.href="http://localhost:8080/test/deleteSome.do?ids="+array+"";

       }

   </script>

</head>
<body>
<h2>ssm练习</h2>

<form action="${pageContext.request.contextPath}/test/queryAll.do" method="post">
    姓名：<input type="text" name="empName">
    性别：<select name="empSex"><option value="0">请选择</option><option value="1">男</option><option value="2">女</option></select>
    电话：<input type="text" name="phone">
    部门：<select name="deptNo">
    <option value="0">请选择</option>
            <c:forEach items="${deptList}" var="dept">
                <option value="${dept.deptNo}">${dept.deptName}</option>
            </c:forEach>
         </select>
         <input type="submit" value="模糊查询">
</form>


<a href="${pageContext.request.contextPath}/insert.jsp">添加</a>
<a href="javascript:void(0)" onclick="shan()">批量删除</a>
<table border="1" align="center" cellspacing="10">
    <tr>
        <td>全选<input type="checkbox" id="all" onclick="allchange(this)"></td>
        <td>编号</td> <td>姓名</td> <td>性别</td><td>电话</td><td>部门</td><td>操作</td></tr>


    <c:forEach items="${empList.records}" var="emp" varStatus="star">
        <tr>
            <td><input type="checkbox" name="ids" value="${emp.empNo}"></td>
            <td>${star.index+1}</td>
            <td>${emp.empName}</td>
            <td>${emp.empSex eq "1"?"男":"女"}</td>
            <td>${emp.phone}</td>
            <td>${emp.deptObject.deptName}</td>
            <td>
                <a href="${pageContext.request.contextPath}/test/selectEmpById.do?id=${emp.empNo}">修改</a>
                |
                <a href="${pageContext.request.contextPath}/test/delete.do?id=${emp.empNo}">删除</a>
            </td>
        </tr>
    </c:forEach>

</table>


    <mypage:mytaglib url="${pageContext.request.contextPath}/test/queryAll.do"
                     param="page"  currentPage="${empList.current}" totalPage="${empList.getPages()}"
                     myPageSize="pageSize" myPageSizeVal="${empList.size}"
                     param1="empName" param1Value="${emp.empName}"
                     param2="empSex" param2Value="${emp.empSex}"
                     param3="phone" param3Value="${emp.phone}"
                     param4="deptNo" param4Value="${emp.deptNo}"></mypage:mytaglib>
    当前是第${empList.getCurrent()}页，共${empList.getPages()}页
    <br>
    每页显示<select id="showNumSize" onchange="cha()">
            <option value="2" ${2 eq pageInfo.pageSize ? 'selected':''}>2</option>
            <option value="3" ${3 eq pageInfo.pageSize ? 'selected':''}>3</option>
            <option value="4" ${4 eq pageInfo.pageSize ? 'selected':''}>4</option>
            <option value="5" ${5 eq pageInfo.pageSize ? 'selected':''}>5</option>
           </select>条



</body>
</html>
