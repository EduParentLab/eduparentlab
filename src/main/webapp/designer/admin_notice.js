
// 페이지 갈아끼우는 함수는 전역으로 따로 유지
function loadContent(page) {
  fetch(`pages/${page}.html`)
    .then(res => res.text())
    .then(html => {
      const target = document.querySelector(".box-right"); // ← 이게 정답
      if (!target) {
        console.error("❌ .box-right 요소를 찾을 수 없습니다!");
        return;
      }
      target.innerHTML = html;
      if (page === "statistics") {
  initStatisticsChart();
  initSignupChart();
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

function initStatisticsChart() {
  const ctx = document.getElementById('categoryChart');
  if (!ctx) return;

  new Chart(ctx, {
    type: 'pie',
    data: {
      labels: ['자유게시판', '입시정보', '고등학교', '공지사항'],
      datasets: [{
        data: [3, 5, 2, 7],
        backgroundColor: ['#ff9baa', '#9fe3e0', '#9eccfa', '#ffe582']
      }]
    },
    options: {
      responsive: false,
      plugins: {
        legend: {
          position: 'bottom'
        }
      }
    }
  });
}

function initSignupChart() {
  const ctx = document.getElementById('signupChart');
  if (!ctx) return;

  // Chart.js + datalabels 등록

  new Chart(ctx, {
    type: 'bar',
    data: {
      labels: ['09-01', '09-02', '09-03', '09-04', '09-05'], // 날짜
      datasets: [{
        label: '일별 가입자 수',
        data: [10, 3, 6, 2, 4],
        backgroundColor: 'rgba(54, 162, 235, 0.5)'
      }]
    },
    options: {
      responsive: false,
      plugins: {
        legend: {
          position: 'top'
        },
        datalabels: {
          anchor: 'end',
          align: 'top',
          color: '#000',
          font: {
            size: 12,
            weight: 'bold'
          },
          formatter: value => value
        }
      },
      scales: {
        y: {
          beginAtZero: true,
          ticks: {
            stepSize: 2
          }
        }
      }
    },
    plugins: [ChartDataLabels]
  });
}