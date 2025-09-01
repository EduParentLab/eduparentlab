<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>학부모정보통</title>
  <link rel="stylesheet" href="layout.css"/>
  <link rel="stylesheet" href="all_search.css"/>
</head>


<body>
  <div class="wrapper">
    <div id="headerArea"></div>
    <main>
      <div class="center-wrapper">
        <h3 style="font-size:30px; margin:10px;"> 전체검색 </h3>
        <div class="section-notice">
	        <c:forEach var="notice" items="${notice}">
	           <div style="display:flex; justify-content:center; width: 5%;">
	            <label style="border:2px solid red; color:red; font-weight: bold;">공지</label>
	           </div>
	           <div style="display:flex; justify-content:flex-start; width: 50%;">
	            <a href="${pageContext.request.contextPath}/post.do?m=content&seq=${notice.post_num}" style="font-weight:bold">${notice.post_subject}</a>
	           </div>
	           <div style="display:flex; justify-content:flex-end; width: 40%;">
	            <label>${notice.post_date}</label>
	           </div>
	        </c:forEach>
        </div>

        
        <div class="section-banner">
          <a href="#">
           <img src="./assets/banner.jpg" style="width:100%; height:auto;display: block;" />
          </a>
        </div>
		<c:if test="${empty searchResult}">
		    <p>검색 결과가 없습니다.</p>
		</c:if>
        <div class="section-board">
            <div style="display:flex;flex-direction: column;">
                <label style="font-size:28px;font-weight: bold;"> 자유게시판 </label>
                <c:forEach var="result" items="${searchResult}">
                	<c:if test="${result.category_num == 1}">
		                <a href="${pageContext.request.contextPath}/post.do?m=content&seq=${result.post_num}" style="font-size:20px;color:blue;">
		                ${result.post_subject}
		                </a>
		                <a href="#" style="font-size:16px;color:black;text-decoration: none;">
		                ${result.post_content}
		                </a>
	                </c:if>
                </c:forEach>
            </div>
             <div style="display:flex; margin-left: auto;"><a href="${pageContext.request.contextPath}/post.do?m=list&category_num=1">더보기</a></div>
        </div>

        <div class="section-board">
            <div style="display:flex;flex-direction: column;">
                <label style="font-size:28px;font-weight: bold;"> 입시정보 </label>
                <a href="#" style="font-size:20px;color:blue;"> 입시는 재밌다</a>
                <a href="#" style="font-size:16px;color:black;text-decoration: none;">입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다</a>
            </div>
            <div style="display:flex;flex-direction: column;">
                <a href="#" style="font-size:20px;color:blue;"> 입시는 재밌다</a>
                <a href="#" style="font-size:16px;color:black;text-decoration: none;">입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다</a>
            </div>
            <div style="display:flex;flex-direction: column;">
                <a href="#" style="font-size:20px;color:blue;"> 입시는 재밌다</a>
                <a href="#" style="font-size:16px;color:black;text-decoration: none;">입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다</a>
            </div>
            <div style="display:flex;flex-direction: column;">
                <a href="#" style="font-size:20px;color:blue;"> 입시는 재밌다</a>
                <a href="#" style="font-size:16px;color:black;text-decoration: none;">입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다</a>
            </div>
            <div style="display:flex;flex-direction: column;">
                <a href="#" style="font-size:20px;color:blue;"> 입시는 재밌다</a>
                <a href="#" style="font-size:16px;color:black;text-decoration: none;">입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다</a>
            </div>
             <div style="display:flex; margin-left: auto;"><a href="#">더보기</a></div>
        </div>

        <div class="section-board">
            <div style="display:flex;flex-direction: column;">
                <label style="font-size:28px;font-weight: bold;"> 고등학교 </label>
                <a href="#" style="font-size:20px;color:blue;"> 입시는 재밌다</a>
                <a href="#" style="font-size:16px;color:black;text-decoration: none;">입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다</a>
            </div>
            <div style="display:flex;flex-direction: column;">
                <a href="#" style="font-size:20px;color:blue;"> 입시는 재밌다</a>
                <a href="#" style="font-size:16px;color:black;text-decoration: none;">입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다</a>
            </div>
            <div style="display:flex;flex-direction: column;">
                <a href="#" style="font-size:20px;color:blue;"> 입시는 재밌다</a>
                <a href="#" style="font-size:16px;color:black;text-decoration: none;">입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다</a>
            </div>
            <div style="display:flex;flex-direction: column;">
                <a href="#" style="font-size:20px;color:blue;"> 입시는 재밌다</a>
                <a href="#" style="font-size:16px;color:black;text-decoration: none;">입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다</a>
            </div>
            <div style="display:flex;flex-direction: column;">
                <a href="#" style="font-size:20px;color:blue;"> 입시는 재밌다</a>
                <a href="#" style="font-size:16px;color:black;text-decoration: none;">입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다입시는 재밌다</a>
            </div>
             <div style="display:flex; margin-left: auto;"><a href="#">더보기</a></div>
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
<script src="all_search.js"></script>



</body>
</html>
