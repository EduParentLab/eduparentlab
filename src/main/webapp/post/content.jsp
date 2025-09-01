<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>학부모정보통</title>
   <link rel="stylesheet" href="<%=request.getContextPath()%>/post/css/layout.css" />
  <link rel="stylesheet" href="<%=request.getContextPath()%>/post/css/board_content.css" />
</head>

<body>
  <div class="wrapper">

    <header>
      <div class="logo">
       <a href="<%=request.getContextPath()%>/index.jsp">
         <img src="<%=request.getContextPath()%>/post/assets/logoremoveback.png" alt="학부모정보통 로고" />
	    </a>
      </div>
      <div class="search-container">
        <div class="search-bar">
          <div class="search-logo">N</div>
          <input type="text" id="searchInput" placeholder="검색어를 입력해 주세요." />
          <button class="search-btn">🔍</button>
        </div>
        <!--  아래 추천 검색어 목록 -->
        <div class="search-dropdown">
          <div class="search-section-title">검색 추천</div>
          <ul class="search-list">
            <li>교육</li>
            <li>탐구</li>
            <li>연구소</li>
          </ul>
        </div>
      </div>
      <div class="login"><button>로그인</button><button>마이페이지</button></div>
    </header>
    
     <!-- 네비게이션 -->
    <div class="navigation-button-container">
      <a href="<%=request.getContextPath()%>/post.do?m=list&category_num=1" class="navigation-button">공지게시판</a>
      <a href="<%=request.getContextPath()%>/post.do?m=list&category_num=2" class="navigation-button">입시게시판</a>
      <a href="<%=request.getContextPath()%>/post.do?m=list&category_num=3" class="navigation-button">자유게시판</a>
    </div>
  
    <main> 
    
	<div class="center-wrapper">
        <div class="section-title">
           <p style="font-size:35px; font-weight:bold" >입시게시판</p> 
        </div>
        <div class="section-content-title">
            ${dto.post_subject}
        </div>
        
        <div class="section-content-info">
            <label>${dto.post_date}</label>
            <div style="display: flex; align-items: center; gap: 5px;">
	            <img src="<%=request.getContextPath()%>/post/assets/eye.png" alt="조회수" class="eye-icon" style="width: 20px; height: 20px;"/>
	            <label>조회수 ${dto.post_view}</label>
            </div>
            
            <label>${dto.nickname}</label>
            <button style="display: flex; align-items: center; gap: 5px; background-color:white; border:0px solid white; ">
                <img src="<%=request.getContextPath()%>/post/assets/like.png" alt="좋아요" class="like-icon" style="width: 20px; height: 20px;"/>
                <label>${dto.likes}</label>
            </button>
            <label>${dto.post_num}</label>
            <label>신고하기</label>
            
     	   </div> 	
     	    <div class="section-content-body">
           		 <p>${dto.post_content}</p>
    	    </div>    
		</div>
        <div id="commentArea"></div>
    </main>

    <footer>
      <p>회사소개 | 이용약관 | 개인정보처리방침 등등</p>
      <p>© 1999 - 2025 dcinside. All rights reserved.</p>
    </footer>
  </div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="search.js"></script>



</body>
</html>
