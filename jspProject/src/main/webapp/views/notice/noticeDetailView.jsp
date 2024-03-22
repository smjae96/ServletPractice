<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.notice.model.vo.Notice" %>
<%
	Notice n = (Notice)request.getAttribute("notice");
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
		<h2 align="center">공지사항 상세보기</h2>
		<br>
		
		<table>
			<tr>
				<th width="70">제목</th>
				<td colspan="3" width="400"><%= n.getNoticeTitle() %></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><%= n.getNoticeWriter() %></td>
				<th>작성일</th>
				<td><%= n.getCreateDate() %></td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3">
					<p style="height: 150px"><%= n.getNoticeContent() %></p>
				</td>
			</tr>
		</table>
		<br><br>
		<div>
			<a href="<%= contextPath %>/list.no" class="btn btn-info">목록으로 돌아가기</a>
			<%-- 로그인한 계정이 관리자인 경우에만 수정하기/삭제하기 버튼이 표시되도록 --%>
			<% if(loginUser != null && loginUser.getUserId().equals(n.getNoticeWriter())) { %>
				<a href="<%= contextPath %>/updateForm.no?num=<%= n.getNoticeNo() %>" class="btn btn-warning">수정하기</a>
				<a href="<%= contextPath %>/delete.no?num=<%= n.getNoticeNo() %>" class="btn btn-danger">삭제하기</a>
				<%--
					사용될 쿼리문:
						update notice
							set status = 'N'
							where notice_no = ?
							
					공지 사항 삭제를 성공했을 경우,
						"해당 공지사항이 삭제되었습니다." 메시지창을 띄워주고 공지사항 목록페이지로 이동
					공지 사항 삭제를 실패했을 경우,
						"공지사항 삭제를 실패했습니다." 라는 메시지가 보여지는 에러페이지로 이동
					
				 --%>
			<% } %>
			
		</div>
	</div>
</body>
</html>