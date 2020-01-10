<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2019/11/29
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加</title>

    <script src="${pageContext.request.contextPath}/jjss/jquery-3.3.1.js"></script>

    <script type="text/javascript">
        $(function () {
            $.ajax({
                type:"post",
                url:"/test/selectDept.do",
                contentType:"application/json",
                success:function (data) {
                    var str="<option value='-1'>请选择</option>";
                    for (var i=0;i<data.length;i++){
                        str+="<option value='"+data[i].deptNo+"'>"+data[i].deptName+"</option>";
                    }
                    $("#dept").html(str);

                }


            });
        });

    </script>

</head>

<body>
<h1>添加员工</h1>

<form action="${pageContext.request.contextPath}/test/insert.do" method="post">

    姓名：<input type="text" name="empName">

    性别：<select name="empSex">
    <option value="1">男</option>
    <option value="2">女</option>
         </select>

    电话：<input type="text" name="phone">

    部门：
    <select id="dept" name="deptNo">

    </select>
<input type="submit" value="添加">
</form>

<c:forEach items="${errors}" var="error">
    ${error.defaultMessage}
</c:forEach>





</body>
</html>
