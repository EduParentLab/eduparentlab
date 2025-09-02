<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="display: flex; justify-content: center; gap: 10px; margin-top: 20px;">
            <div style="display: flex; align-items: center; gap: 5px; width: 50%; padding-left: 10px;">
                <P>댓글 5</P>
            </div>
            <div style="display: flex; align-items: center; justify-content:right; gap: 5px; width: 50%; padding-right: 10px;">
                <button class="align-button" style="border-right:solid black;">인기순</button>
                <button class="align-button " style="border-right:solid black;">최신순</button>
                <button class="align-button">오래된 순</button>
            </div>
    </div>
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
	<c:forEach var="c" items="${comment}">
		<div class="section-content-comment">
          <!-- 댓글 헤더 -->
          <div style="margin-bottom:0px;padding:0px 0px; display: flex; flex-direction: column; gap:0px; width: 90%;">
	          <div style="display: flex; justify-content:flex-start; align-items: center; padding: 10px; border-bottom: 1px solid #ffffff; gap:20px; border:solid rgb(255, 255, 255);">
	            <div>${c.email}</div>
	            <div>${c.comment_date}</div>
	            <div style="display: flex; align-items: center; gap: 5px;">
	                <img src="post/assets/like.png" alt="좋아요" class="like-icon" style="width: 20px; height: 20px;"/>
	              <div>24</div>
	            </div>
	          </div>
	          <!-- 댓글 내용 -->
	          <div style="padding: 10px; border-bottom: 1px solid #ddd; margin-top:0px; border:solid rgb(255, 255, 255);">
	            <label>
	              <span class="content">${c.comment_content}</span>
	            </label>
	          </div>
          </div>
        </div>
    </c:forEach>
    	<div class="section-content-recomment">
          <div style="border:solid rgb(243, 233, 233); margin-bottom:0px;padding:0px 0px; display: flex; flex-direction: column; gap:0px; width: 90%;">
          <div style="display: flex; justify-content:flex-start; align-items: center; padding: 10px; border-bottom: 1px solid #ddd; gap:20px; border:solid rgb(255, 255, 255);">
            <div>
              햄토리
            </div>
            <div>
              2024.08.23
            </div>
            <div style="display: flex; align-items: center; gap: 5px;">
                <img src="post/assets/like.png" alt="좋아요" class="like-icon" style="width: 20px; height: 20px;"/>
              <div>
                24
              </div>
            </div>
          </div>
          <div style="padding: 10px; border-bottom: 1px solid #ddd; margin-top:0px; border:solid rgb(255, 255, 255);">
            <label>
              
              <img src="post/assets/reply.png" alt="대댓글" class="reply-icon" style="width: 20px; height: 20px;">
              저도 이 시험 준비하고 있는데, 정보 감사합니다!저도 이 시험 준비하고 있는데, 정보 감사합니다!저도 이 시험 준비하고 있는데, 정보 감사합니다!
            </label>
          </div>
          </div>
        </div>	  
	          <!-- 답댓글 영역 -->
	          <div class="section-content-recomment-input">
		        <form class="recommentForm" action="comment/comment.do?m=recomment" method="post" style="display:none;">
			      <input type="hidden" name="post_num" value="${post_num}">
				  <input type="hidden" name="parent_num" value="${c.comment_num}">
		          <div style="display: flex; justify-content: flex-end; width: 100%; align-items: center; gap:10px;">
		               <textarea name="content" class="comment-input" placeholder="댓글을 입력하세요." style="width:90%; height:100px; border:solid rgb(0, 0, 0);"></textarea>
		          </div>
		          <div style="display: flex; justify-content: flex-end; width: 100%; margin-top:10px;">
		            <button style="width:150px; height:40px;">등록</button>
		          </div>
		        </form> 
			</div>
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
    $(document).on("click", ".section-content-comment", function(){
    	const $commentDiv = $(this).closest(".section-content-comment");
    	const commentNum = $commentDiv.data("comment-num");
    	$.get("${pageContext.request.contextPath}/comment/comment.do", { m: "checkReplyAuth", comment_num: commentNum })
        .done(function() {
            $li.find(".section-content-recomment-input").toggle();
        })
        .fail(function() {
            alert("답글 권한이 없습니다.");
        });
    });
   
});

</script>
