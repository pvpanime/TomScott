<%--
  Created by IntelliJ IDEA.
  User: kdt
  Date: 2024-11-20
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <jsp:include page="include/style.jsp" />
  <title>Acc Input</title>
</head>
<body>
  <%--  get two number inputs --%>
  <form action='${pageContext.request.contextPath}/acc/result' method='POST'>
    <fieldset>
      <legend>Numbers input</legend>
      <input type='number' name='op1' />
      <input type='number' name='op2' />
      <select name='op'>
        <option value='add'>+</option>
        <option value='sub'>-</option>
        <option value='mul'>*</option>
        <option value='div'>/</option>
      </select>
      <input type='submit' value="Accumulate" />
    </fieldset>
  </form>
</body>
</html>
