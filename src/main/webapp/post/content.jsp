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
        <!-- ▼ 아래 추천 검색어 목록 ▼ -->
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
           <p style="font-size:35px; font-weight:bold">${dto.post_subject}</p> 
        </div>
        
        <div style="display: flex; align-items: center; gap: 5px;">
             <span>${dto.post_date}</span>
            <img src="<%=request.getContextPath()%>/post/assets/eye.png" alt="조회수" class="eye-icon" style="width: 20px; height: 20px;"/>
            <label>조회수 ${dto.post_view}</label>
            </div>
            <label>rydbrxkarn@gmail.com</label>
            <div style="display: flex; align-items: center; gap: 5px;">
                <img src="<%=request.getContextPath()%>/post/assets/like.png" alt="좋아요" class="like-icon" style="width: 20px; height: 20px;"/>
                <label>25</label>
            </div>
            <label>#2357</label>
            <label>신고하기</label>
             <div class="section-content-info">
           		<span>${dto.post_content}</span>
      		  </div>
        </div>
        
        
      
          
            
           
      
        <div style="display: flex; justify-content: center; gap: 10px; margin-top: 20px;">
            <div style="display: flex; align-items: center; gap: 5px; width: 50%; padding-left: 10px;">
                <P>댓글 4</P>
            </div>
            <div style="display: flex; align-items: center; justify-content:right; gap: 5px; width: 50%; padding-right: 10px;">
                <button class="align-button" style="border-right:solid black;">인기순</button>
                <button class="align-button " style="border-right:solid black;">최신순</button>
                <button class="align-button">오래된 순</button>
            </div>
        </div>

        <div class="section-content-comment-input">
          <textarea class="comment-input" placeholder="댓글을 입력하세요."></textarea>
            <div style="display: flex; justify-content: flex-end; width: 100%;">
              <button class="comment-submit-btn" style="margin:0px;padding:0px; width: 150px; height: 50px;background-color: rgb(192, 165, 165);border:solid rgb(255, 255, 255); border-radius: 5px; cursor: pointer;">
              <label style="margin:0px;padding:0px;font-size:20px; font-weight:bold;">댓글 작성</label>
              </button>
            </div>
        </div>

        <div class="section-content-comment">
          <div style="margin-bottom:0px;padding:0px 0px; display: flex; flex-direction: column; gap:0px; width: 90%;">
          <div style="display: flex; justify-content:flex-start; align-items: center; padding: 10px; border-bottom: 1px solid #ffffff; gap:20px; border:solid rgb(255, 255, 255);">
            <div>
              햄토리
            </div>
            <div>
              2024.08.23
            </div>
            <div style="display: flex; align-items: center; gap: 5px;">
                <img src="<%=request.getContextPath()%>/post/assets/like.png" alt="좋아요" class="like-icon" style="width: 20px; height: 20px;"/>
              	
              <div>
                24
              </div>
            </div>
          </div>
          <div style="padding: 10px; border-bottom: 1px solid #ddd; margin-top:0px; border:solid rgb(255, 255, 255);">
            <label>
              저도 이 시험 준비하고 있는데, 정보 감사합니다!
            </label>
          </div>
          </div>
        </div>

        
        <div class="section-content-recomment">
          <div style="border:solid rgb(243, 233, 233); margin-bottom:0px;padding:0px 0px; display: flex; flex-direction: column; gap:0px; width: 90%;">
          <div style="display: flex; justify-content:flex-start; align-items: center; padding: 10px; border-bottom: 1px solid #ddd; gap:20px; border:solid rgb(255, 255, 255);">
            <div>
              햄토리
            </div>
            <div>
              2024.08.23
            </div>
            <div style="display: flex; align-items: center; gap: 5px;">
                <img src="<%=request.getContextPath()%>/post/assets/like.png" alt="좋아요" class="like-icon" style="width: 20px; height: 20px;"/>
              <div>
                24
              </div>
            </div>
          </div>
          <div style="padding: 10px; border-bottom: 1px solid #ddd; margin-top:0px; border:solid rgb(255, 255, 255);">
            <label>
              
             <img src="<%=request.getContextPath()%>/post/assets/reply.png" alt="대댓글" class="reply-icon" style="width: 20px; height: 20px;">
              저도 이 시험 준비하고 있는데, 정보 감사합니다!저도 이 시험 준비하고 있는데, 정보 감사합니다!저도 이 시험 준비하고 있는데, 정보 감사합니다!
            </label>
          </div>
          </div>
        </div>




        <div class="section-content-recomment-input">
          <div style="display: flex; justify-content: flex-end; width: 100%; align-items: center; gap:10px;">
               <textarea class="comment-input" placeholder="댓글을 입력하세요." style="width:90%; height:100px; border:solid rgb(0, 0, 0);"></textarea>
          </div>
          <div style="display: flex; justify-content: flex-end; width: 100%; margin-top:10px;">
            <button style="width:150px; height:40px;">등록</button>
          </div>

        </div>


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
