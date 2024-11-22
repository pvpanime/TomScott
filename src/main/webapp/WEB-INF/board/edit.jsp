<%--@elvariable id="board" type="dev.nemi.tomscott.board.BoardDTO"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <jsp:include page="../include/style.jsp" />
  <title>Edit Board!</title>
</head>
<body class="bg-dark text-light">
  <main>
    <h1>Edit Board!</h1>
    <form action="${pageContext.request.contextPath}/board/edit" method="post">
      <input type="hidden" name="title" value="${board.getTitle()}" />
      <fieldset>
        <legend>Board</legend>
        <div>
          <h1>${board.getTitle()}</h1>
        </div>
        <div>
          <div>
            <label for="BoardContent">Content</label>
          </div>
          <div>
            <textarea id="BoardContent" name="content" style="width: 100%; height: 50%;"><c:out value="${board.getContent()}" /></textarea>
          </div>
        </div>
      </fieldset>
      <fieldset>
        <legend>Submit</legend>
        <input type="submit" value="Publish">
      </fieldset>
    </form>
  </main>
</body>
</html>
