<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
(function() {
    // ===== 1. 카테고리별 게시글 수 =====
    const categoryLabels = [
        <c:forEach var="entry" items="${postCount}" varStatus="loop">
            "${fn:escapeXml(entry.key)}"<c:if test="${!loop.last}">,</c:if>
        </c:forEach>
    ];

    const categoryData = [
        <c:forEach var="entry" items="${postCount}" varStatus="loop">
            ${entry.value}<c:if test="${!loop.last}">,</c:if>
        </c:forEach>
    ];

    const ctx1 = document.getElementById('categoryChart').getContext('2d');
    new Chart(ctx1, {
        type: 'pie',
        data: {
            labels: categoryLabels,
            datasets: [{
                data: categoryData,
                backgroundColor: ['#ff9baa','#9fe3e0','#9eccfa','#ffe582']
            }]
        },
        options: {
            responsive: false,
            plugins: {
                legend: { position: 'bottom' },
                datalabels: {
                    color: '#000',
                    formatter: (value) => value
                }
            }
        },
        plugins: [ChartDataLabels]
    });

    // ===== 2. 날짜별 가입자 수 =====
    const signupLabels = [
        <c:forEach var="entry" items="${userCount}" varStatus="loop">
            "${fn:escapeXml(entry.key)}"<c:if test="${!loop.last}">,</c:if>
        </c:forEach>
    ];

    const signupData = [
        <c:forEach var="entry" items="${userCount}" varStatus="loop">
            ${entry.value}<c:if test="${!loop.last}">,</c:if>
        </c:forEach>
    ];

    const ctx2 = document.getElementById('signupChart').getContext('2d');
    new Chart(ctx2, {
        type: 'bar',
        data: {
            labels: signupLabels,
            datasets: [{
                label: '일별 가입자 수',
                data: signupData,
                backgroundColor: 'rgba(54,162,235,0.5)'
            }]
        },
        options: { responsive: false }
    });
})();
</script> 