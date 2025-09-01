<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<h1>postCount = ${postCount}</h1>
<h1>userCount = ${userCount}</h1>
<button id="toggleNav">☰ 메뉴</button>
<div class="long-divider"></div>
<div style="display:flex; align-items:center; justify-content:center; flex-direction: column;">
    <label style="font-size:24px; font-family:sans-serif; font-weight: bold;">카테고리별 게시글 수</label>
    <canvas id="categoryChart" width="500" height="500"></canvas>
</div>
<div class="long-divider"></div>
<div class="long-divider"></div>
<div style="display:flex; align-items:center; justify-content:center; flex-direction: column;">
    <h3>날짜별 가입자 수</h3>
    <canvas id="signupChart" width="500" height="400"></canvas>
</div>
    <div class="long-divider"></div>

<style>
.long-divider {
    width: 100%;           /* 또는 원하는 길이 예: 1200px */
    border: none;
    border-top: 1px solid #000000;  /* 선 스타일 */
    margin: 20px 0;        /* 위아래 여백 */
}
</style>
<script>
  const postCount = {
    <c:forEach var="entry" items="${postCount}" varStatus="loop">
      "${entry.key}": ${entry.value}<c:if test="${!loop.last}">,</c:if>
    </c:forEach>
  };
  console.log("postCount=", postCount);
</script>
<c:forEach var="entry" items="${postCount}">
  <script>
    console.log("KEY='${entry.key}', VALUE='${entry.value}'");
  </script>
</c:forEach>
<script>
  const postCountJS = {
    <c:forEach var="entry" items="${postCount}" varStatus="loop">
      "${entry.key}": ${entry.value}<c:if test="${!loop.last}">,</c:if>
    </c:forEach>
  };
</script>

<script src="admin_notice.js"></script>

<script>
window.addEventListener('DOMContentLoaded', () => {
    const postLabels = [];
    const postData = [];
    <c:forEach var="entry" items="${postCount}">
        postLabels.push("${entry.key}");
        postData.push(parseInt("${entry.value}"));
    </c:forEach>

    new Chart(document.getElementById('categoryChart'), {
        type: 'pie',
        data: {
            labels: postLabels,
            datasets: [{
                data: postData,
                backgroundColor: [
                    'rgba(255, 99, 132, 0.6)',
                    'rgba(54, 162, 235, 0.6)',
                    'rgba(255, 206, 86, 0.6)',
                    'rgba(75, 192, 192, 0.6)',
                    'rgba(153, 102, 255, 0.6)'
                ]
            }]
        },
        options: {
            responsive: false,
            plugins: {
                datalabels: {
                    display: true,
                    color: 'white',
                    font: { weight: 'bold', size: 14 },
                    formatter: (value) => `${value}개`
                },
                tooltip: {
                    callbacks: {
                        label: function(context) {
                            return `${context.label}: ${context.raw}개`;
                        }
                    }
                },
                legend: { display: true, position: 'bottom' },
                title: { display: true, text: '카테고리별 게시글 수', font: { size: 18, weight: 'bold' } }
            }
        },
        plugins: [ChartDataLabels]
    });

    // 날짜별 가입자 수
    const userLabels = [];
    const userData = [];
    <c:forEach var="entry" items="${userCount}">
        userLabels.push("${entry.key}");
        userData.push(parseInt("${entry.value}"));
    </c:forEach>

    new Chart(document.getElementById('signupChart'), {
        type: 'bar',
        data: {
            labels: userLabels,
            datasets: [{
                label: '일별 가입자 수',
                data: userData,
                borderWidth: 1,
                backgroundColor: 'rgba(54, 162, 235, 0.5)'
            }]
        },
        options: {
            responsive: false,
            scales: {
                y: {
                    beginAtZero: true,
                    ticks: { stepSize: 1 }
                }
            }
        }
    });
});
</script>
                