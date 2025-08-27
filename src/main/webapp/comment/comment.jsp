<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="commentForm" action="comment.do?m=insert" method="post">
	    <input type="hidden" name="post_num" value="1">
	    <input type="hidden" name="email" value="user1@edu_parent.com">
	    <textarea name="content" rows="3" cols="50" placeholder="댓글을 입력하세요"></textarea>
	    <button type="submit">등록</button>
	</form>
	
	<ul id="commentList">
	  <c:forEach var="c" items="${comment}">
	      <li>
	          <span>${c.comment_content}</span>
	          <span>(${c.email})</span>
	          <span>${c.comment_date}</span>

 	          <!-- 삭제 폼 -->
	          <form action="comment.do?m=delete" method="post" style="display:inline;">
	              <input type="hidden" name="m" value="delete">
	              <input type="hidden" name="comment_num" value="${c.comment_num}">
	              <button type="submit">삭제</button>
	          </form>
	
	          <!-- 수정 폼 -->
	          <form action="comment.do?m=update" method="post" style="display:inline;">
	              <input type="hidden" name="m" value="update">
	              <input type="hidden" name="comment_num" value="${c.comment_num}">
	              <input type="text" name="comment_content" value="${c.comment_content}">
	              <button type="submit">수정</button>
	          </form>
	           
	          <!-- 답댓글 폼 -->
	          <form action="comment.do?m=recomment" method="post" style="display:inline;">
		            <input type="hidden" name="post_num" value="1">
				    <input type="hidden" name="email" value="user2@edu_parent.com">
				    <input type="hidden" name="parent_num" value="${c.comment_num}">
				    <textarea name="content" rows="3" cols="50" placeholder="답댓글을 입력하세요"></textarea>
				    <button type="submit">등록</button>
	          </form>
	      </li>
	  </c:forEach>
	</ul>
</body>
</html>