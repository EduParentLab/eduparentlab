<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/post/css/comment.css" />
</head>
<body>
  <div id="commentList">
	<!-- 댓글상단 -->
	<div style="display: flex; justify-content: center; gap: 10px; margin-top: 20px;">
            <div style="display: flex; align-items: center; gap: 5px; width: 50%; padding-left: 10px;">
                <P>댓글 ${fn:length(comment)}</P>
            </div>
            <div style="display: flex; align-items: center; justify-content:right; gap: 5px; width: 50%; padding-right: 10px;">
                <button class="align-button " style="border-right:solid black;" data-latest="true">최신순</button>
                <button class="align-button" data-latest="false">오래된 순</button>
            </div>
    </div>
    
    <!-- 새 댓글 입력폼 -->
	<div class="section-content-comment-input">
        <form id="commentForm" action="comment/comment.do?m=insert" method="post">
          <input type="hidden" name="post_num" value="${post_num}">
          <textarea name="content" class="comment-input" placeholder="댓글을 입력하세요."></textarea>
            <div style="display: flex; justify-content: flex-end; width: 100%;">
              <button class="comment-submit-btn" style="margin:0px;padding:0px; width: 150px; height: 50px;background-color: rgb(192, 165, 165);border:solid rgb(255, 255, 255); border-radius: 5px; cursor: pointer;">
              댓글 작성
              </button>
            </div>
        </form>
    </div>
    
    	<!-- 댓글 리스트 -->
		<div class="section-content-comment-list">
		  <c:forEach var="c" items="${comment}">
		  
		  <!-- 부모댓글 -->
			<div class="section-content-comment" data-comment-num="${c.comment_num}">
	        	<div class="comment-original" style="margin-bottom:0px;padding:0px 0px; display: flex; flex-direction: column; gap:0px; width: 1200px; border:2px solid black;margin-top:10px;">
		            <div class="33"style="width: 500px;
		            display: flex;
		            justify-content:flex-start;
		            align-items: center;
		            padding: 10px;
		            border-bottom: 1px solid #ffffff;
		            gap:10px;
		            border:solid rgb(255, 255, 255);">
		                <div class="comment-writer">
		                	<label>${c.nickName}</label>
		                </div>
		                <div><fmt:formatDate value="${c.comment_date}" pattern="yyyy-MM-dd"/></div>
		                
		                <button class="editBtn" type="submit">수정</button>
		                <button class="deleteBtn" type="submit">삭제</button>
		                <button class="recommentBtn" type="submit">답글</button>
		            </div>
		            <div style="padding: 10px; white-space: pre-line;" border-bottom: 1px solid #ddd; margin-top:0px; border:solid rgb(255, 255, 255);">
		                <span class="content">${c.comment_content}</span>
		            </div>
	        	</div>
		    
		    
		    <!-- 답댓글 리스트 -->
		   <c:if test="${not empty c.recomments}">
			 <div class="section-content-recomment-list">
				<c:forEach var="recomment" items="${c.recomments}">
				<div class="section-content-recomment" data-comment-num="${recomment.comment_num}">
			        <div class="recomment-original" style="border:solid rgb(243, 233, 233); margin-bottom:0px;padding:0px 0px; display: flex; flex-direction: column; gap:0px; width: 95%;">
			            <div class="34" style="display: flex; justify-content:flex-start; align-items: center; padding: 10px; border-bottom: 1px solid #ddd; gap:20px; border:solid rgb(255, 255, 255);">
			                <div class="recomment-writer">${recomment.nickName}</div>
			                <div><fmt:formatDate value="${c.comment_date}" pattern="yyyy-MM-dd"/></div>
			                
			                <button class="editBtn" type="submit">수정</button>
		                	<button class="deleteBtn" type="submit">삭제</button>
			            </div>
			            <div style="padding: 10px; border-bottom: 1px solid #ddd; margin-top:0px; border:solid rgb(255, 255, 255);">
			                <label>
			                    <img src="post/assets/reply.png" alt="대댓글" class="reply-icon" style="width: 20px; height: 20px; white-space: pre-line;">
			                    <span class="content">${recomment.comment_content}</span>
			                </label>
			            </div>
			        </div>
			    </div>
		      </c:forEach>
		    </div>
		  </c:if>
		    
		    <!-- 답댓글 폼 -->
	        <div class="section-content-recomment-input" style="display:none;">
	            <form class="recommentForm" style="1200px;">
	                <input type="hidden" name="post_num" value="${c.post_num}">
	                <input type="hidden" name="parent_num" value="${c.comment_num}">
	                <textarea style="width:1200px; height:105px;" name="content" placeholder="답글 입력"></textarea>
	                <button class="editBtn" type="submit">등록</button>
	            </form>
	        </div>
	       </div>
		 </c:forEach>	  	
        </div>
      </div>		
         
                    
	<!-- ◀▶ 페이지네이션 -->
	<div id="pagination" style="width:1210px;text-align:center;justify-content:center">
		<div class="pagination">
		    <c:if test="${paging.hasPrev()}">
		        <a href="#" data-page="${paging.startPage - 1}">◀</a>
		    </c:if>
		
		    <c:forEach var="i" begin="${paging.startPage}" end="${paging.endPage}">
		        <a href="#" data-page="${i}" class="${i == paging.currentPage ? 'active' : ''}">${i}</a>
		    </c:forEach>
		
		    <c:if test="${paging.hasNext()}">
		        <a href="#" data-page="${paging.endPage + 1}">▶</a>
		    </c:if>
		</div>
	</div>
</body>
</html>
