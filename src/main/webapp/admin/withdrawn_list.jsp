<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<button id="toggleNav">☰ 메뉴</button>

<div class="search-box" style="margin-left: 50px; margin-top: 20px; margin-right: 50px;">
    <select class="search-filter">
        <option value="title">제목</option>
        <option value="content">내용</option>
        <option value="title+content" selected>제목+내용</option>
    </select>
    <input type="text" class="search-input" placeholder="검색어를 입력하세요" />
    <button class="search-btn">🔍</button>
</div>



<div style="padding:50px; padding-top:20px;">
<table class="notice-table">
<thead>
    <tr>
    <th>이메일</th>
    <th>닉네임</th>
    <th>성별</th>
    <th>생년월일</th>
    <th>이름</th>
    <th>전화번호</th>
    <th>가입일</th>
    </tr>
</thead>
<tbody>
    <c:choose>
	    <c:when test="${empty ghost}">
	        <tr>
	        <td align='center' colspan="7">탈퇴회원 없음</td>
	        </tr>
	    </c:when>
	    <c:otherwise>
	        <c:forEach items="${ghost}" var="dto">
	        <tr>
				<td align='center'><a href='../post/post.do?m=content&code=${dto.email}'>${dto.email}</td>			
				<td align='center'>${dto.nickname}</td>		
				<td align='center'>${dto.gender}</td>
				<td align='center'>${dto.birth}</td>
				<td align='center'>${dto.name}</a></td>						
				<td align='center'>${dto.phone}</td>
				<td align='center'>${dto.cdate}</td>
			</tr>		
	        </c:forEach>
	    </c:otherwise>	    
	</c:choose>	
</tbody>
</table>
</div>


<div class="pagination">
    <a href="#" class="page current">1</a>
    <a href="#" class="page">2</a>
    <a href="#" class="page">3</a>
    <span class="next-disabled">▶▶</span>
</div>
