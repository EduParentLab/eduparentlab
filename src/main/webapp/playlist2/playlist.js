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
	loadCategory("all");
});
function loadCategory(category) {
  const container = document.getElementById("playlist-container");
  container.innerHTML = "";
  const filtered = category === "all"
    ? allPlaylists
    : allPlaylists.filter(item => item.category?.trim() === category.trim());
  if (!filtered || filtered.length === 0) {
    container.innerHTML = "<p style='text-align:center;'>해당 카테고리의 영상이 없습니다.</p>";
    return;
  }
  for (let i = 0; i < filtered.length; i += 3) {
    const row = document.createElement("div");
    row.style.display = "flex";
    row.style.gap = "10px";
    row.style.marginBottom = "20px";
    const items = filtered.slice(i, i + 3);
    items.forEach(item => {
      const link = document.createElement("a");
      link.href = item.link;
      link.target = "_blank";
      link.style.flex = "1";
      const videoId = extractVideoId(item.link);
      const img = document.createElement("img");
      img.src = videoId
        ? `https://img.youtube.com/vi/${videoId}/0.jpg`
        : "https://via.placeholder.com/320x180?text=Invalid+Link";
      img.alt = item.title || "영상 썸네일";
      img.style.width = "100%";
      img.style.height = "200px";
      img.style.objectFit = "cover";
      img.style.borderRadius = "8px";
      link.appendChild(img);
      row.appendChild(link);
    });
    container.appendChild(row);
  }
}
function extractVideoId(url) {
  const regExp = /(?:v=|\/embed\/|youtu\.be\/)([a-zA-Z0-9_-]+)/;
  const match = url.match(regExp);
  return match ? match[1] : "";
}
