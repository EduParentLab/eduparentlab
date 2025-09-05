document.addEventListener("DOMContentLoaded", function () {
  fetch("headerBox.jsp")
    .then(res => res.text())
    .then(html => {
      document.getElementById("headerArea").innerHTML = html;

      // ✅ fetch가 끝난 이후에 실행해야 안전함
      const isLoggedIn = false;

      const loginBefore = document.getElementById("login-before");
      const loginAfter = document.getElementById("login-after");

      if (loginBefore && loginAfter) {
        loginBefore.style.display = isLoggedIn ? "none" : "flex";
        loginAfter.style.display = isLoggedIn ? "flex" : "none";
      }
    });

  fetch("footerBox.jsp")
    .then(res => res.text())
    .then(html => {
      document.getElementById("footerArea").innerHTML = html;
    });
});

const images = [
    "../designer/assets/banner2.png",
    "../designer/assets/banner3.png"
  ];

  let index = 0;
  const banner = document.getElementById("banner");

  setInterval(() => {
    // 1. 먼저 투명하게 만들기
    banner.style.opacity = 0;

    // 2. 일정 시간(1s) 후 이미지 교체 + 다시 보이기
    setTimeout(() => {
      index = (index + 1) % images.length;
      banner.src = images[index];
      banner.style.opacity = 1;
    }, 1000); // transition 시간과 동일하게 맞춤
  }, 3000); // 3초마다 실행

