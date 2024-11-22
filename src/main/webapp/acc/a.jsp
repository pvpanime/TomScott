<%--
  Created by IntelliJ IDEA.
  User: kdt
  Date: 2024-11-20
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
  <form action="${pageContext.request.contextPath}/acc/result" method="post">
    <input name="op1" type="number" value="0" />
    <input name="op2" type="number" value="0" />
    <%--  select one of +, - , *, /  --%>
    <select name="operator">
      <option value="add">+</option>
      <option value="sub">-</option>
      <option value="mul">*</option>
      <option value="div">/</option>
    </select>
    <input type="submit" />
  </form>
</body>
</html>
