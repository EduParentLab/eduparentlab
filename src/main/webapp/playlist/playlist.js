document.addEventListener("DOMContentLoaded", function () {
  fetch("../main/headerBox.jsp")
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

      // ✅ header 로드 완료 후 유튜브 목록 로드
      loadCategory("ipsi");
    });
	
	fetch("../main/footerBox.jsp")
	    .then(res => res.text())
	    .then(html => {
	      document.getElementById("footerArea").innerHTML = html;
	    });
});
const categories = {
  ipsi: [
    "https://www.youtube.com/watch?v=w0UTfL-N9Wo",
    "https://www.youtube.com/watch?v=V729VAEg-JQ",
    "https://www.youtube.com/watch?v=3wLubqaz0UU",
	"https://www.youtube.com/watch?v=w0UTfL-N9Wo",
	    "https://www.youtube.com/watch?v=V729VAEg-JQ"
  ],
  jpop: [
    "https://www.youtube.com/watch?v=-rRFxzRCHKI",
    "https://www.youtube.com/watch?v=rjwqxhg5-BY",
    "https://www.youtube.com/watch?v=JfM94tIgVj8"
  ],
  classic: [
    "https://www.youtube.com/watch?v=a1Y73sPHKxw",
    "https://www.youtube.com/watch?v=FYH8DsU2WCk",
    "https://www.youtube.com/watch?v=GsqUZkmO-zk"
  ]
};

function createThumb(link) {
  const videoId = new URL(link).searchParams.get("v");
  const thumbnail = `https://img.youtube.com/vi/${videoId}/hqdefault.jpg`;

  const a = document.createElement("a");
  a.href = link;
  a.target = "_blank";
  a.classList.add("youtube-thumb");

  const img = document.createElement("img");
  img.src = thumbnail;
  img.classList.add("youtube-img");

  a.appendChild(img);
  return a;
}

function loadCategory(categoryName) {
  const links = categories[categoryName];
  const container = document.getElementById("playlist-container");

  container.innerHTML = ""; // 기존 삭제

  links.forEach(link => {
    container.appendChild(createThumb(link));
  });
}