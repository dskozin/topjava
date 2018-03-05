<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Meal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<div class="container">
    <div class="row">
        <div class="col">

            <h2>Meals</h2>

            <form action="meals" method="post" enctype="application/x-www-form-urlencoded">
                <div class="form-group">
                    <label for="dateTime">Дата и время</label>
                    <input type="datetime-local" class="form-control" name="date_time" id="dateTime"
                           value="${meal.dateTime}">
                </div>
                <div class="form-group">
                    <label for="description">Описание</label>
                    <input type="text" class="form-control" name="description" id="description"
                           value="${meal.description}">
                </div>
                <div class="form-group">
                    <label for="calories">Количество каллорий</label>
                    <input type="number" class="form-control" name="calories" id="calories" value="${meal.calories}">
                </div>
                <input type="hidden" name="id" value="${meal.id}">
                <a class="btn btn-light" href="meals">Назад</a>
                <button type="submit" class="btn btn-primary">Сохранить</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>