<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page = "../login/login_check_modul.jsp"/>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
</head>
<body>
	<h1>마이페이지</h1>
	${loginOkUser.name}님 안녕하세요!
	<a href="../mypage/mypage_update.jsp">
    <button>개인정보수정</button>
	</a>
	
	<h2>개인정보</h2>
	
	<table>
		<tr>
		<!-- 왼쪽 표 -->
		<td>
			<table border="1">
				<tr><th>이름</th><td>${loginOkUser.name}</td></tr>
				<tr><th>닉네임</th><td>${loginOkUser.nickname}</td></tr>
		        <tr><th>이메일</th><td>${loginOkUser.email}</td></tr>
		        <tr><th>전화번호</th><td>${loginOkUser.phone}</td></tr>
		        <tr><th>성별</th><td>${loginOkUser.gender}</td></tr>
		        <tr><th>생년월일</th><td>${loginOkUser.birth}</td></tr>
		        <tr><th>가입일</th><td>${loginOkUser.cdate}</td></tr>
	        </table>
    	</td>
              
        <!-- 오른쪽 표 -->
		<td>
	   		<table border="1">
		        <tr><td>총 게시글 : ${mypostcount}</td></tr>
		        <tr><td>총 댓글 : ${mycommentcount}</td></tr>
		        <tr><td>받은 총 공감수 : ${mypostlike}</td></tr>
	     	</table>
    	</td>
		</tr>
	</table>
	
	
	<!-- 내가 쓴 글 -->
	<h2>내가 쓴 글</h2>
	
<form action="../mypage/mypage.do?m=delete" method="post">
	<table border="1" width="100%">
	
		 <tr>
	        <th>선택</th>
	        <th>제목</th>
	        <th>작성일</th>
	        <th>조회수</th>
	        <th>공감수</th>
	    </tr>
    
	<c:forEach var="p" items="${mypost}">

        <tr>
            <td><input type="checkbox" name="chk" value="${p.post_num}"></td>
            <td>${p.post_subject}</td>
            <td><fmt:formatDate value="${p.post_date}" pattern="yyyy-MM-dd"/></td>
            <td>${p.post_view}</td>
            <td>${p.likes}</td>
        </tr>
    </c:forEach>
	
	</table>
	
	
	<!-- 페이지 네비게이션 -->
<div style="text-align:center; margin-top:10px;">
    <a href="#">1</a> 
    <a href="#">2</a> 
    <a href="#">3</a> 
    <a href="#">4</a> 
    ...
</div>

<button type="submit">삭제</button>
</form>
	
</body>
</html>