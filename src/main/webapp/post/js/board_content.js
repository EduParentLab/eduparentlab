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
	//답글 버튼 클릭 -> 입력폼 토글
	    $(document).on("click", ".section-content-comment", function(){
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
}
	
