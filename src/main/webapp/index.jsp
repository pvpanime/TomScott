<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %></h1>
<h1><%= "Hello World!" %></h1>
<h1><%= "Hello World!" %></h1>
<h1><%= "Hello World!" %></h1>
<h1><%= "Hello World!" %></h1>
<h1><%= "Hello World!" %></h1>
<h1><%= "Hello World!" %></h1>
<h1><%= "Hello World!" %></h1>
<h1><%= "Hello World!" %></h1>
<h1><%= "Hello World!" %></h1>
<h1><%= "Hello World!" %></h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<p>
    <a href="${pageContext.request.contextPath}/9">Go to 9</a>
</p>
<form action="${pageContext.request.contextPath}/9" method="post">
    <input type="submit" value="Go to 9" />
</form>
</body>
</html>