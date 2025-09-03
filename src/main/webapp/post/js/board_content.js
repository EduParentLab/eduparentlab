const CONTEXT_PATH = window.location.pathname.split('/')[1]; 
const BASE_URL = `${window.location.origin}/${CONTEXT_PATH}`;

document.addEventListener("DOMContentLoaded", function () {
loadComments();
});

function loadComments() {
	const postNum = new URLSearchParams(window.location.search).get("seq");
	fetch(`${BASE_URL}/comment/comment.do?m=list&post_num=${postNum}`)
    .then(res => res.text())
    .then(html => {
    document.getElementById("commentArea").innerHTML = html;
	
    })
	.then(()=>{
		events();
	})
}

function events(){
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
		    e.preventDefault();
		    const $form = $(this);
		    const $commentDiv = $form.closest(".section-content-comment");
		    const postNum = $form.find('input[name="post_num"]').val();
		    const parentNum = $form.find('input[name="parent_num"]').val();
		    const content = $form.find('textarea[name="content"]').val();

		    $.post(`${BASE_URL}/comment/comment.do?m=recomment`, 
		        { post_num: postNum, parent_num: parentNum, content: content },
		        function(newReply) {
		            // 서버에서 새 답댓글 JSON 반환
		            const $container = $commentDiv.find(".section-content-recomment-list");
		            
					// createRecommentHTML() 사용
		            const html = createRecommentHTML(newReply);
		            $container.append(html);
					
					//입력창 초기화 및 숨기기
		            $form.find('textarea[name="content"]').val('');
		            $form.hide();
		        }
		    ).fail(function() {
		        alert("답댓글 등록 중 오류 발생");
		    });
		});
		
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
}
function createRecommentHTML(recomment) {
    return `
    <div class="section-content-recomment" data-recomment-num="${recomment.comment_num}">
        <div style="border:solid rgb(243, 233, 233); margin-bottom:0px;padding:0px 0px; display: flex; flex-direction: column; gap:0px; width: 90%;">
            <div style="display: flex; justify-content:flex-start; align-items: center; padding: 10px; border-bottom: 1px solid #ddd; gap:20px; border:solid rgb(255, 255, 255);">
                <div>${recomment.email}</div>
                <div>${recomment.comment_date || ''}</div>
            </div>
            <div style="padding: 10px; border-bottom: 1px solid #ddd; margin-top:0px; border:solid rgb(255, 255, 255);">
                <label>
                    <img src="post/assets/reply.png" alt="대댓글" class="reply-icon" style="width: 20px; height: 20px;">
                    ${recomment.comment_content}
                </label>
            </div>
        </div>
    </div>`;
}

	
