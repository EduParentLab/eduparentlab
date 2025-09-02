<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 기능</title>
</head>
<body>

	<!-- 댓글 등록 폼 -->
	<form id="commentForm">
	    <input type="hidden" name="post_num" value="1">
	    <input type="hidden" name="email" value="user1@edu_parent.com">
	    <textarea name="comment_content" rows="3" cols="50" placeholder="댓글을 입력하세요"></textarea>
	    <button type="submit">등록</button>
	</form>
	
	<!-- 댓글 목록 -->
	<ul id="commentList">
	  <c:forEach var="c" items="${comment}">
	      <li data-comment-num="${c.comment_num}">
	          <span class="content">${c.comment_content}</span>
	          <span>(${c.email})</span>
	          <span>${c.comment_date}</span>
	          <button type="button" class="deleteBtn">삭제</button>
	          <button type="button" class="editBtn">수정</button>
			  <button type="button" class="showReformBtn">답글</button>
	          
	          <!-- 답댓글 폼 (토글용) -->
	          <form class="recommentForm" style="display:none;">
	          		<input type="hidden" name="post_num" value="1">
	          		<input type="hidden" name="email" value="user2@edu_parent.com">
				    <input type="hidden" name="parent_num" value="${c.comment_num}">
				    <textarea name="comment_content" placeholder="답댓글 입력"></textarea>
				    <button type="submit">등록</button>
			  </form>
			  <!-- 답댓글 리스트 -->
	          <ul class="recommentList"></ul>
	      </li>
	  </c:forEach>
	</ul>

</body>
</html>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
$(document).ready(function() {
    // ✅ 페이지 들어왔을 때만 전체 댓글 로드
    loadComments();

    // 1️ 댓글 등록 (AJAX)
    $("#commentForm").on("submit", function(e) {
        e.preventDefault();

        $.post("comment.do?m=insert&format=json", $(this).serialize(), function(newComment) {
        	console.log(newComment);
        	alert("등록완료 : "+JSON.stringify(newComment));
            
            let html = `
                <li data-comment-num="${newComment.comment_num}">
                    <span class="content">${newComment.comment_content}</span>
                    <span>(${newComment.email})</span>
                    <span>${newComment.comment_date}</span>
                    <button type="button" class="deleteBtn">삭제</button>
                    <button type="button" class="editBtn">수정</button>
                    <button type="button" class="showReformBtn">답글</button>
                    <form class="recommentForm" style="display:none;">
                        <input type="hidden" name="post_num" value="${newComment.post_num}">
                        <input type="hidden" name="email" value="user2@edu_parent.com">
                        <input type="hidden" name="parent_num" value="${newComment.comment_num}">
                        <textarea name="comment_content" placeholder="답댓글 입력"></textarea>
                        <button type="submit">등록</button>
                    </form>
                    <!-- 답댓글 리스트 -->
      	          <ul class="recommentList"></ul>
                </li>
            `;
            
            $("#commentList").append(html);
            $("#commentForm textarea").val(""); // 입력창 초기화
        }, "json");
        
    });

    // 2️ 답댓글 토글
    $(document).on("click", ".showReformBtn", function(){
        $(this).closest("li").find(".recommentForm").toggle();
    });

    // 3️ 답댓글 등록 (AJAX)
    $(document).on("submit", ".recommentForm", function(e) {
        e.preventDefault();
        const $form = $(this);
        $.post("${pageContext.request.contextPath}/comment/comment.do?m=recomment&format=json", $form.serialize(), function(newRe) {
            let html = `
                <ul>
                    <li data-comment-num="${newRe.comment_num}" style="margin-left:20px;">
                        <span class="content">${newRe.comment_content}</span>
                        <span>(${newRe.email})</span>
                        <span>${newRe.comment_date}</span>
                        <button type="button" class="deleteBtn">삭제</button>
                        <button type="button" class="editBtn">수정</button>
                    </li>
                </ul>
            `;
            $form.closest("li").find(">ul.recommentList").append(html);
            $form.hide().find("textarea").val("");
            
        }, 
        "json"
        )
        .fail(function(xhr, status, error) {
        console.error("등록 실패:", status, error, xhr.responseText);
        alert("댓글 등록 중 오류 발생: " + xhr.responseText);
    	});
    });

    // 4️ 댓글 수정
    $(document).on("click", ".editBtn", function() {
        const $btn = $(this);
        const $li = $btn.closest("li");
        const $content = $li.find("> .content");
        const oldText = $content.text();
        const $input = $('<input type="text">').val(oldText);
        $content.empty().append($input);
        $btn.text("저장").removeClass("editBtn").addClass("saveBtn");
    });

    $(document).on("click", ".saveBtn", function() {
        const $btn = $(this);
        const $li = $btn.closest("li");
        const commentNum = $li.data("comment-num");
        const newText = $li.find("input").val();

        $.post("${pageContext.request.contextPath}/comment/comment.do?m=update&format=json", 
            { comment_num: commentNum, comment_content: newText }, 
            function() {
                $li.find("> .content").text(newText);
                $btn.text("수정").removeClass("saveBtn").addClass("editBtn");
            }
        );
    });

    // 5️ 댓글 삭제
    $(document).on("click", ".deleteBtn", function() {
        const $li = $(this).closest("li");
        const commentNum = $li.data("comment-num");
        $.post("${pageContext.request.contextPath}/comment/comment.do?m=delete&format=json", { comment_num: commentNum }, function() {
            $li.remove();
        });
    });

});
function loadComments() {
    const postNum = 1; // 현재 게시글 번호
    $.getJSON("${pageContext.request.contextPath}/comment/comment.do?post_num=" + postNum, function(data) {
        const $list = $("#commentList");
        $list.empty();
        data.comment.forEach(function(c) {
            let html = `
            <li data-comment-num="${c.comment_num}">
            data-comment-num="${c.comment_num}"
                <span class="content">${c.comment_content}</span>
                <span>(${c.email})</span>
                <span>${c.comment_date}</span>
                <button type="button" class="deleteBtn">삭제</button>
                <button type="button" class="editBtn">수정</button>
                <button type="button" class="showReformBtn">답글</button>
                <form class="recommentForm" style="display:none;">
                    <input type="hidden" name="post_num" value="${c.post_num}">
                    <input type="hidden" name="email" value="${c.email}">
                    <input type="hidden" name="parent_num" value="${c.comment_num}">
                    <textarea name="comment_content" placeholder="답댓글 입력"></textarea>
                    <button type="submit">등록</button>
                </form>
                <!-- 답댓글 리스트 -->
  	          	<ul class="recommentList"></ul>
            </li>
            `;
            $list.append(html);
        });
    });
}
</script>
