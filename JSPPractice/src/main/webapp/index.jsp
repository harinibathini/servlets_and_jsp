<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ani" uri="/WEB-INF/CustomTag.tld" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Home</title>
</head>
<body>
	<h1>Hello World!</h1>
	<c:if test="${not empty param.name}">
		<p>Name: ${param.name}</p>
	</c:if>
	<!-- <form method="post" action="welcome.jsp">
	id:<input type="text" name="id"><br>
	firstname:<input type="text" name="fname">
	lastname:<input type="text" name="lname">
	<input type="submit" value="register">
	</form> -->
	<h2>Hello World!</h2>
	<hr>
	Hello... <ani:MyMsg/>
</body>
</html>
