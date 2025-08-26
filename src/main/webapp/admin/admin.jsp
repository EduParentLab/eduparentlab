<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
</head>
<h1>관리자 페이지</h1>
<body>
	<h2>게시글 통계 나중에 넣을거임</h2>
	<h2>유저 통계 나중에 넣을거임</h2>
	
	
	<div class="tab">
	  <button class="tablinks" onclick="openTab(event, 'news', 'Admin.do?m=getNews')">공지사항</button>
	  <button class="tablinks" onclick="openTab(event, 'user', 'Admin.do?m=getMember')">사용자목록</button>
	  <button class="tablinks" onclick="openTab(event, 'ghost', 'Admin.do?m=getGhost')">탈퇴회원목록</button>
	</div>
	<div>
		<h2>탭1 공지사항</h2>
	</div>
	<div>
		<h2>탭2 사용자목록</h2>
	</div>
	<div>
		<h2>탭3 탈퇴회원목록</h2>
	</div>
</body>
</html>