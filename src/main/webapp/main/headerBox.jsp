<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<header style="padding-left:40px;padding-right:40px;">
  <div class="logo">

    <a href="/educationlab/main/main.do">
      <img src="<%=request.getContextPath()%>/designer/assets/logoremoveback.png" />

    </a>
  </div>
  <div class="search-container" style="justify-content:center;">
    <div class="search-bar">
      <div class="search-logo">H</div>
      	<form action="${pageContext.request.contextPath}/main/main.do" method="get" style="margin:0px;padding:0px;display:flex;">
      		<input type="hidden" name="m" value="search" />
		    <input style="width:200px;"type="text" id="keyword" name="keyword" value="${param.keyword}" placeholder="검색어를 입력해 주세요." />
		    <button type="submit" class="search-btn">🔍</button>
		</form>
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
  <div class="login">
    <c:choose>
      <c:when test="${empty loginOkUser}">
        <a href="${pageContext.request.contextPath}/login/login.do?m=form"><button>로그인</button></a>
        <a href="${pageContext.request.contextPath}/register/register.jsp"><button>회원가입</button></a>
      </c:when>
      <c:otherwise>
        <label style="color:green">${loginOkUser.name}</label>님 안녕하세요
        <a href="${pageContext.request.contextPath}/mypage/mypage.do"><button>마이페이지</button></a>
        <a href="${pageContext.request.contextPath}/login/login.do?m=logout"><button>로그아웃</button></a>
        <c:if test="${loginOkUser.role_num == 1}">
        <a href="${pageContext.request.contextPath}/admin/admin.do"><button>관리자페이지</button></a>
        </c:if>
      </c:otherwise>
    </c:choose>
  </div>
  
</header>

<div class="navigation-button-container">
  <a href="${pageContext.request.contextPath}/post.do?m=list&category_num=1" class="navigation-button">자유게시판</a>
  <a href="${pageContext.request.contextPath}/post.do?m=list&category_num=2" class="navigation-button">입시정보</a>
  <a href="${pageContext.request.contextPath}/post.do?m=list&category_num=3" class="navigation-button">고등학교</a>
  <a href="${pageContext.request.contextPath}/post.do?m=list&category_num=4" class="navigation-button">공지사항</a>
  <a href="${pageContext.request.contextPath}/playlist.do?" class="navigation-button">학플리</a>
</div>
</body>
</html>