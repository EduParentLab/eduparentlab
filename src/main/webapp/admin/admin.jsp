<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
</head>
<h1>관리자 페이지</h1>
<a href='../'>인덱스</a>
<body>
	<h2>게시글 통계 나중에 넣을거임</h2>
	<h2>유저 통계 나중에 넣을거임</h2>	
	<div class="tab">
	  <button onclick="getNews()">공지사항</button>
	  <button onclick="getUser()">사용자목록</button>
	  <button onclick="getGhost()">탈퇴회원</button>
	</div>		
	<div id="newsTable">	
	<table border='1' width='1000' cellpadding='2'>
	<tr>
		<th align='center' width='10%'>글번호</th>
		<th align='center' width='50%'>글제목</th>
		<th align='center' width='30%'>작성날짜</th>
		<th align='center' width='10%'>조회수</th>
	</tr>
	<c:choose>
	    <c:when test="${empty news}">
	        <tr>
	        <td align='center' colspan="4">작성한 글 없음</td>
	        </tr>
	    </c:when>
	    <c:otherwise>
	        <c:forEach items="${news}" var="dto">
	        <tr>
			<td align='center'>${dto.post_num}</td>
			<td align='center'>
			<a href='../post/post.do?m=content&code=${dto.post_num}'>${dto.post_subject}</a>
			</td>
			<td align='center'>${dto.post_date}</td>		
			<td align='center'>${dto.post_view}</td>
			</tr>
	        </c:forEach>
	    </c:otherwise>	    
	</c:choose>	
	</table>
	</div>	
	<div id="userTable" style="display:none;">	
	<table border='1' width='1000' cellpadding='2'>
	<tr>
		<th align='center' width='20%'>이메일</th>
		<th align='center' width='5%'>닉네임</th>
		<th align='center' width='5%'>성별</th>
		<th align='center' width='10%'>생년월일</th>
		<th align='center' width='5%'>이름</th>
		<th align='center' width='10%'>휴대폰번호</th>
		<th align='center' width='10%'>가입한날짜</th>
	</tr>
	<c:choose>
	    <c:when test="${empty user}">
	        <tr>
	        <td align='center' colspan="7">사용자 없음</td>
	        </tr>
	    </c:when>
	    <c:otherwise>
	        <c:forEach items="${user}" var="dto">
	        <tr>
			<td align='center'>${dto.email}</td>			
			<td align='center'>${dto.nickname}</td>		
			<td align='center'>${dto.gender}</td>
			<td align='center'>${dto.birth}</td>
			<td align='center'>${dto.name}</td>
			<td align='center'>${dto.phone}</td>
			<td align='center'>${dto.cdate}</td>
			</tr>		
	        </c:forEach>
	    </c:otherwise>	    
	</c:choose>	
	</table>
	</div>
	<div id="ghostTable" style="display:none;">	
	<table border='1' width='1000' cellpadding='2'>
	<tr>
		<th align='center' width='20%'>이메일</th>
		<th align='center' width='5%'>닉네임</th>
		<th align='center' width='5%'>성별</th>
		<th align='center' width='10%'>생년월일</th>
		<th align='center' width='5%'>이름</th>
		<th align='center' width='10%'>휴대폰번호</th>
		<th align='center' width='10%'>가입한날짜</th>
	</tr>
	<c:choose>
	    <c:when test="${empty ghost}">
	        <tr>
	        <td align='center' colspan="7">탈퇴회원 없음</td>
	        </tr>
	    </c:when>
	    <c:otherwise>
	        <c:forEach items="${ghost}" var="dto">
	        <tr>
			<td align='center'>${dto.email}</td>			
			<td align='center'>${dto.nickname}</td>		
			<td align='center'>${dto.gender}</td>
			<td align='center'>${dto.birth}</td>
			<td align='center'><a href='../post/post.do?m=content&code=${dto.name}'>${dto.name}</a></td>
			<td align='center'>${dto.phone}</td>
			<td align='center'>${dto.cdate}</td>
			</tr>		
	        </c:forEach>
	    </c:otherwise>	    
	</c:choose>	
	</table>
	</div>
</body>
</html>
<script>
	function getNews(){
		document.getElementById("userTable").style.display = "none";
		document.getElementById("ghostTable").style.display = "none";
		document.getElementById("newsTable").style.display = "block";
	}
	function getUser(){
		document.getElementById("newsTable").style.display = "none";
		document.getElementById("ghostTable").style.display = "none";
		document.getElementById("userTable").style.display = "block";
	}
	function getGhost(){	
		document.getElementById("newsTable").style.display = "none";
		document.getElementById("userTable").style.display = "none";
		document.getElementById("ghostTable").style.display = "block";
	}
</script>
	