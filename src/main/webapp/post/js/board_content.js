const CONTEXT_PATH = window.location.pathname.split('/')[1]; 
const BASE_URL = `${window.location.origin}/${CONTEXT_PATH}`;

document.addEventListener("DOMContentLoaded", function () {
	events();
	loadComments();
});

function loadComments(latest=true) {
	const postNum = new URLSearchParams(window.location.search).get("seq");
	fetch(`${BASE_URL}/comment/comment.do?m=list&post_num=${postNum}&latest=${latest}`)
    .then(res => res.text())
    .then(html => {
		const temp = document.createElement('div');
        temp.innerHTML = html;

        // JSP에서 #commentList와 #pagination div를 나눠서 만들었다고 가정
        const commentListHtml = temp.querySelector('#commentList').innerHTML;
        const paginationHtml = temp.querySelector('#pagination').innerHTML;

        document.getElementById('commentList').innerHTML = commentListHtml;
        document.getElementById('pagination').innerHTML = paginationHtml;
    })
	
}


document.addEventListener("DOMContentLoaded", function () {
    fetch(`${contextPath}/main/headerBox.jsp`)
      .then(res => res.text())
      .then(html => {
        document.getElementById("headerArea").innerHTML = html;
        const isLoggedIn = false;
        const loginBefore = document.getElementById("login-before");
        const loginAfter = document.getElementById("login-after");
        if (loginBefore && loginAfter) {
          loginBefore.style.display = isLoggedIn ? "none" : "flex";
          loginAfter.style.display = isLoggedIn ? "flex" : "none";
        }
      });
  fetch(`${contextPath}/main/footerBox.jsp`)
    .then(res => res.text())
    .then(html => {
      document.getElementById("footerArea").innerHTML = html;
	});
});
  



