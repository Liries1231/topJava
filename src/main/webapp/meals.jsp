<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.javawebinar.topjava.model.Meal" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table border=1>
    <thead>
    <tr>
        <th>№</th>
        <th>Description</th>
        <th>Date and Time</th>
        <th>Calories</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:set var="count" scope="session" value="1"/>
    <c:forEach items="${meals}" var="meal">
        <c:set var="color" value="${(meal.excess == 'true') ? '#db7093' : '#90ee90'}" />
        <tr bgcolor="${color}">
            <td><c:out value="${meal.id}"/></td>
            <td><c:out value="${meal.description}"/></td>
            <td><c:out value="${meal.dateTime}"/></td>

            <td><c:out value="${meal.calories}"/></td>
            <td>
            <a href="meals?action=edit&id=${meal.id}">Update</a>
            <a href="meals?action=delete&id=${meal.id}">Delete</a>
            </td>
        </tr>

    </c:forEach>
    </tbody>
</table>
<p><a href="meals?action=insert">Add Meal</a></p>
</body>


</html>