<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Home</title>
</head>
<body style="background:yellow;">
	<h1>Hello World!</h1>
	<c:if test="${not empty param.name}">
		<p>Name: ${param.name}</p>
	</c:if>

	<%!
	int a = 50;
	int b = 10;
	String name = "Coditas Solutions";

	public int doSum(){
	 return a+b;
	}

	public String reverse(){
	  StringBuffer br = new StringBuffer(name);
	  return br.reverse().toString();
	}
	%>

	<%
	out.println(a);
	out.print("<br>");
	out.println(b);
	out.print("<br>");
	out.println(doSum());
	out.print("<br>");
	out.println(reverse());
    %>

    <h1 style='color:red;'>Sum is: <%= doSum() %></h1>
    <h1><%= name %></h1>
</body>
</html>
