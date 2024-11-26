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
    #the-modal-dialog {
        border-radius: 8px;
        width: max(45vw, 300px);
    }
    tbody tr {
        cursor: pointer;
    }
</style>
<dialog id="the-modal-dialog">
    <form method="dialog" action="#" id="my-fucking-form">
      <input id="food-id" type="hidden" name="id"/>
      <div class="row mb-3">
        <label for="food-name" class="col-sm-4 col-form-label">Food Name:</label>
        <div class="width-100">
          <input id="food-name" class="form-control" type="text" name="name"/>
        </div>
      </div>
      <div class="row mb-3">
        <label for="food-price" class="col-sm-4 col-form-label">Price:</label>
        <div class="width-100">
          <input id="food-price" class="form-control" type="number" name="price"/>
        </div>
      </div>
      <div class="row mb-3">
        <label for="food-desc" class="col-sm-4 col-form-label">Description:</label>
        <div class="width-100">
          <textarea id="food-desc" class="form-control" name="description" rows="6"></textarea>
        </div>
      </div>
      <button class="btn btn-primary" id="submit-button">Submit</button>
      <button class="btn btn-danger" id="cancel-button">Cancel</button>
    </form>
</dialog>
<div class="jumbotron jumbotron-fluid">
  <div class="container">
    <h1 class="display-1">Foods</h1>
  </div>
</div>
<div class="container">
  <table id="main-table" class="table table-hover">
    <thead class="text-center">
    <tr>
      <th>이름</th>
      <th>가격(원)</th>
      <th>설명</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="food" items="${foods}">
      <tr data-id="${food.id}" onclick="openDialogUsing('${food.id}')">
        <td>${food.name}</td>
        <td class="text-end">${food.getPriceView()}</td>
        <td>${food.description}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <button type="button" class="btn btn-primary" onclick="openDialogForNew()">New</button>
</div>
<script>
    const dialog = document.getElementById("the-modal-dialog");
    const foodIdInput = document.getElementById("food-id")
    const foodNameInput = document.getElementById("food-name")
    const foodPriceInput = document.getElementById("food-price")
    const foodDescInput = document.getElementById("food-desc")
    let mode = null;

    function openDialog(id, name, price, description) {
        foodIdInput.value = id;
        foodNameInput.value = name;
        foodPriceInput.value = price;
        foodDescInput.value = description;
        mode = 'update'
        dialog.showModal();
    }

    function openDialogUsing(id) {
        fetch('/food/get/' + id).then(res => {
            if (res.ok) return res.text();
            else throw new Error(res.statusText);
        }).then(text => {
            if (text.endsWith('\n')) text = text.substring(0, text.length - 1);
            const [id, name, price, ...descriptionRows] = text.split('\n')
            openDialog(id, name, price, descriptionRows.join("\n"));
        }).catch(err => {
            console.error(err);
        })
    }

    function openDialogForNew() {
        foodIdInput.value = '';
        foodNameInput.value = '';
        foodPriceInput.value = '';
        foodDescInput.value = '';
        mode = 'insert'
        dialog.showModal();
    }

    function requestUpdate() {
        const body = new URLSearchParams({
            id: foodIdInput.value,
            name: foodNameInput.value,
            price: foodPriceInput.value,
            description: foodDescInput.value
        });
        fetch('/food/update', {
            method: 'POST',
            body: body,
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        }).then(response => (response.ok) && location.reload())
    }

    function requestInsert() {
        const body = new URLSearchParams({
            name: foodNameInput.value,
            price: foodPriceInput.value,
            description: foodDescInput.value
        });
        fetch('/food/insert', {
            method: 'POST',
            body: body,
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        }).then(response => (response.ok) && location.reload())
    }

    function doSubmit() {
        if (mode === 'update') requestUpdate()
        else if (mode === 'insert') requestInsert()
    }

    document.getElementById('submit-button').addEventListener('click', doSubmit)
    document.getElementById('cancel-button').addEventListener('click', () => dialog.close());

</script>
</body>
</html>
