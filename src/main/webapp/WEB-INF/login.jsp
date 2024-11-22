<%--
  Created by IntelliJ IDEA.
  User: kdt
  Date: 2024-11-20
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <jsp:include page="include/style.jsp" />
  <title>Title</title>
</head>
<body>
  <form action="${pageContext.request.contextPath}/doLogin" method="post">
    <fieldset>
      <legend>Login</legend>
      <label>email: <input type="email" name="email" required /></label>
      <label>Password: <input type="password" name="password" required /></label>
      <input type="submit" value="Login" />
    </fieldset>
  </form>
</body>
</html>
