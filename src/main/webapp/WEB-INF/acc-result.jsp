<%--
  Created by IntelliJ IDEA.
  User: kdt
  Date: 2024-11-20
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <jsp:include page="include/style.jsp" />
  <title>Here's the result!</title>
</head>
<body>
  <h1>Here's the result!</h1>
  <div><%= request.getAttribute("result") %></div>
</body>
</html>
