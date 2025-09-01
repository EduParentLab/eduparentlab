<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>학부모정보통</title>
  <!-- CSS 경로 -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/post/css/layout.css" />
  <link rel="stylesheet" href="<%=request.getContextPath()%>/post/css/board_list.css" />
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


    <!-- 네비게이션 -->
    <div class="navigation-button-container">
      <a href="<%=request.getContextPath()%>/post.do?m=list&category_num=1" class="navigation-button">자유게시판</a>
      <a href="<%=request.getContextPath()%>/post.do?m=list&category_num=2" class="navigation-button">입시정보</a>
      <a href="<%=request.getContextPath()%>/post.do?m=list&category_num=3" class="navigation-button">고등학교</a>
       <a href="<%=request.getContextPath()%>/post.do?m=list&category_num=4" class="navigation-button">공지사항</a>
    </div>

    <main>
      <div class="center-wrapper">

   
    <!-- 타이틀 -->
	<div class="section-title">
	  <c:choose>
	    <c:when test="${category_num == 1}">
	      <p style="font-size:35px; font-weight:bold">자유게시판</p>
	    </c:when>
	    <c:when test="${category_num == 2}">
	      <p style="font-size:35px; font-weight:bold">입시정보</p>
	    </c:when>
	    <c:when test="${category_num == 3}">
	      <p style="font-size:35px; font-weight:bold">고등학교</p>
	    </c:when>
	    <c:when test="${category_num == 4}">
	      <p style="font-size:35px; font-weight:bold">공지사항</p>
	    </c:when>
	    <c:otherwise>
	      <p style="font-size:35px; font-weight:bold">게시판</p>
	    </c:otherwise>
	  </c:choose>
	</div>
    
    <!-- 카테고리 안내문구 -->
    <div class="section-discription">
      <c:choose>
        <c:when test="${category_num eq '1'}">
          본 게시판은 자유게시판입니다.
        </c:when>
        <c:when test="${category_num eq '2'}">
          본 게시판은 입시정보 게시판입니다.
        </c:when>
        <c:when test="${category_num eq '3'}">
          본 게시판은 고등학교 게시판입니다.
        </c:when>
        <c:when test="${category_num eq '4'}">
          본 게시판은 공지사항입니다.
        </c:when>
        <c:otherwise>
          본 게시판입니다.
        </c:otherwise>
      </c:choose>
    </div>

       <!-- 정렬 기준 선택 -->
		<div class="section-pagingfilter">
		  <form action="<%=request.getContextPath()%>/post.do" method="get">
		    <input type="hidden" name="m" value="list"/>
		    <input type="hidden" name="category_num" value="${category_num}"/>
		    <select name="sort" class="dropdown-select" onchange="this.form.submit()">
		      <option value="latest" ${param.sort == 'latest' ? 'selected' : ''}>최신순</option>
		      <option value="views" ${param.sort == 'views' ? 'selected' : ''}>조회수순</option>
		    </select>
		  </form>
		</div>
       
        <!-- 게시판 목록 -->
        <div class="section-board-list">
          <table class="board-table">
            <thead>
              <tr>
                <th style="width: 50%;">제목</th>
                <th style="width: 10%;">글쓴이</th>
                <th style="width: 20%;">작성일</th>
                <th style="width: 10%;">조회수</th>
                <th style="width: 10%;">좋아요</th>
              </tr>
            </thead>
            <tbody>
              <c:choose>
                <c:when test="${empty list}">
                  <tr><td colspan="5">등록된 게시글이 없습니다.</td></tr>
                </c:when>
                <c:otherwise>
                  <c:forEach var="dto" items="${list}">
                    <tr>
                      <td>
                        <a href="<%=request.getContextPath()%>/post.do?m=content&seq=${dto.post_num}&category_num=${category_num}">
                          ${dto.post_subject}
                        </a>
                      </td>
                      <td>${dto.nickname}</td>
                      <td>${dto.post_date}</td>
                      <td>${dto.post_view}</td>
                      <td>${dto.likes}</td>
                    </tr>
                  </c:forEach>
                </c:otherwise>
              </c:choose>
            </tbody>
          </table>
        </div>

        <!-- 글쓰기 버튼 -->
        <div class="section-write-btn" style="gap:10px">
          <img src="<%=request.getContextPath()%>/post/assets/plus-circle.svg" class="icon" alt="글쓰기 아이콘" style="width: 40px; height: 40px;"/>
          <button class="write-btn" 
                  onclick="location.href='<%=request.getContextPath()%>/post.do?m=input&category_num=${category_num}'"
                  style="background-color: rgb(164, 183, 247); padding:10px; border-radius: 10px;">
            글쓰기
          </button>
        </div>

        <!-- 검색 -->
        <div class="section-searchbar">
          <form action="<%=request.getContextPath()%>/post.do" method="get" class="search-bar-container">
            <input type="hidden" name="m" value="list"/>
            <input type="hidden" name="category_num" value="${category_num}"/>

            <div class="dropdown-wrap">
              <select name="type" class="dropdown-btn">
                <option value="title" ${param.type == 'title' ? 'selected' : ''}>제목</option>
                <option value="content" ${param.type == 'content' ? 'selected' : ''}>내용</option>
                <option value="title_content" ${param.type == 'title_content' ? 'selected' : ''}>제목+내용</option>
                <option value="writer" ${param.type == 'writer' ? 'selected' : ''}>작성자</option>
              </select>
            </div>

            <input type="text" name="keyword" class="search-input" value="${param.keyword}" placeholder="검색어를 입력하세요" />
            <button type="submit" class="search-button">
              <img src="<%=request.getContextPath()%>/post/assets/search-icon.png" alt="검색" class="search-icon" />
            </button>
          </form>
        </div>

        <!-- 페이징 -->
        <div class="section-paging">
          <div class="pagination">
            <c:if test="${paging.hasPrev()}">
              <a href="post.do?m=list&page=${paging.startPage - 1}&rows=${param.rows}&sort=${param.sort}&type=${param.type}&keyword=${param.keyword}&category_num=${category_num}">◀</a>
            </c:if>

            <c:forEach var="i" begin="${paging.startPage}" end="${paging.endPage}">
              <a href="post.do?m=list&page=${i}&rows=${param.rows}&sort=${param.sort}&type=${param.type}&keyword=${param.keyword}&category_num=${category_num}"
                 class="${i == paging.currentPage ? 'current' : ''}">${i}</a>
            </c:forEach>

            <c:if test="${paging.hasNext()}">
              <a href="post.do?m=list&page=${paging.endPage + 1}&rows=${param.rows}&sort=${param.sort}&type=${param.type}&keyword=${param.keyword}&category_num=${category_num}">▶</a>
            </c:if>
          </div>
        </div>
      </div>
    </main>

    <footer>
      <p>회사소개 | 이용약관 | 개인정보처리방침 등등</p>
      <p>© 1999 - 2025 dcinside. All rights reserved.</p>
    </footer>
  </div>

  <!-- JS 경로 -->
  <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
  <script src="<%=request.getContextPath()%>/post/board_list.js"></script>
</body>
</html>
