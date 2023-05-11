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
	<c:if test="${not empty param.name}">
		<p>Name: ${param.name}</p>
	</c:if>
	<%--
	<%out.print("welcome to jsp");%>
	<form action="welcome.jsp">
	<input type="text" name="uname"><br>
	<input type="submit" value="go">
	</form>

	<%! int data=50; %>
    <%= "Value of the variable is:"+data %>
    <%! int cube(int n){
     return n*n*n;
     }
     %>
    <%= "Cube of 3 is:"+cube(3) %>
    --%>
	</body>
</html>
