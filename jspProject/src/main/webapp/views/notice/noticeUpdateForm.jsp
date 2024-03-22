<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "com.kh.notice.model.vo.Notice" %>
<%
	Notice n = (Notice)request.getAttribute("notice");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP Project</title>
<style>
    .outer {
        background: black; color: white;
        width: 1000px; height: 500px;
        margin: auto; margin-top: 50px;
    }
    form table { border: 1px solid white;}
    form input, form textarea {width: 100%; }
</style>
</head>
<body>
    <%@ include file="../common/menubar.jsp" %>
    <div class="outer" align="center">
        <br>
        <h2>공지사항 수정하기</h2>
        <br>

        <form action="<%= contextPath %>/update.no" method="post">
        	<input type="hidden" name="num" value= "<%=n.getNoticeNo() %>">
            <table>
                <tr>
                    <th>제목</th>
                    <td><input type="text" name="title" value = "<%= n.getNoticeTitle() %>" required></td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td><textarea name="content" cols="30" rows="10" required style="resize: none;"><%= n.getNoticeContent() %></textarea></td>
                </tr>
            </table>
            <br><br>
            <div>
                <button type="submit">수정하기</button>
                <button type="button" onclick="history.back()">뒤로가기</button>
            </div>
        </form>
    </div>
</body>
</html>