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
	<h1>Hello World!</h1>
	<form method="get" action="Update">
      <input type="hidden" name="id" value="<%=request.getAttribute("id")%>">
      <label for="name">Name:</label>
      <input type="text" name="name" value="<%=request.getAttribute("name")%>">
      <br>
      <label for="email">Email:</label>
      <input type="text" name="email" value="<%=request.getAttribute("email")%>">
      <br>
      <label for="age">Pass:</label>
      <input type="text" name="pass" value="<%=request.getAttribute("pass")%>">
      <br>
       <label for="age">Phone</label>
            <input type="text" name="phone" value="<%=request.getAttribute("phone")%>">
            <br>
      <input type="submit" value="Edit">
    </form>





	<c:if test="${not empty param.name}">
		<p>Name: ${param.name}</p>
	</c:if>
</body>
</html>