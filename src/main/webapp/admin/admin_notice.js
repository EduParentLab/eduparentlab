// 페이지 갈아끼우는 함수는 전역으로 따로 유지
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
	  
	  //notice 탭 검색 기능
	  if (page === "notice"){			
		    const searchFilter = document.getElementById("search-filter");
		    const input = target.querySelector("#noticeSearchInput");
		    const btn = target.querySelector("#noticeSearchBtn");
            
			function filterRows() {
				const searchOption = searchFilter.value;
				//console.log(searchOption);
                const query = input.value.toLowerCase();
				
				const rows = document.querySelectorAll("#noticeTable tbody tr");
				rows.forEach(row => {
		            let matched = false;
					//제목+내용 다중 필터
		            if (searchOption === "subject+content") {
		                const cells = row.querySelectorAll("td#post_subject, td#post_content");
		                cells.forEach(function(cell) {
		                    if (cell.innerText.toLowerCase().includes(query)) {
		                        matched = true; 
		                    }
		                });
		            } else {//단일 필터
		                const cell = row.querySelector("td#" + searchOption);
		                if (cell.innerText.toLowerCase().includes(query)) {
		                    matched = true;
		                }
		            }
		            row.style.display = matched ? "" : "none";
		        });
            }
			btn.addEventListener("click", filterRows);
			input.addEventListener("keyup", filterRows);					
	  }
	  //user_list 탭 검색 기능 
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
	  			btn.addEventListener("click", filterRows);
	  			input.addEventListener("keyup", filterRows);
	   }	  
	  //statistics 탭 그래프 그리기 script 실행
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
  // ✅ 1. 사이드바 토글 기능
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

  // ✅ 2. 초기 공지사항 불러오기
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
    // 기존 active 모두 제거
    document.querySelectorAll('.section-menu').forEach(m => m.classList.remove('active'));
    
    // 클릭된 항목에만 active 부여
    this.classList.add('active');

    // 페이지 로딩 (기존 코드 유지)
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

function loadNotice(page) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "admin.do?m=notice&page=" + page, false); // false -> 동기 요청
    xhr.send();

    if (xhr.status === 200) {
        // 서버에서 받은 HTML을 그대로 넣기        
		document.querySelector(".box.box-right").innerHTML = xhr.responseText;
    } else {
        alert("데이터를 불러오는 데 실패했습니다.");
    }
}

// 페이지 로딩 시 초기 호출
window.onload = function() {
    loadNotice(1);
};

// 페이징 버튼 클릭 시
$(document).on("click", ".pagination a", function(e){
           // e.preventDefault(); // 기본 동작 차단
            const page = $(this).data("page"); // data-page 가져오기
            loadNotice(page); // ✅ 정렬 상태(currentLatest)를 자동 반영
});
