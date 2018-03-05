<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<div class="container">
    <div class="row">
        <div class="col">

            <h2>Meals</h2>

            <table class="table table-bordered table-hover">
                <thead class="thead-dark">
                <tr>
                    <th>Дата и время</th>
                    <th>Описание</th>
                    <th>Количество каллорий</th>
                    <th>Редактировать</th>
                    <th>Удалить</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${meals}" var="meal">
                    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealWithExceed"/>
                    <c:set var="style" value="${meal.exceed ? 'danger' : 'success'}"/>
                    <tr class="table-${style}">
                        <td class="text-${style}">${meal.formattedDateTime}</td>
                        <td class="text-${style}">${meal.description}</td>
                        <td class="text-${style}">${meal.calories}</td>
                        <td><a href="meals?action=edit&id=${meal.id}">Редактировать</a></td>
                        <td><a href="meals?action=delete&id=${meal.id}">Удалить</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <a href="meals?action=edit">Создать новое</a>
        </div>
    </div>
</div>
</body>
</html>