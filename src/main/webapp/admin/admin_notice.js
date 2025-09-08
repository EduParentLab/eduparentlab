function loadContent(page) {
  fetch(`admin.do?m=${page}`)
    .then(res => res.text())
    .then(html => {
      const target = document.querySelector(".box-right"); // ← 이게 정답
      if (!target) {
        console.error("❌ .box-right 요소를 찾을 수 없습니다!");
        return;
      }
	  target.innerHTML = html;
	  if (page === "notice"){			
		    const searchFilter = document.getElementById("search-filter");
		    const input = target.querySelector("#noticeSearchInput");
		    const btn = target.querySelector("#noticeSearchBtn");
			function filterRows() {
				const searchOption = searchFilter.value;
                const query = input.value.toLowerCase();
				const rows = document.querySelectorAll("#noticeTable tbody tr");
				rows.forEach(row => {
		            let matched = false;
		            if (searchOption === "subject+content") {
		                const cells = row.querySelectorAll("td#post_subject, td#post_content");
		                cells.forEach(function(cell) {
		                    if (cell.innerText.toLowerCase().includes(query)) {
		                        matched = true; 
		                    }
		                });
		            } else {
		                const cell = row.querySelector("td#" + searchOption);
		                if (cell.innerText.toLowerCase().includes(query)) {
		                    matched = true;
		                }
		            }
		            row.style.display = matched ? "" : "none";
		        });
            }			
			input.addEventListener("keyup", filterRows);
			searchFilter.addEventListener("click", filterRows);					
	  }
	  if (page === "user_list" || page === "withdrawn_list"){
	  		    const searchFilter = document.getElementById("search-filter");
	  		    const input = target.querySelector("#userListSearchInput");
	  		    const btn = target.querySelector("#userListSearchBtn");
	  			function filterRows() {
	  				const searchOption = searchFilter.value;
	  				console.log(searchOption);
	                const query = input.value.toLowerCase();
	  				const rows = document.querySelectorAll("#userTable tbody tr");
	  				rows.forEach(row => {
	  		            const cell = row.querySelector("td#" + searchOption); 
						row.style.display = cell.innerText.toLowerCase().includes(query)? "" : "none";					
	  		        });
	              }	  			
	  			input.addEventListener("keyup", filterRows);
<<<<<<< HEAD
				searchFilter.addEventListener("click", filterRows);		
	   }	  
	  //statistics 탭 그래프 그리기 script 실행
=======
	   }
>>>>>>> c06f9215002cdc022160113431cc018f0772ef4c
	  if (page === "statistics") {
	      const scripts = target.querySelectorAll('script');
	      scripts.forEach(oldScript => {
	          const newScript = document.createElement('script');
	          if (oldScript.src) {
	              newScript.src = oldScript.src;
	          } else {
	              newScript.text = oldScript.textContent;
	          }
	          document.body.appendChild(newScript);
	      });
	  }
      const toggleBtn = document.getElementById("toggleNav");
      const leftBox = document.getElementById("leftBox");
      let isHidden = false;
      if (toggleBtn) {
        toggleBtn.addEventListener("click", function () {
          if (isHidden) {
            leftBox.style.width = "200px";
            leftBox.style.padding = "0px";
            leftBox.style.border = "1px solid black";
          } else {
            leftBox.style.width = "0";
            leftBox.style.padding = "0";
            leftBox.style.border = "none";
          }
          isHidden = !isHidden;
        });
      }
    })
    .catch(err => {
      console.error("🚨 페이지 로딩 실패:", err);
    });
}
document.addEventListener("DOMContentLoaded", function () {
  const toggleBtn = document.getElementById("toggleNav");
  const leftBox = document.getElementById("leftBox");
  let isHidden = false;
  if (toggleBtn) {
    toggleBtn.addEventListener("click", function () {
      if (isHidden) {
        leftBox.style.width = "200px";
        leftBox.style.padding = "0px";
        leftBox.style.border = "1px solid black";
      } else {
        leftBox.style.width = "0";
        leftBox.style.padding = "0";
        leftBox.style.border = "none";
      }
      isHidden = !isHidden;
    });
  }
  loadContent("notice");
});
document.querySelectorAll('.section-menu').forEach(menu => {
  menu.addEventListener('click', function () {
    const page = this.dataset.page;
    if (page) {
      loadContent(page);
    }
  });
});
document.querySelectorAll('.section-menu').forEach(menu => {
  menu.addEventListener('click', function () {
    document.querySelectorAll('.section-menu').forEach(m => m.classList.remove('active'));
    this.classList.add('active');
    const page = this.dataset.page;
    if (page) {
      loadContent(page);
    }
  });
});
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
});
fetch("../main/footerBox.jsp")
    .then(res => res.text())
    .then(html => {
      document.getElementById("footerArea").innerHTML = html;
    });
});