<%--
  Created by IntelliJ IDEA.
  User: kdt
  Date: 2024-11-25
  Time: 10:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <meta charset="utf-8">
  <jsp:include page="../include/style.jsp"/>
  <title>Foods</title>
</head>
<body data-bs-theme="dark">
<dialog id="the-fucking-dialog">
  <form method="dialog" action="#" id="my-fucking-form">
    <input id="food-id" type="hidden" name="id"/>
    <p>
      <label for="food-name">Food Name:</label>
      <input id="food-name" type="text" name="name"/>
    </p>
    <p>
      <label for="food-desc">Description:</label>
      <input id="food-desc" type="text" name="description"/>
    </p>
    <input type="submit" value="Change"/>
  </form>
</dialog>
<div class="jumbotron jumbotron-fluid">
  <div class="container">
    <h1 class="display-1">Foods</h1>
  </div>
</div>
<div class="container">
  <table id="main-table" class="table">
    <tbody>
    <tr>
      <th>이름</th>
      <th>설명</th>
    </tr>
    <c:forEach var="food" items="${foods}">
      <tr data-id="${food.id}" onclick="openDialog('${food.id}', '${food.name}', '${food.description}')">
        <td>${food.name}</td>
        <td>${food.description}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
<script>
    function openDialog(id, name, description) {
        const dialog = document.getElementById("the-fucking-dialog");
        document.getElementById("food-id").value = id;
        document.getElementById("food-name").value = name;
        document.getElementById("food-desc").value = description;
        dialog.show();
    }

    const myFuckingForm = document.getElementById('my-fucking-form')
    myFuckingForm.addEventListener('submit', function (event) {
        event.preventDefault();
        const body = new URLSearchParams(new FormData(myFuckingForm));
        fetch('/food', {
            method: 'PUT',
            body: body,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
        })
    });
</script>
</body>
</html>
