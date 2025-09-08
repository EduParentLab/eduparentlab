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
    "https://www.youtube.com/watch?v=BnFuI_7QgDo",
	"https://www.youtube.com/watch?v=Q7s4W1OwgQw",
	"https://www.youtube.com/watch?v=RKvDwxLixgA",
	"https://www.youtube.com/watch?v=ZjWUfRynLUI",
  ],
  jpop: [
    "https://www.youtube.com/watch?v=-rRFxzRCHKI",
  ],
  classic: [
    "https://www.youtube.com/watch?v=a1Y73sPHKxw",
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

  container.innerHTML = "";

  links.forEach(link => {
    container.appendChild(createThumb(link));
  });
}