<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
  <style>
  	.top-bar { width: 100%; margin-bottom: 15px; text-align: right; }
  </style>
  <button id="toggleNav">☰ 메뉴</button>

  <div class="search-box" style="margin-left: 50px; margin-top: 20px; margin-right: 50px;">
      <select id="search-filter" class="search-filter">
          <option value="post_subject">제목</option>
          <option value="post_content">내용</option>
          <option value="subject+content">제목+내용</option>
      </select>
      <input type="text" id="noticeSearchInput" class="search-input" placeholder="검색어를 입력하세요" />
      <button id="noticeSearchBtn" class="search-btn">🔍</button>
  </div>

  <div style="text-align: right;">
      <button class="delete-btn" style="padding-right:50px; padding-top:20px;">🗑️</button>
  </div>



  <div style="padding:50px; padding-top:20px;">
  <table id="noticeTable" class="notice-table">
	 <thead>
	     <tr>
	     <th><input type="checkbox" /></th>
	     <th>글번호</th>
	     <th>글제목</th>      
	     <th>작성일</th>
	     <th>조회수</th>
	     <th>좋아요</th>	     
	     </tr>
	 </thead>
	 <tbody>
	     <c:choose>
			<c:when test="${empty notice}">
				<tr>
				<td align='center' colspan="6">작성한 글 없음</td>
				</tr>
			</c:when>
			<c:otherwise>
			  <c:forEach items="${notice}" var="dto">
			      <tr>
			        <td><input type="checkbox" /></td>
					<td align='center'>${dto.post_num}</td>
					<td id="post_subject" align='center'>
					<a href='../post.do?m=content&seq=${dto.post_num}'>${dto.post_subject}</a>
					</td>	
					<td align='center'>${dto.post_date}</td>		
					<td align='center'>${dto.post_view}</td>
					<td align='center'>${dto.likes}</td>
					<td id="post_content" style="position:absolute; left:-9999px;">${dto.post_content}</td>
				  </tr>
			  </c:forEach>
		   </c:otherwise>	    
	    </c:choose>		
     </tbody>
  </table>
  	<!-- ✏ 글쓰기 버튼 -->
	<div class="top-bar">
    	<a href="../post/input.jsp">➕ 글쓰기</a>
	</div>
  </div>


  <div class="pagination">
      <a href="#" class="page current">1</a>
      <a href="#" class="page">2</a>
      <a href="#" class="page">3</a>
      <a href="#" class="page">4</a>
      <a href="#" class="page">5</a>
      <a href="#" class="page">6</a>
      <a href="#" class="page">7</a>
      <a href="#" class="page">8</a>
      <a href="#" class="page">9</a>
      <a href="#" class="page">10</a>
      <a href="#" class="page">11</a>
      <a href="#" class="page">12</a>
      <a href="#" class="page">13</a>
      <a href="#" class="page">14</a>
      <a href="#" class="page">15</a>
      <span class="next-disabled">▶▶</span>
  </div>

