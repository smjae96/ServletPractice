<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.board.model.vo.Board, com.kh.board.model.vo.*" %>
<%
Board b = (Board)request.getAttribute("board");
Attachment a = (Attachment)request.getAttribute("attachment");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP Project</title>
<style>
	.outer { background-color: black; color: white;
			width: 1000px; height: 500px; margin: auto; margin-top: 50px;
		}
	.outer table {
		border: 1px solid white;
		border-collapse: collapse;
	}
	
	.outer > table tr, .outer > table td {
		border: 1px solid white; 
	}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	
	<div class="outer" align="center">
		<br>
		<h2 align="center">일반게시글 상세보기</h2>
		<br>
		
		<table>
			<tr>
				<th>카테고리</th>
				<td><%= b.getCategoryNo() %></td>
				<th>제목</th>
				<td><%= b.getBoardTitle() %></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><%= b.getBoardWriter() %></td>
				<th>작성일</th>
				<td><%= b.getCreateDate() %></td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3">
					<p style="height: 200px"><%= b.getBoardContent() %></p>
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td colspan="3">
					<%-- 첨부파일이 없는 경우 --%>
					<% if(a == null) { %>
					첨부파일이 없습니다.
					<% } else { %>
					<%-- 첨부파일이 있는 경우 --%>
					<a href="<%=contextPath %>/<%= a.getFilePath() %><%= a.getChangeName()%>"><%= a.getOriginName() %></a>
					<% } %>
				</td>
			</tr>
		</table>
		<br><br>
		<div align="center">
			<a href="javascript:window.history.back();" class="btn btn-info">목록으로 돌아가기</a>
			<%-- 로그인한 사용자와 게시글 작성자가 동일한 경우 표시 --%>
		
				<a href="<%= contextPath %>/updateForm.no?num=<%= b.getBoardNo() %>" class="btn btn-warning">수정하기</a>
				<a href="<%= contextPath %>/delete.no?num=<%= b.getBoardNo() %>" class="btn btn-danger">삭제하기</a>
			
			
		</div>
	</div>
</html>