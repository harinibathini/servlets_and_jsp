<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login</title>
</head>
<body>
	<h1>Login</h1>
	<c:if test="${not empty param.name}">
		<p>Name: ${param.name}</p>
	</c:if>
	<form action="Login" method="post">
	<fieldset>
	<tr>
	<td>User Email:</td>
	<td><input type="text" name="email"></td>
	</tr>
	<tr>
	<td>User Country:</td>
	<td><input type="text" name="country"></td>
	</tr>
	<tr>
	<td><input type="submit" value="Login"></td>
	<td><input type="reset"  value="Reset"></td>

	</fieldset>
	</form>

</body>
</html>
