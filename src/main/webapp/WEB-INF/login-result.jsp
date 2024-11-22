<%--
  Created by IntelliJ IDEA.
  User: kdt
  Date: 2024-11-20
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <jsp:include page="include/style.jsp" />
  <title>Login Result</title>
</head>
<body>
  <h1><%= request.getAttribute("message")%></h1>
  <a href="${pageContext.request.contextPath}/login">go back</a>
</body>
</html>
