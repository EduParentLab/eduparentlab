<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="util.FileUtil" %>


<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>학부모정보통</title>
   <link rel="stylesheet" href="<%=request.getContextPath()%>/main/layout.css" />
  <link rel="stylesheet" href="<%=request.getContextPath()%>/post/css/board_content.css" />
  <link rel="stylesheet" href="<%=request.getContextPath()%>/post/css/comment.css" />
</head>
<body>
  <div class="wrapper">
  <div id="headerArea"></div>
    <main> 
	<div class="center-wrapper">
      <div class="section-title">
		  <c:choose>
		    <c:when test="${dto.category_num == 1}">
		      <p style="font-size:35px; font-weight:bold">자유게시판</p>
		    </c:when>
		    <c:when test="${dto.category_num == 2}">
		      <p style="font-size:35px; font-weight:bold">입시정보</p>
		    </c:when>
		    <c:when test="${dto.category_num == 3}">
		      <p style="font-size:35px; font-weight:bold">고등학교</p>
		    </c:when>
		    <c:when test="${dto.category_num == 4}">
		      <p style="font-size:35px; font-weight:bold">공지사항</p>
		    </c:when>
		  </c:choose>
		</div>   
        <div class="section-content-title">
            <div style="width: 100%;">
             <label style="color:#9b59b6;font-size:24px">${dto.post_subject}</label>
            </div>               
        </div>     
        <div class="section-content-info">    
        		<td>
					<fmt:formatDate value="${dto.post_date}" pattern="yyyy-MM-dd"/>
				</td>
            <div style="display: flex; align-items: center; gap: 5px;">
	            <img src="<%=request.getContextPath()%>/post/assets/eye.png" alt="조회수" class="eye-icon" style="width: 20px; height: 20px;"/>
	            <label>${dto.post_view}</label>
            </div>
            
            <label>${dto.nickname}</label>
          <form id="likesForm">
           <input type= "hidden" name="post_num" value="${dto.post_num}">                    	
            <button type="submit" class="likes-button" style="display: flex; align-items: center; gap: 5px; background-color:white; border:0px solid white; ">
                <img src="<%=request.getContextPath()%>/post/assets/like.png" alt="좋아요" class="like-icon" style="width: 20px; height: 20px;"/>
                <label id="likes-count-${dto.post_num}">${dto.likes}</label>
            </button>        
          </form> 
           <c:forEach var="file" items="${fileList}">
			  <div style="display:flex; align-items:center; margin-left:10px;">
			    <img src="<%=request.getContextPath()%>/post/assets/file.svg"
			         style="width:20px; height:20px; margin-right:5px;" alt="파일 아이콘"/>
			    <a href="${pageContext.request.contextPath}/download.do?file=${file.file_name}"          
			       style="color:blue; text-decoration: underline;">
			      ${file.file_origin_name}
			    </a>
			  </div>
			</c:forEach>
		</div>      
   	 <div class="section-content-body"> 
		  <c:forEach var="file" items="${fileList}">
			  <c:if test="${file.image}">
			    <div style="width:100%; text-align:center; margin:10px 0;">
			      <img src="${pageContext.request.contextPath}/download.do?file=${file.file_name}&mode=view"
			           alt="${file.file_origin_name}"
			           style="width:400px; height:auto;"/>
			    </div>
			  </c:if>
			</c:forEach>	
  		 <div style="margin-bottom:20px; white-space: pre-line;">
		   <p>${dto.post_content}</p>
		</div>
  	  </div>
		<c:if test="${canEdit or canDelete}">
  <div style="display:flex;
              justify-content:flex-end;
              align-items:center;
              border-bottom:1px solid black;
              gap:5px;
              padding:15px;">
		    <c:if test="${canEdit}">
		      <form action="<%=request.getContextPath()%>/post.do" method="get" style="display:inline;">
		        <input type="hidden" name="m" value="edit" />
		        <input type="hidden" name="seq" value="${dto.post_num}" />
		        <input type="hidden" name="path" value="${path}" />
		        <button type="submit" style="
		            width: 50px;
		            height: 25px;
		            background-color: #8dc4a4;
		            color: #333;
		            font-size: 12px;
		            font-weight: bold;
		            border: none;
		            border-radius: 10px;
		            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
		            transition: all 0.2s ease;
		            cursor: pointer;"
		          onmouseover="this.style.backgroundColor='#ffc2dc'"
		          onmouseout="this.style.backgroundColor='#8dc4a4'">
		          수정
		        </button>
		      </form>
		    </c:if>
		    <c:if test="${canDelete}">
		      <form action="<%=request.getContextPath()%>/post.do?m=delete" method="post" style="display:inline;">
		        <input type="hidden" name="seq" value="${dto.post_num}" />
		        <input type="hidden" name="category_num" value="${dto.category_num}" />
		        <input type="hidden" name="path" value="${path}" /> <!-- 관리자 페이지용 -->
		        <button type="submit" style="
		            width: 50px;
		            height: 25px;
		            background-color: #bc665c;
		            color: #333;
		            font-size: 12px;
		            font-weight: bold;
		            border: none;
		            border-radius: 10px;
		            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
		            transition: all 0.2s ease;
		            cursor: pointer;"
		          onclick="return confirm('정말 삭제하시겠습니까?');"
		          onmouseover="this.style.backgroundColor='#b5dcfb'"
		          onmouseout="this.style.backgroundColor='#bc665c'">
		          삭제
		        </button>
		      </form>
		    </c:if>
		  </div>
		</c:if>
        <div id="commentArea" style="display:flex; width:1210px; flex-direction:column;">
          <div id="commentList" style="display:flex; width:1210px; flex-direction:column;"></div>
          <div id="pagination" style="display:flex; width:1210px; text-align:center;justify-content:center;margin-top:20px;"></div>
        </div>
    </main>
    <div id="footerArea"></div>
  </div>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="<%=request.getContextPath()%>/post/js/board_content.js"></script>
<script> const contextPath = "<%=request.getContextPath()%>";</script>
</body>
</html>

