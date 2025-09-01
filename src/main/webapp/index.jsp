<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>

	<h1>학부모 정보통</h1><br></br>
	<a href="admin/admin.do">관리자페이지</a><br/>
	<a href="post.do">post</a><br/>
	<a href="test.jsp">DB연결테스트</a><br/>
	<a href="likesTest.jsp">공감테스트</a><br/>	
	<a href="<%=request.getContextPath()%>/post.do">post + comment</a><br/>
	<br>
    <br>
	
	<c:choose>
        <c:when test="${empty loginOkUser}">
            <a href="login/login.do?m=form">로그인</a>
        </c:when>
        <c:otherwise>
            <font style="color:green">${loginOkUser.name}</font>님 안녕하세요
            <a href="login/login.do?m=logout">로그아웃</a>
            <a href="mypage/mypage.do">마이페이지</a>
            
        </c:otherwise>
    </c:choose>
    
    <br>
    <br>
    <a href="register/register.jsp">회원가입</a>


</body>
</html>