<%@page import="session钝化和活化.BeansListener"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		BeansListener bean = new BeansListener();	
		bean.setNum(10);
		bean.setUser("张三");
		session.setAttribute("bean", bean);
	%>
</body>
</html>