document.addEventListener("DOMContentLoaded", function () {
  // 페이지 로드 시 전체 카테고리 출력
  loadCategory("all");
});

function loadCategory(category) {
  const container = document.getElementById("playlist-container");
  container.innerHTML = ""; // 기존 목록 초기화

  // 필터링
  const filtered = category === "all"
    ? allPlaylists
    : allPlaylists.filter(item => item.category === category);

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

// YouTube 링크에서 Video ID 추출
function extractVideoId(url) {
  const regExp = /(?:youtube\.com\/(?:watch\?v=|embed\/)|youtu\.be\/)([a-zA-Z0-9_-]{11})/;
  const match = url.match(regExp);
  return match ? match[1] : "";
}
