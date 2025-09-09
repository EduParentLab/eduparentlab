<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>학부모정보통</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/main/layout.css" />
  <link rel="stylesheet" href="<%=request.getContextPath()%>/playlist2/playlist.css" />
</head>
<body>
  <div class="wrapper">
    <div id="headerArea"></div>
    <main>
      <div class="center-wrapper">
        <div class="section-banner">
          <a href="main/main.do">
            <img src="<%=request.getContextPath()%>/designer/assets/banner3.png" style="width:100%; height:auto;display: block;" />
          </a>
        </div>
        <div style="display:flex; flex-wrap:wrap; gap:10px; padding:10px;">
		  <button class="select-button" onclick="loadCategory('수시')">수시</button>
		  <button class="select-button" onclick="loadCategory('정보')">정보</button>
		  <button class="select-button" onclick="loadCategory('육아')">육아</button>
		</div>
        <h3 style="margin:10px;">학플리</h3>
        <div class="section-playlist" id="playlist-container">
        </div>
      </div>
    </main>
    <div id="footerArea"></div>
  </div>
  <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
  <script src="<%=request.getContextPath()%>/playlist2/playlist.js"></script>
  <script>
  const allPlaylists = [
    <c:forEach var="p" items="${playlist}" varStatus="status">
      {
        title: "${fn:escapeXml(p.title)}",
        link: "${fn:escapeXml(p.youtubeUrl)}",
        category: "${fn:trim(p.category)}"
      }<c:if test="${!status.last}">,</c:if>
    </c:forEach>
  ];
</script>
<script> const contextPath = "<%=request.getContextPath()%>";</script>
</body>
</html>