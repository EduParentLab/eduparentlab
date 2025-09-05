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
});