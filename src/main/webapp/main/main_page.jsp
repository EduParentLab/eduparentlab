<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>학부모정보통</title>
  <link rel="stylesheet" href="layout.css" />
  <link rel="stylesheet" href="main_page.css" />
</head>


<body>
  <div class="wrapper">
    <div id="headerArea"></div>
    <main>
      <div class="center-wrapper">
        <div class="section-banner">
          <a href="#">
           <img src="../designer/assets/banner2.png" style="width:100%; height:auto;display: block;" />
          </a>
        </div>
        <c:forEach var="notice" items="${notice}">
        <div class="section-notice">
	           <div style="display:flex; justify-content:center; width: 5%;">
	            <label style="border:2px solid red; color:red; font-weight: bold;">공지</label>
	           </div>
	           <div style="display:flex; justify-content:flex-start; width: 50%;">
	            <a href="${pageContext.request.contextPath}/post.do?m=content&seq=${notice.post_num}" style="font-weight:bold">${notice.post_subject}</a>
	           </div>
	           <div style="display:flex; justify-content:flex-end; width: 40%;">
	            <label>${notice.post_date}</label>
	           </div>
        </div>
        </c:forEach>
        <div class="section-popular-recent-posts">
             <div class="popular-board">
                <h3>인기 게시물</h3>
				<c:forEach var="popular" items="${popularList}">
	                <div class="board-item">
		                <div class="board-title">
		                  <a href="${pageContext.request.contextPath}/post.do?m=content&seq=${popular.post_num}" style="text-decoration: none;">${popular.post_subject}</a>
		                </div>
		                <div class="meta-info">
		                    <span>${popular.nickname}</span>
		                    <span><img src="https://cdn-icons-png.flaticon.com/512/847/847969.png" alt="clock" />${popular.post_date}</span>
		                    <span><img src="https://cdn-icons-png.flaticon.com/512/889/889140.png" alt="like" />${popular.post_view}</span>
		                </div>
	                </div>
			    </c:forEach>
            </div>


            <div class="popular-board">
                <h3>최신 게시물</h3>
                <c:forEach var="latest" items="${latestList}">
	                <div class="board-item">
		                <div class="board-title">
		                  <a style="text-decoration: none;" href="${pageContext.request.contextPath}/post.do?m=content&seq=${latest.post_num}">${latest.post_subject}</a>
		                </div>
		                <div class="meta-info">
		                    <span>${latest.nickname}</span>
		                    <span><img src="https://cdn-icons-png.flaticon.com/512/847/847969.png" alt="clock" />${latest.post_date}</span>
		                    <span><img src="https://cdn-icons-png.flaticon.com/512/889/889140.png" alt="like" />${latest.post_view}</span>
		                </div>
	                </div>
			    </c:forEach>
            </div>
        </div>
        <h3 style="margin:10px";>학플리</h3>
        
		<div class="section-playlist" id="playlist-container">
          <!-- JS로 삽입 -->
        </div>


        </div>
      </div>
    </main>
    <div id="footerArea"></div>
  </div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="main_page.js"></script>
<script src="${pageContext.request.contextPath}/playlist/playlist.js"></script>



</body>
</html>
