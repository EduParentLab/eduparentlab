document.addEventListener("DOMContentLoaded", function () {
  fetch("../main/headerBox.jsp")
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

  fetch("../main/footerBox.jsp")
    .then(res => res.text())
    .then(html => {
      document.getElementById("footerArea").innerHTML = html;
    });
});
