<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>학부모정보통</title>
  <link rel="stylesheet" href="../main/layout.css" />
  <link rel="stylesheet" href="playlist.css" />
</head>


<body>
  <div class="wrapper">
    <div id="headerArea"></div>
    <main>
      <div class="center-wrapper">
        <div class="section-notice">
           <div style="display:flex; justify-content:center; width: 5%;">
            <label style="border:2px solid red; color:red; font-weight: bold;">공지</label>
           </div>
           <div style="display:flex; justify-content:flex-start; width: 50%;">
            <a href="#" style="font-weight:bold">휴대폰 인증 오류 해결되었습니다.</a>
           </div>
           <div style="display:flex; justify-content:flex-end; width: 40%;">
            <label>2025.08.15</label>
           </div>
        </div>

        
        <div class="section-banner">
          <a href="#">
           <img src="../designer/assets/banner.jpg" style="width:100%; height:auto;display: block;" />
          </a>
        </div>
        <div style="display:flex; flex-wrap:wrap; gap:10px; padding:10px;">
             <button class="select-button" onclick="loadCategory('ipsi')">수시</button>
         	 <button class="select-button" onclick="loadCategory('jpop')">정보</button>
         	 <button class="select-button" onclick="loadCategory('classic')">육아</button>
        </div>


        <h3 style="margin:10px;">학플리</h3>
        <div class="section-playlist" id="playlist-container">
          <!-- JS로 삽입 -->
        </div>

			
			
        


      </div>
    </div>
  </main>


    <footer>
      <p>회사소개 | 이용약관 | 개인정보처리방침 등등</p>
      <p>© 1999 - 2025 dcinside. All rights reserved.</p>
    </footer>
  </div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="playlist.js"></script>
<script src="main_page.js"></script>



</body>
</html>
