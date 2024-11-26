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
<style>
    #the-fucking-dialog {
        border-radius: 8px;
    }

    tbody tr {
        cursor: pointer;
    }
</style>
<dialog id="the-fucking-dialog">
  <div id="stop-propagation">
    <form method="dialog" action="#" id="my-fucking-form">
      <input id="food-id" type="hidden" name="id"/>
      <div class="row mb-3">
        <label for="food-name" class="col-sm-4 col-form-label">Food Name:</label>
        <div class="col-sm-10">
          <input id="food-name" class="form-control" type="text" name="name"/>
        </div>
      </div>
      <div class="row mb-3">
        <label for="food-desc" class="col-sm-4 col-form-label">Description:</label>
        <div class="col-sm-10">
          <input id="food-desc" class="form-control" type="text" name="description"/>
        </div>
      </div>
      <input class="btn btn-primary" type="submit" value="Change"/>
      <button class="btn btn-danger" value="cancel" formmethod="dialog">Cancel</button>
    </form>
  </div>
</dialog>
<div class="jumbotron jumbotron-fluid">
  <div class="container">
    <h1 class="display-1">Foods</h1>
  </div>
</div>
<div class="container">
  <table id="main-table" class="table table-hover">
    <thead>
    <tr>
      <th>이름</th>
      <th>설명</th>
    </tr>
    </thead>
    <tbody>
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
    const dialog = document.getElementById("the-fucking-dialog");

    document.getElementById("stop-propagation").addEventListener('click', (event) => {
        event.stopPropagation();
    });

    function openDialog(id, name, description) {
        document.getElementById("food-id").value = id;
        document.getElementById("food-name").value = name;
        document.getElementById("food-desc").value = description;
        dialog.showModal();
    }

    const myFuckingForm = document.getElementById('my-fucking-form')
    myFuckingForm.addEventListener('submit', function (event) {
        event.preventDefault();
        const body = new URLSearchParams(new FormData(myFuckingForm));
        fetch('/food/update', {
            method: 'POST',
            body: body,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
        }).then(response => {
            if (response.ok) {
                location.reload();
            }
        }).catch(err => {
            window.alert("으악!! HTTP 오류가 발생했습니다! Console을 확인하세요")
            console.error(err);
        })
        dialog.close()
    });
</script>
</body>
</html>
