<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, java.util.Date, java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pizza Order</title>
<style>
	fieldset {
		width: 40%;
	}
	.info {
		display: flex;
	}
	label {
		width: 20%;
	}
	textarea {
		width: 80%
	}
</style>
</head>
<body>
<h1>피자 주문하기</h1>
<h2>현재 날짜</h2>
<h3><% Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
		String today = sdf.format(d);
		out.println(today);
	%></h3>

<form action="/2_JSP/orderPizza.do" method="GET">
	
	<fieldset>
		<legend>주문자 정보</legend>
		<div class="info">
		<label>이름</label>
		<input type="text" name="name" required>
		</div>
		<br>
		<div class="info">
		<label>연락처</label>
		<input type="text" name="phone" required>
		</div>
		<br>
		<div class="info">
		<label>주소</label>
		<input type="text" name="address" required>
		</div>
		<br>
		<div style="display: flex;">
		<label>요청사항</label>
		<textarea rows=10 placeholder="내용을 입력하세요." name="requestion"></textarea>
		</div>
	</fieldset>
	
	<fieldset>
		<legend>주문 정보</legend>
		<label>피자</label>
		<select name="menu">
			<option value="콤비네이션피자">콤비네이션 피자</option>
			<option value="치즈피자">치즈 피자</option>
			<option value="불고기피자">불고기 피자</option>
			<option value="포테이토피자">포테이토 피자</option>
			<option value="하와이안피자">하와이안 피자</option>
		</select>
		<br>
		<label>토핑</label>
		<input type="checkbox" name="topping" value="치즈">
		<label>치즈</label>
		<input type="checkbox" name="topping" value="올리브">
		<label>올리브</label>
		<input type="checkbox" name="topping" value="불고기">
		<label>불고기</label>
		<input type="checkbox" name="topping" value="치즈 크러스트">
		<label>치즈 크러스트</label>
		<input type="checkbox" name="topping" value="버섯">
		<label>버섯</label>
		<input type="checkbox" name="topping" value="파인애플">
		<label>파인애플</label>
		<input type="checkbox" name="topping" value="베이컨">
		<label>베이컨</label>
		<input type="checkbox" name="topping" value="포테이토">
		<label>포테이토</label>
		<br>
		<label>사이드</label>
		<input type="checkbox" name="side" value="피클">
		<label>피클</label>
		<input type="checkbox" name="side" value="사이다">
		<label>사이다</label>
		<input type="checkbox" name="side" value="콜라">
		<label>콜라</label>
	</fieldset>
	<br>
	<input type="submit" value="주문">
	<input type="reset" value="초기화">
</form>

</body>
</html>