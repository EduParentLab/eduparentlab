<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<form id="commentForm" action="${pageContext.request.contextPath}/comment/comment.do?m=insert" method="post">
	    <input type="hidden" name="seq" value="${param.seq}">
	    <textarea name="content" rows="3" cols="50" placeholder="댓글을 입력하세요"></textarea>
	    <button type="submit">등록</button>
	</form>
	
	<ul id="commentList">
	  <c:forEach var="c" items="${comment}">
	      <li data-comment-num="${c.comment_num}">
	          <!-- 댓글 본문 -->
	          <span class="content">${c.comment_content}</span>
	          <span>(${c.email})</span>
	          <span>${c.comment_date}</span>
	
	          <!-- 삭제 버튼 -->
	          <button type="button" class="deleteBtn" onclick="return confirm('선택한 댓글을 삭제하시겠습니까?');">삭제</button>
	
	          <!-- 수정 버튼 -->
	          <button type="button" class="editBtn">수정</button>
			  
			  <!-- 답댓글 버튼 -->
			  <button type="button" class="showReformBtn">답글</button>
	          
    		  
	          <!-- 답댓글 영역 -->
	          <form class="recommentForm" action="comment/comment.do?m=recomment" method="post" style="display:none;">
	          		<input type="hidden" name="post_num" value="${c.post_num}">
	          		<input type="hidden" name="email" value="user2@edu_parent.com">
				    <input type="hidden" name="parent_num" value="${c.comment_num}">
				    <textarea name="content" placeholder="답댓글 입력"></textarea>
				    <button type="submit">등록</button>
			  </form>
	      </li>
	  </c:forEach>
	</ul>
	<!-- ◀▶ 페이지네이션 -->
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
</body>
</html>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
$(function() {
    // 댓글 수정 버튼 클릭
    $(document).on("click", ".editBtn", function() {
        const $btn = $(this);
        const $li = $btn.closest("li");
        const commentNum = $li.data("comment-num");
        
        $.get("${pageContext.request.contextPath}/comment/comment.do", { m: "checkUpdateAuth", comment_num: commentNum })
        	.done(function() {
		        const $content = $li.find("> .content");
		        const oldText = $content.text();
		
		        // span → input
		        const $input = $('<input type="text">').val(oldText);
		        $content.empty().append($input);

		        // 버튼 변경
		        $btn.text("저장").removeClass("editBtn").addClass("saveBtn");
        	})
        	.fail(function(){
        		alert("수정 권한이 없습니다.");
        	})
    });

    // 댓글 저장 버튼 클릭
    $(document).on("click", ".saveBtn", function() {
        const $btn = $(this);
        const $li = $btn.closest("li");
        const $input = $li.find("input");
        const newText = $input.val();
        const commentNum = $li.data("comment-num");
        
        console.log("commentNum:", commentNum, "newText:", newText);
        
        $.post('comment/comment.do?m=update', 
            { comment_num: commentNum, comment_content: newText },
            function() {
                $li.find("> .content").text(newText);
                $btn.text("수정").removeClass("saveBtn").addClass("editBtn");
            }
        ).fail(function() {
            alert("수정 중 오류 발생");
        });
    });

    // 댓글 삭제 버튼 클릭
    $(document).on("click", ".deleteBtn", function() {
        const $btn = $(this);
        $btn.prop("disabled", true);
        const $li = $btn.closest("li");
     	// 1️⃣ li가 제대로 잡히는지 확인
        console.log("선택된 li:", $li);

        // 2️⃣ data-commentNum 값 확인
        const commentNum = $li.data("comment-num");
        console.log("data-commentNum 값:", commentNum);

        if(!commentNum) {
            alert("comment_num이 없습니다.");
            return;
        }
     	
        $.post('comment/comment.do?m=delete', { comment_num: commentNum })
         .done(function(res) { 
        	 if(res.trim() === "success"){
        		 $li.remove(); 
        	 }else{
        		 alert("삭제 실패");
        	 }
         })
         .fail(function() { alert("삭제 중 오류 발생"); });
    });
	
    //답글 버튼 클릭 -> 입력폼 토글
    $(document).on("click", ".showReformBtn", function(){
    	const $li = $(this).closest("li");
    	const commentNum = $li.data("comment-num");
    	$.get("${pageContext.request.contextPath}/comment/comment.do", { m: "checkReplyAuth", comment_num: commentNum })
        .done(function() {
            $li.find(".recommentForm").toggle();
        })
        .fail(function() {
            alert("답글 권한이 없습니다.");
        });
    });
   
});

</script>
