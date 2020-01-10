<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2019/11/29
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/test/update.do" method="post">

    <input type="hidden" name="empNo" value="${emp.empNo}">
    姓名：<input type="text" name="empName" value="${emp.empName}">

    性别：<select name="empSex">
    <option value="1" ${emp.empSex eq '1'? 'selected':''}>男</option>
    <option value="2" ${emp.empSex eq '2'? 'selected':''}>女</option>
</select>

    电话：<input type="text" name="phone" value="${emp.phone}">

    部门：
    <select id="dept" name="deptNo">
      <c:forEach items="${deptList}" var="dept">
          <option ${dept.deptNo eq emp.deptNo ? "selected":""} value="${dept.deptNo}">${dept.deptName}</option>
      </c:forEach>

    </select>
    <input type="submit" value="修改">
</form>










</body>
</html>
