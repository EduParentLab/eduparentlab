document.addEventListener("DOMContentLoaded", function () {
loadComments();
});

function loadComments() {
	fetch("post/js/commentBox.html")
    .then(res => res.text())
    .then(html => {
    document.getElementById("commentArea").innerHTML = html;
    });
}

// (선택) 댓글 작성 후 비동기 갱신 예시
document.addEventListener("click", function (e) {
if (e.target.classList.contains("comment-submit-btn")) {
    const content = document.querySelector(".comment-input").value;
    if (!content.trim()) return alert("댓글을 입력하세요.");

    // 서버에 댓글 POST 요청 (예시)
    fetch("/comment/insert", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ boardId: 123, content: content })
    })
    .then(res => res.json())
    .then(data => {
    if (data.success) {
        loadComments(); // 댓글 부분만 다시 로딩!
    }
    });
}
});