function events(){
		// 댓글 작성 submit
	    $(document).on("submit", "#commentForm", function(e){
			e.stopPropagation();
			e.preventDefault();
	        const $form = $(this);
	        const postNum = $form.find('input[name="post_num"]').val();
	        const content = $form.find('textarea[name="content"]').val().trim();
			// 빈 댓글 처리
		   if(content === "") {
		       alert("내용을 입력해주세요.");
		       $form.find('textarea[name="content"]').focus();
		       return; // submit 중단
		   }
			$.post(`${BASE_URL}/comment/comment.do?m=insert`, 
			    { post_num: postNum, content: content }
			).done(function() {
			    $form.find('textarea[name="content"]').val('');
			    loadComments(); // 댓글 리스트 전체를 새로 렌더링
			}).fail(function() {
			    alert("댓글 등록 중 오류 발생");
			});
	    });
	    //댓글쓴 클릭 -> 입력폼 토글
	    $(document).on("click", ".comment-writer", function(e){
			e.stopPropagation();
	    	const $commentDiv = $(this).closest(".section-content-comment");
	    	const commentNum = $commentDiv.data("comment-num");
	    	$.get(`${BASE_URL}/comment/comment.do`, { m: "checkReplyAuth", comment_num: commentNum })
	        .done(function() {
	            $commentDiv.find(".section-content-recomment-input").toggle();
	        })
	        .fail(function() {
	            alert("답글 권한이 없습니다.");
	        });
	    });
		// 입력창 클릭 시 토글 닫히지 않도록
		$(document).on("click", ".section-content-recomment-input", function(e) {
		    e.stopPropagation();
		});
		//답댓글 입력
		$(document).on("submit", ".recommentForm", function(e) {
			e.stopPropagation();
			e.preventDefault();
		    const $form = $(this);
		    const postNum = $form.find('input[name="post_num"]').val();
		    const parentNum = $form.find('input[name="parent_num"]').val();
		    const content = $form.find('textarea[name="content"]').val().trim();
			// 빈 댓글 처리
		   if(content === "") {
		       alert("댓글 내용을 입력해주세요.");
		       $form.find('textarea[name="content"]').focus();
		       return; // submit 중단
		   }
			$.post(`${BASE_URL}/comment/comment.do?m=recomment`, 
			    { post_num: postNum, parent_num: parentNum, content: content }
			).done(function() {
			    $form.find('textarea[name="content"]').val('');
			    loadComments(); // 전체 댓글 새로 렌더링
			}).fail(function() {
			    alert("답댓글 등록 중 오류 발생");
			});
		});
		//답댓글쓴이 눌렀을때 반응 없게 
		$(document).on("click", ".recomment-writer", function(e) {
		    e.stopPropagation();  // 상위 토글 등 이벤트 전달 차단
		    e.preventDefault();   // 링크나 기본 클릭 동작 차단
		    // 아무 동작도 하지 않음
		});
		//페이징
		$(document).on("click", ".pagination a", function(e){
		    e.preventDefault(); // 기본 동작 차단
		    const page = $(this).data("page"); // data-page 가져오기
		    const postNum = new URLSearchParams(window.location.search).get("seq"); // 현재 post_num 가져오기


		    fetch(`${BASE_URL}/comment/comment.do?m=list&post_num=${postNum}&page=${page}`)
		        .then(res => res.text())
				.then(html => {
				    const temp = document.createElement('div');
				    temp.innerHTML = html;

				    const commentListHtml = temp.querySelector('#commentList').innerHTML;
				    const paginationHtml = temp.querySelector('#pagination').innerHTML;

				    document.getElementById('commentList').innerHTML = commentListHtml;
				    document.getElementById('pagination').innerHTML = paginationHtml;
				});
		});
		//최신순, 인기순
		$(document).on("click", ".align-button", function(e){
		    e.preventDefault();
		    const latest = $(this).data("latest"); // true/false
		    const postNum = new URLSearchParams(window.location.search).get("seq");
		    fetch(`${BASE_URL}/comment/comment.do?m=list&post_num=${postNum}&latest=${latest}`)
		        .then(res => res.text())
				.then(html => {
				    const temp = document.createElement('div');
				    temp.innerHTML = html;

				    const commentListHtml = temp.querySelector('#commentList').innerHTML;
				    const paginationHtml = temp.querySelector('#pagination').innerHTML;

				    document.getElementById('commentList').innerHTML = commentListHtml;
				    document.getElementById('pagination').innerHTML = paginationHtml;
				});
		});
		// 댓글 수정 버튼 클릭
		$(document).on("click", ".editBtn", function() {
		    const $btn = $(this);
		    const $commentDiv = $btn.closest(".section-content-comment, .section-content-recomment"); // li 대신 div 선택
		    const commentNum = $commentDiv.data("comment-num");
		    
		    $.get(`${BASE_URL}/comment/comment.do`, { m: "checkUpdateAuth", comment_num: commentNum })
		    	.done(function() {
			        let $content = $commentDiv.find("> .comment-original > div:nth-child(2) > .content"); // content span 선택
					if($content.length === 0){
					    $content = $commentDiv.find("> .recomment-original .content");
					}
					const oldText = $content.text();

			        // span → input
					const $textarea = $('<textarea></textarea>')
					    .val(oldText)
					    .attr('rows', 3)           // 높이: 4줄
					    .attr('cols', 50)          // 너비: 50글자 기준
					    .css({
					        width: '100%',         // div에 맞춰 꽉 채우기
					        minHeight: '60px',     // 최소 높이
					        fontSize: '14px',
					        padding: '5px'
					    });
					$content.empty().append($textarea);

			        // 버튼 변경
			        $btn.text("등록").removeClass("editBtn").addClass("saveBtn");
		    	})
		    	.fail(function(){
		    		alert("수정 권한이 없습니다.");
		    	})
			});
	
			//댓글 수정 후 등록 버튼 클릭
			$(document).on("click", ".saveBtn", function() {
			    const $btn = $(this);
			    const $commentDiv = $btn.closest(".section-content-comment, .section-content-recomment"); // li 대신 div 선택
			    const $textarea = $commentDiv.find("textarea");
			    const newText = $textarea.val();
			    const commentNum = $commentDiv.data("comment-num");
			    
			    console.log("commentNum:", commentNum, "newText:", newText);
			    
			    $.post(`${BASE_URL}/comment/comment.do?m=update`, 
			        { comment_num: commentNum, comment_content: newText },
			        function() {
			            let $content = $commentDiv.find("> .comment-original > div:nth-child(2) > .content");
						if($content.length === 0){
						    $content = $commentDiv.find("> .recomment-original .content");
						}
						$content.text(newText);
			            $btn.text("수정").removeClass("saveBtn").addClass("editBtn");
			        }
			    ).fail(function() {
			        alert("수정 중 오류 발생");
			    });
			});

		    // 댓글 삭제 버튼 클릭
		    $(document).on("click", ".deleteBtn", function() {
				const $btn = $(this);
				    const $commentDiv = $btn.closest(".section-content-comment, .section-content-recomment"); // ✅ li 대신 div 선택
				    const commentNum = $commentDiv.data("comment-num");           // data-comment-num 읽기
		        console.log("data-commentNum 값:", commentNum);

		        if(!commentNum) {
		            alert("comment_num이 없습니다.");
		            return;
		        }
		     	
		        $.post('comment/comment.do?m=delete', { comment_num: commentNum })
		         .done(function(res) { 
		        	 if(res.trim() === "success"){
		        		 loadComments();
		        	 }else{
		        		 alert("삭제 실패");
		        	 }
		         })
		         .fail(function() { alert("삭제 중 오류 발생"); });
		    });
}