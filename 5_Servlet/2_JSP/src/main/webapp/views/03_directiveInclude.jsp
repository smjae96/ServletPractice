<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Directive Include</title>

<style>
	h1 {color: red;'}
</style>
</head>
<body>
	<h1>include 지시어</h1>
	
	<div style="border: 1px solid magenta">
		<%@ include file="01_ScriptingElement.jsp" %>
	</div>
	
	포함된 jsp 페이지에서 선언된 변수 사용 가능 <br>
	1부터 100까지 총 합계 : <%= i %>
</body>
</html>