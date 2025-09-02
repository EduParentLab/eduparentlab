<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>학부모정보통</title>
  <link rel="stylesheet" href="layout.css" />
  <link rel="stylesheet" href="all_search.css" />
</head>


<body>
  <div class="wrapper">
    <div id="headerArea"></div>
    <main>
    
      <div class="center-wrapper">
        <h3 style="font-size:46px; margin:10px; margin-bottom:0px "> 전체검색 </h3>
        
		
        <label style="font-size:30px; margin:10px; font-weight:bold;margin-top:0px"> ${keyword} 관련 총
	                     <label style="font-size:30px; margin:10px; color:red;font-weight:bold;margin-top:0px;margin-left:0px">
	                        <c:set var="totalCount" value="0"/>
				            <c:forEach var="list" items="${searchMap.values()}">
				              <c:set var="totalCount" value="${totalCount + fn:length(list)}"/>
				            </c:forEach>
	                        ${totalCount}건 
	                     </label> 
        </label>

		<c:forEach var="cateNum" items="${categories.keySet()}">
		    <div class="section-board">
		        <div style="display:flex;flex-direction: column;">
		            <label style="font-size:28px;font-weight: bold;">${categories[cateNum]}</label>
		
		            <c:choose>
		                <c:when test="${not empty searchMap[cateNum]}">
		                    <c:forEach var="result" items="${searchMap[cateNum]}">
		                        <a href="${pageContext.request.contextPath}/post.do?m=content&seq=${result.post_num}" style="font-size:20px;color:blue;">
		                          ${result.post_subject}
		                        </a>
		                        <a href="#" style="font-size:16px;color:black;text-decoration: none;">
		                          ${result.post_content}
		                        </a>
		                    </c:forEach>
		                </c:when>
		                <c:otherwise>
		                    <p>검색 결과가 없습니다.</p>
		                </c:otherwise>
		            </c:choose>
		
		            
		        </div> <!-- flex column 닫기 -->
		        <div style="display:flex; margin-left: auto;">
			            <a href="${pageContext.request.contextPath}/post.do?m=list&category_num=${cateNum}&type=title&keyword=${keyword}">
			            더보기
			            </a>
		        </div>
		    </div> <!-- section-board 닫기 -->
		    <div class="divide-block" style="background-color:white;width: 100%;height:15px;"></div>
		</c:forEach>

        
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
