<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>학부모정보통</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/main/layout.css" />
  <link rel="stylesheet" href="<%=request.getContextPath()%>/post/css/board_list.css" />
</head>

<body>
  <div class="wrapper">
	<div id="headerArea"></div>
    <main style="background-color:white;">
      <div class="center-wrapper" style="background-color:white;" data-category="${category_num}">

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
    
    <div class="section-discription">
      <c:choose>
      	<c:when test="${category_num eq '1'}">
		  <div>
		    <label>💬 이곳은 학부모 정보통의 자유게시판입니다.</label><br/>
		    <label>아이들의 배움과 성장을 위해, 부모가 함께 지혜를 나누는 곳.</label><br/>
		    <label>입시 정보부터 생활 학습 팁까지 자유롭게 공유하며, 서로의 경험이 모두의 자산이 됩니다. 🌟</label>
		  </div>
		</c:when>
        <c:when test="${category_num eq '2'}">
		  <div>
		    <label>🎓 이곳은 입시에 관심 있는 모든 학부모와 학생들을 위한 열린 공간입니다.</label><br/>
		    <label>최신 입시 정보와 소식을 확인할 수 있을 뿐만 아니라, 서로의 경험과 고민을 나누며 함께 성장하는 커뮤니티입니다.</label><br/>
		    <label>입시는 혼자 준비하면 막막할 수 있지만, 🤗 함께 나누는 정보와 응원은 분명히 큰 힘이 됩니다.</label><br/>
		    <label>궁금한 점을 자유롭게 질문하시고, 알고 있는 정보를 나누어 주시면, 모두가 더 나은 길을 찾아갈 수 있습니다. ✨</label>
		  </div>
		</c:when>      
        <c:when test="${category_num eq '3'}">
		  <div>
		    <label>🎓 이곳은 고등학교 생활과 관련된 다양한 이야기를 나누는 공간입니다.</label><br/>
		    <label>📚 공부 방법, 🏫 학교 생활 팁, 🎭 동아리 활동, 📝 시험 후기 등 학생과 학부모님들이 함께 공감하고 공유할 수 있는 주제를 자유롭게 올려주세요.</label><br/>
		    <label>🤝 혼자 고민하기보다 함께 나누고 듣는 이야기가 더 큰 힘이 됩니다.</label>
		  </div>
		</c:when>  
        <c:when test="${category_num eq '4'}">
		  <div>
		    <label>📢 이곳은 학부모 정보통의 <b>공식 공지사항 게시판</b>입니다.</label><br/>
		    <label>사이트 운영 일정, 새로운 기능 추가, 서비스 점검 등 <b>중요한 안내사항</b>을 신속하고 정확하게 전달합니다.</label><br/>
		    <label>학부모님들께서 꼭 확인하셔야 할 내용이 게시되니, 새로운 공지가 등록되면 반드시 확인해 주시기 바랍니다. ✅</label>
		  </div>
		</c:when>  
        <c:otherwise>
          본 게시판입니다.
        </c:otherwise>
      </c:choose>
    </div>

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
                      <td>
						  <fmt:formatDate value="${dto.post_date}" pattern="yyyy-MM-dd"/>
					 </td>
                      <td>${dto.post_view}</td>
                      <td>${dto.likes}</td>
                    </tr>
                  </c:forEach>
                </c:otherwise>
              </c:choose>           
            </tbody>
          </table>
          <div id="preview-box" 
          style="position:absolute;
			  display:none;
			  border:1px solid #ccc;
			  background:#fff;
			  padding:5px;
			  z-index:9999;">
  <img id="preview-img" src="" style="max-width:200px; max-height:150px;" />
</div>  
      </div>  
		<c:if test="${loginOkUser.email=='admin@edu_parent.com' or category_num != '4'}">
	        <div class="section-write-btn" style="gap:10px">
	          <img src="<%=request.getContextPath()%>/post/assets/plus-circle.svg" class="icon" alt="글쓰기 아이콘" style="width: 40px; height: 40px;"/>
	          <button class="write-btn" 
	                  onclick="location.href='<%=request.getContextPath()%>/post.do?m=input&category_num=${param.category_num}'"
	                  style="background-color: rgb(164, 183, 247); padding:10px; border-radius: 10px;">
	            글쓰기
	          </button>
	        </div>
		</c:if>
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
      </div>
    </main>

    <footer>
      <p>회사소개 | 이용약관 | 개인정보처리방침 등등</p>
      <p>© 1999 - 2025 eduparents. All rights reserved.</p>
    </footer>
  </div>

  <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
  <script> const contextPath = "<%=request.getContextPath()%>";</script>
  <script src="<%=request.getContextPath()%>/post/js/board_list.js"></script>
</body>
</html>
