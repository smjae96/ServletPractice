<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.member.model.vo.Member"%>
<%
	Member loginUser = (Member)session.getAttribute("loginUser");
	// 로그인 시도 전 loginUser 객체가 null

    String contextPath = request.getContextPath();
	
	String alertMsg = (String)session.getAttribute("alertMsg");
	// 메시지가 있으면 : 해당 메시지 내용
	// 메시지가 없으면 : null
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP Project</title>
<style>
    .login-area > * { float: right;

    }
    .login-area a { text-decoration: none;}

    .nav-area {
        background: black;
    }
    .menu {
        display: table-cell;    /* table의 cell 형식처럼 바꿔주는 속성. */
        width: 150px;
        height: 50px;
    }
    .menu a {
        text-decoration: none;
        color: white;
        font-size: 20px;
        font-weight: bold;
        display: block;
        width: 100%;
        height: 100%;
        line-height: 50px;
    }
    .menu a:hover, .login-user a:hover {
    	background: darkgrey;
    }
</style>
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous">
</script>
<!-- bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<!-- bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
crossorigin="anonymous"></script>
</head>
<body>
	<% if(alertMsg != null) { %>
	<script>
		alert("<%= alertMsg %>");
	</script>
	<% session.removeAttribute("alertMsg"); %>	<%-- removeAttribute : 세션의 값 삭제하기 --%>
	<% } %>
    <h1 align="center">Welcome Jaeyoon World</h1>

    <div class="login-area">
        <!-- 1) 로그인 전-->
        <% if(loginUser == null) { %>
        <form action="<%= contextPath %>/login.me" method="POST">
            <table>
                <tr>
                    <th>아이디</th>
                    <td><input type="text" name="userId" required/></td>
                </tr>
                <tr>
                    <th>비밀번호</th>
                    <td><input type="password" name="userPwd" required></td>
                </tr>
                <tr>
                    <th colspan="2">
                        <button type="submit">로그인</button>
                        <button type="button" onclick="enrollPage()">회원가입</button>
                    </th>
                </tr>
            </table>
            <script>
            	function enrollPage() {
            		// 회원가입 페이지를 요청
            		//  location.href = "<%= contextPath%>/views/member/memberEnrollForm.jsp"; 
            		// 디렉토리 구조가 url에 그대로 노출 => 보안에 취약
            		
            		// 단순한 페이지 요청도 서블릿을 통해 처리될 수 있도록 하자.
            		location.href = "<%= contextPath %>/enrollForm.me";
            	}
            </script>
        </form>
        <% } else { %>

        <!-- 2) 로그인 후-->
        <div class="login-user">
            <b><%= loginUser.getUserName() %></b>의 방문을 환영합니다.
            <br><br>
            <div align="center">
                <a href="<%= contextPath %>/myPage.me">마이페이지</a>
                <a href="<%= contextPath %>/logout.me">로그아웃</a>
            </div>
        </div>
        <% } %>
    </div>
    <!-- clear : float 스타일 속성의 영향을 받지 않도록 처리 / both: 왼쪽 오른쪽 둘 다 영향 받지 않도록-->
    <br clear="both">
    <br>
    <div class="nav-area" align="center">
        <div class="menu"><a href="<%= contextPath %>">Home</a></div>
        <div class="menu"><a href="<%= contextPath %>/list.no">공지사항</a></div>
        <div class="menu"><a href="<%= contextPath %>/list.bo?cpage=1">일반게시판</a></div>
        <div class="menu"><a href="">사진게시판</a></div>
    </div>
</body>
</html>