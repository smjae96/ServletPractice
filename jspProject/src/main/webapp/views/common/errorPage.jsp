<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ErrorPage</title>
</head>
<body>
<%-- 같은 경로 상에 menubar.jsp 파일이 있으므로 그냥 menubar.jsp로 써줘도 됨. --%>
	<%@ include file="menubar.jsp" %>
	<h1 align="center"><%= request.getAttribute("errorMsg") %></h1>
	
</body>
</html>