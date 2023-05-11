<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Home</title>
</head>
<body>
	<jsp:useBean id="student" class="com.Student" scope="application">
	<jsp:setProperty property="*" name="student"/>

	<hr>
	<h1>Student Information is::<br><hr></h1>
	Id is:<jsp:getProperty property="id" name="student"/><br>
	Fname is:<jsp:getProperty property="fname" name="student"/><br>
	Lname is:<jsp:getProperty property="lname" name="student"/><br>
	</jsp:useBean>
</body>
</html>
