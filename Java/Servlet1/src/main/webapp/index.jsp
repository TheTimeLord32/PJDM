<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="java.util.Date"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Index JSP</title>
</head>
<body>
	<p>Hello <%=request.getRemoteAddr() %>! </p>
	<p>Server time is <%=new Date()%> </p>
</body>
</html>