<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <jsp:include page="../include/style.jsp" />
  <title>Board</title>
</head>
<body data-bs-theme="dark">
<div class="container py-4">
  <main>
    <div class="jumbotron bg-dark text-light text-center py-5">
      <h1 class="display-1">Board Index</h1>
    </div>
    <ul class="list-group">
      <c:forEach var="dto" items="${list}">
        <li class="list-group-item">
          <a href="/board/read/${dto.getPath()}" class="text-decoration-none text-light">${dto.getTitle()}</a>
        </li>
      </c:forEach>
    </ul>
    <div class="mt-4 text-center">
      <a href="${pageContext.request.contextPath}/board/write">
        <button type="button" class="btn btn-primary">Write</button>
      </a>
    </div>
  </main>
</div>

</body>
</html>