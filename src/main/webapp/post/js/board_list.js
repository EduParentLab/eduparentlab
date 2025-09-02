
  $(function () {
    $('.dropdown-btn').on('click', function () {
      $('.dropdown-list').toggle();
    });

    $('.dropdown-list li').on('click', function () {
      const text = $(this).text();
      $('.selected-text').text(text);
      $('.dropdown-list').hide();
    });

    $(document).on('click', function (e) {
      if (!$(e.target).closest('.dropdown-wrap').length) {
        $('.dropdown-list').hide();
      }
    });
  });

  
  document.addEventListener("DOMContentLoaded", function () {
    fetch("./main/headerBox.jsp")
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
  });
  
  const writeBtn = document.getElementById("writeBtn");
  if (writeBtn) {
    writeBtn.addEventListener("click", function () {
      const catNum = document.querySelector(".center-wrapper").dataset.category;
      location.href = `${contextPath}/post.do?m=input&category_num=${catNum}`;
    });
  }
