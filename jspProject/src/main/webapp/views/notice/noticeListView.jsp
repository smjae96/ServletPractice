<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.notice.model.vo.Notice" %>
<% 
	ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.outer { background: black; color: white;
			width: 1000px; height: 500px; margin: auto; margin-top: 50px;}
			
	.list-area { border: 1px solid white; text-align: center;}		
	.list-area>tbody>tr:hover {
		background: darkgray; cursor: pointer;
	}
</style>
</head>
<body>
	<%-- menubar.jsp 파일을 포함시키기 --%>
	<%@ include file="../common/menubar.jsp" %>
	<div class="outer">
		<br>
		<h2 align="center">공지사항</h2>
		<br>
		<%-- 로그인 한 계정이 관리자인 경우만 글쓰기 버튼이 표시되도록 --%>
		<% if(loginUser != null && loginUser.getUserId().equals("admin")) { %>
			<div align="right" style="width:850px;">
				<a href="<%= contextPath %>/enroll.no" class="btn btn-warning">글쓰기</a>
			</div>
			<br>
		<% } %>
		<%-- ----- --%>
	<table align="center" class="list-area">
		<thead>
			<th>글번호</th>
			<th width="400">글제목</th>
			<th width="100">작성자</th>
			<th>조회수</th>
			<th width="100">작성일</th>
		</thead>
		<tbody>
			<%-- 공지사항이 없을 경우 => 리스트(list)가 비어있는 상태 --%>
			<% if(list.isEmpty()) { %>
			<tr>
				<td colspan="5">존재하는 공지사항이 없습니다.</td>
			</tr>
			<% } else { %>
			<%-- for(int i=0; i<list.size(); i++) { --%>
			<%-- 공지사항이 있을 경우 => 반복문을 통해 공지사항을 출력 --%>
			<% for(Notice n : list) { %>
			<tr>
				<td><%= n.getNoticeNo() %></td>
				<td><%= n.getNoticeTitle() %></td>
				<td><%= n.getNoticeWriter() %></td>
				<td><%= n.getCount() %></td>
				<td><%= n.getCreateDate() %></td>
			</tr>
			<% } %>
		<% } %>
		</tbody>
	</table>
	</div>
	<script>
		const trList = document.querySelectorAll(".list-area>tbody>tr");
		for (const tr of trList) {
			tr.onclick = function() {
				const noticeNo = this.childNodes[1].innerText;
				// console.log(this.childNodes);
				// console.log(noticeNo);

				// get 요청 => 요청url?키값=밸류값&키값=밸류값;
				location.href = "<%= contextPath %>/detail.no?num="+noticeNo;
				//				=> /jsp/detail.no?num=2
			}
		}
	</script>
</body>
</html>