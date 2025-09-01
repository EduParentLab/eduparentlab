<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<header>
  <div class="logo">
    <a href="index.html">
      <img src="./assets/logoremoveback.png" alt="학부모정보통 로고" />
    </a>
  </div>
  <div class="search-container">
    <div class="search-bar">
      <div class="search-logo">N</div>
      <input type="text" id="searchInput" placeholder="검색어를 입력해 주세요." />
      <a href="#">
        <button class="search-btn">🔍</button>
      </a>
    </div>
    <div class="search-dropdown">
      <div class="search-section-title">검색 추천</div>
      <ul class="search-list">
        <li>교육</li>
        <li>탐구</li>
        <li>연구소</li>
      </ul>
    </div>
  </div>
  <div class="login" id="login-before">
    <a href="login.html">
       <button>로그인</button>
    </a>
    <a href="resist.html">
      <button>회원가입</button>
    </a>
    </div>
    <div class="login" id="login-after" style="display:none;">
    <label>진석님 안녕하세요</label><button>마이페이지</button><button>로그아웃</button>
  </div>
</header>

<div class="navigation-button-container">
  <a href="main.do" class="navigation-button">홈</a>
  <a href="../post.do?m=list&category_num=1" class="navigation-button">자유</a>
  <a href="../post.do?m=list&category_num=2" class="navigation-button">입시</a>
  <a href="../post.do?m=list&category_num=3" class="navigation-button">고등</a>
  <a href="../post.do?m=list&category_num=4" class="navigation-button">공지사항</a>
</div>
</body>
</html>