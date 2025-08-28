<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels@2.2.0/dist/chartjs-plugin-datalabels.min.js"></script>
<title>관리자 페이지</title>
</head>
<h1>관리자 페이지</h1>
<a href='../'>인덱스</a>
<body>	
	<div class="tab">
	  <button onclick="showSection('newsTable')">공지사항</button>
	  <button onclick="showSection('userTable')">사용자목록</button>
	  <button onclick="showSection('ghostTable')">탈퇴회원</button>
	  <button onclick="showSection('statistics')">통계</button>
	</div>		
	<div id="newsTable">	
	<table border='1' width='1000' cellpadding='2'>
	<tr>
		<th align='center' width='10%'>글번호</th>
		<th align='center' width='50%'>글제목</th>
		<th align='center' width='30%'>작성날짜</th>
		<th align='center' width='10%'>조회수</th>
	</tr>
	<c:choose>
	    <c:when test="${empty news}">
	        <tr>
	        <td align='center' colspan="4">작성한 글 없음</td>
	        </tr>
	    </c:when>
	    <c:otherwise>
	        <c:forEach items="${news}" var="dto">
	        <tr>
			<td align='center'>${dto.post_num}</td>
			<td align='center'>
			<a href='../post.do?m=content&seq=${dto.post_num}'>${dto.post_subject}</a>
			</td>
			<td align='center'>${dto.post_date}</td>		
			<td align='center'>${dto.post_view}</td>
			</tr>
	        </c:forEach>
	    </c:otherwise>	    
	</c:choose>		
	</table>
	<button onclick="location.href='../post/post.do'" style="float:right">글쓰기</button>
	</div>	
	<div id="userTable" style="display:none;">	
	<table border='1' width='1000' cellpadding='2'>
	<tr>
		<th align='center' width='20%'>이메일</th>
		<th align='center' width='5%'>닉네임</th>
		<th align='center' width='5%'>성별</th>
		<th align='center' width='10%'>생년월일</th>
		<th align='center' width='5%'>이름</th>
		<th align='center' width='10%'>휴대폰번호</th>
		<th align='center' width='10%'>가입한날짜</th>
	</tr>
	<c:choose>
	    <c:when test="${empty user}">
	        <tr>
	        <td align='center' colspan="7">사용자 없음</td>
	        </tr>
	    </c:when>
	    <c:otherwise>
	        <c:forEach items="${user}" var="dto">
	        <tr>
			<td align='center'>${dto.email}</td>			
			<td align='center'>${dto.nickname}</td>		
			<td align='center'>${dto.gender}</td>
			<td align='center'>${dto.birth}</td>
			<td align='center'>${dto.name}</td>
			<td align='center'>${dto.phone}</td>
			<td align='center'>${dto.cdate}</td>
			</tr>		
	        </c:forEach>
	    </c:otherwise>	    
	</c:choose>	
	</table>
	</div>
	<div id="ghostTable" style="display:none;">	
	<table border='1' width='1000' cellpadding='2'>
	<tr>
		<th align='center' width='20%'>이메일</th>
		<th align='center' width='5%'>닉네임</th>
		<th align='center' width='5%'>성별</th>
		<th align='center' width='10%'>생년월일</th>
		<th align='center' width='5%'>이름</th>
		<th align='center' width='10%'>휴대폰번호</th>
		<th align='center' width='10%'>가입한날짜</th>
	</tr>
	<c:choose>
	    <c:when test="${empty ghost}">
	        <tr>
	        <td align='center' colspan="7">탈퇴회원 없음</td>
	        </tr>
	    </c:when>
	    <c:otherwise>
	        <c:forEach items="${ghost}" var="dto">
	        <tr>
			<td align='center'><a href='../post/post.do?m=content&code=${dto.email}'>${dto.email}</td>			
			<td align='center'>${dto.nickname}</td>		
			<td align='center'>${dto.gender}</td>
			<td align='center'>${dto.birth}</td>
			<td align='center'>${dto.name}</a></td>
			<td align='center'><a href='../post/post.do?m=content&code=${dto.name}'>${dto.email}</a></td>							
			<td align='center'>${dto.nickname}</td>
			<td align='center'>${dto.gender}</td>	
			<td align='center'>${dto.birth}</td>
			<td align='center'>${dto.name}</td>
			<td align='center'>${dto.phone}</td>
			<td align='center'>${dto.cdate}</td>
			</tr>		
	        </c:forEach>
	    </c:otherwise>	    
	</c:choose>	
	</table>
	</div>
	<div id="statistics" style="display:none;">
	<h2>카테고리 별 게시글 수</h2>
	<canvas id="myPieChart" width="400" height="400"></canvas>
	<h2>날짜 별 가입자 수</h2>
	<canvas id="userChart" width="400" height="200"></canvas>
	</div>
	
    <script>     
	function showSection(sectionId) {
	    const sections = ["newsTable", "userTable", "ghostTable", "statistics"];
	    
	    sections.forEach(id => {
	        document.getElementById(id).style.display = (id === sectionId ? "block" : "none");
	    });
	}
	//그래프 코드 예시 아무거나 가지고옴 효상님 마음대로 변경하면 됩니다/그래프 위에 숫자 계속 null로 찍힘...커서 대면 값은 나옴 왜그런거야...
		const postLabels = [];
	    const postData = [];
	    
	    <c:forEach var="entry" items="${postCount}">
	        postLabels.push("${entry.key}");	    
	        postData.push(${entry.value});
	    </c:forEach>
	    
	    const ctxPost = document.getElementById('myPieChart').getContext('2d');
	    
	    new Chart(ctxPost, {
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
	                    formatter: (value, context) => {
	                        const dataset = context.chart.data.datasets[0];
	                        const total = dataset.data.reduce((sum, val) => sum + val, 0);
	                        const percentage = ((value / total) * 100).toFixed(1);
	                        //return `${percentage}%`;
	                    }
	                },
	                tooltip: {
	                    callbacks: {
	                        label: function(context) {
	                            const label = context.label || '';
	                            const value = context.raw || 0;
	                            //return `${label}: ${value}개`;
	                        }
	                    }
	                },
	                legend: { display: true, position: 'bottom' },
	                title: { display: true, text: '카테고리별 게시글 수', font: { size: 18, weight: 'bold' } }
	            }
	        },
	        plugins: [ChartDataLabels]
	    });
	</script>	
	
	<!-- 📊 날짜별 가입자 수 (Bar Chart) -->
	<script>
		const userLabels = [];
	    const userData = [];
	
	    <c:forEach var="entry" items="${userCount}">
	        userLabels.push("${entry.key}");
	        userData.push(${entry.value});
	    </c:forEach>

	    const ctxUser = document.getElementById('userChart').getContext('2d');
	    new Chart(ctxUser, {
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
	</script>	
</body>
</html>
