<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>학부모정보통</title>
  <!-- ▶ CSS 경로 수정 -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/main/layout.css" />
  <link rel="stylesheet" href="<%=request.getContextPath()%>/playlist2/playlist.css" />
</head>

<body>
  <div class="wrapper">
    <div id="headerArea"></div>
    <main>
      <div class="center-wrapper">
        <!-- 공지 구조 유지 -->
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

        <!-- ▶ 배너 -->
        <div class="section-banner">
          <a href="#">
            <img src="<%=request.getContextPath()%>/designer/assets/banner.jpg" style="width:100%; height:auto;display: block;" />
          </a>
        </div>

        <!-- ▶ 카타고리 선택 -->
        <div style="display:flex; flex-wrap:wrap; gap:10px; padding:10px;">
  <button class="select-button" onclick="loadCategory('수시')">수시</button>
  <button class="select-button" onclick="loadCategory('정보')">정보</button>
  <button class="select-button" onclick="loadCategory('육아')">육아</button>
</div>

        <!-- ▶ 학플리 -->
        <h3 style="margin:10px;">학플리</h3>
        <div class="section-playlist" id="playlist-container">
          <!-- JS 로 삽입 계획 -->
        </div>

      </div>
    </main>
    <div id="footerArea"></div>
  </div>

  <!-- ▶ JS -->
  <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
  <script src="<%=request.getContextPath()%>/playlist2/playlist.js"></script>
  <script src="<%=request.getContextPath()%>/main/main_page.js"></script>
  <script>
  const allPlaylists = [
	    <c:forEach var="item" items="${playlist}" varStatus="loop">
	      {
	        title: "${item.title}",
	        link: "${item.link}",
	        category: "${item.category}"
	      }<c:if test="${!loop.last}">,</c:if>
	    </c:forEach>
	  ];
</script>
</body>
</html>