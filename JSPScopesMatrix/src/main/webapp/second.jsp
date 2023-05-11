<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" info= "This is information"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>
<%

String name=(String)pageContext.getAttribute("user",PageContext.REQUEST_SCOPE);
out.print("Hello "+name);

%>
</body>
</html>

