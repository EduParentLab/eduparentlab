document.addEventListener("DOMContentLoaded", function () {
  fetch("headerBox.jsp")
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
    banner.style.opacity = 0;
    setTimeout(() => {
      index = (index + 1) % images.length;
      banner.src = images[index];
      banner.style.opacity = 1;
    }, 1000);
  }, 3000);

