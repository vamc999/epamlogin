<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Hello.......!</h1>
<%= request.getAttribute("menuname")%>
<%= request.getAttribute("menupagepath")%>
</body>
</html>