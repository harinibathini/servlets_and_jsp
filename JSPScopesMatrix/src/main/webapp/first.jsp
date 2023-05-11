<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<body>
	<%
	String name = request.getParameter("uname");
	out.print("welcome "+name);
	out.print("<br>");
	pageContext.setAttribute("user",name,PageContext.REQUEST_SCOPE);
	%>
	<a href="second.jsp">Second JSP Page</a>
</body>
</html>
