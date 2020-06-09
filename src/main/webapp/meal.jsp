<%--
  Created by IntelliJ IDEA.
  User: Lirie
  Date: 6/9/2020
  Time: 11:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>Meal Edit/Insert</title>
</head>
<body>
<form method="POST" action='meals' name="frmAdd">
    <input hidden type="number" name="id" size="70" value="${meal.id}"/>
    Description : <input
        type="text" name="description" size="70"
                         value="${meal.description}"/> <br/>
    Date and Time : <input
        type="datetime-local" name="dateTime"
        value="${meal.dateTime}"/> <br/>
    Calories : <input
        type="number" min="1" name="calories" size="10"
        value="<${meal.calories}"/> <br/>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>
