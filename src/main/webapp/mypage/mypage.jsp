<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page = "../login/login_check_modul.jsp"/>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>학부모정보통</title>
  <link rel="stylesheet" href="../main/layout.css"/>
  <link rel="stylesheet" href="../mypage/mypage.css"/>
</head>
<body>
  <div class="wrapper">
 <%--<jsp:include page="../login/headerBox.jsp"/> ... --%>
 	<div id="headerArea"></div>
    <main>
      <div class="center-wrapper">
        <div class="section-title">
           <p style="font-size:35px; font-weight:bold;color:#98c7e6;">마이페이지</p> 
        </div>
        <div class="section-discription">
            <div class="profile-box">
    <img src="https://cdn-icons-png.flaticon.com/512/847/847969.png" alt="사용자 아이콘" />
    <div class="profile-name">
    	<label class="hello-message">${loginOkUser.name}님 안녕하세요</label>
    </div>
  </div>
        </div>
        <div class="section-personal-info-title">
           <label style="font-size:20px; font-weight:bold ;color:#98c7e6;" >개인정보</label> 
        </div>
        <c:if test="${loginOkUser.email == sessionScope.loginOkUser.email}">
			<div style="display:flex;justify-content:flex-end;padding:15px" >
				<button class="revise-button" onclick="location.href='../mypage/edit_info.jsp'">
				  내 정보 수정
				</button>
			</div>
	    </c:if>       		
		</div>		
        <div class="section-personal-info-content">
            <div class="container">
                <div class="info-box">
                <h2>개인정보</h2>
                <div class="info-row"><div class="label">이름</div><div class="value">${loginOkUser.name}</div></div>
                <div class="info-row"><div class="label">닉네임</div><div class="value">${loginOkUser.nickname}</div></div>
                <div class="info-row"><div class="label">이메일</div><div class="value">${loginOkUser.email}</div></div>
                <div class="info-row"><div class="label">전화번호</div><div class="value">${loginOkUser.phone}</div></div>
                <div class="info-row"><div class="label">성별</div><div class="value">${loginOkUser.gender}</div></div>
                <div class="info-row"><div class="label">생년월일</div><div class="value">${loginOkUser.birth}</div></div>
                <div class="info-row"><div class="label">가입일</div><div class="value">${loginOkUser.cdate}</div></div>
                </div>
                <div class="stat-box">
                <h2>활동 통계</h2>
                <div class="stat-row"><div class="label">총 게시글</div><div class="value">${mypostcount}</div></div>
                <div class="stat-row"><div class="label">총 댓글</div><div class="value">${mycommentcount}</div></div>
                <div class="stat-row"><div class="label">받은 총 공감수</div><div class="value">${mypostlike}</div></div>
                </div>
            </div>
        </div>
        <div class="section-self-write-title">
           <label style="font-size:20px; font-weight:bold ;color:#98c7e6;" >내가 쓴 글</label> 
        </div>
	<c:choose>
  <c:when test="${fromAdmin}">
    <form action="${pageContext.request.contextPath}/admin/admin.do?m=delete&email=${loginOkUser.email}&page=${pageNum}" method="post">
  </c:when>
  <c:otherwise>
    <form action="${pageContext.request.contextPath}/mypage/mypage.do?m=delete&page=${pageNum}" method="post">
  </c:otherwise>
</c:choose>
        <div class="section-self-write-content">
            <div class="post-container">
                <table>
                <thead>
                    <tr>
                    <th>선택</th>
                    <th>제목</th>
                    <th>작성일</th>
                    <th>조회수</th>
                    <th>공감수</th>
                    </tr>
               </thead>
     <c:forEach var="p" items="${mypost}">
                <tbody>
                    <tr>
                    <td><input type="checkbox" name="chk" value="${p.post_num}"></td>
                    <td>
				     	<a href="<%=request.getContextPath()%>/post.do?m=content&seq=${p.post_num}&category_num=${p.category_num}">
				          ${p.post_subject}
				        </a>
				    </td>
                    <td><fmt:formatDate value="${p.post_date}" pattern="yyyy-MM-dd"/></td>
                    <td>${p.post_view}</td>
                    <td>${p.likes}</td>
                    </tr>
     </c:forEach>
                </tbody>
                </table>
				<div class="pagination">
				  <c:forEach var="i" begin="1" end="${totalPages}">
				    <c:choose>
				      <c:when test="${i == pageNum}">
				        <span class="active">${i}</span>
				      </c:when>
				      <c:otherwise>
				        <c:choose>
				          <c:when test="${fromAdmin}">
				            <a href="${pageContext.request.contextPath}/admin/admin.do?m=mypage&email=${loginOkUser.email}&page=${i}">${i}</a>
				          </c:when>
				          <c:otherwise>
				            <a href="${pageContext.request.contextPath}/mypage/mypage.do?page=${i}">${i}</a>
				          </c:otherwise>
				        </c:choose>
				      </c:otherwise>
				    </c:choose>
				  </c:forEach>
				</div>
            </div>
            <div class="delete-icon">
		        <button type="submit" title="삭제">
		        	<img src="https://cdn-icons-png.flaticon.com/512/1214/1214428.png" alt="삭제" />
		        </button>
	       </div>
        </div>
      </div>
      </form>
    </main>
    <div id="footerArea"></div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script> const contextPath = "<%=request.getContextPath()%>";</script>
<script src="<%=request.getContextPath()%>/main_page.js"></script>
<script src="mypage.js"></script>
<script>
document.addEventListener("DOMContentLoaded", function() {
  const form = document.querySelector("form");
  const deleteBtn = form.querySelector("button[type='submit']");

  form.addEventListener("submit", function(e) {
    const checked = form.querySelectorAll("input[name='chk']:checked");
    if (checked.length === 0) {
      e.preventDefault(); // form 전송 막기
      alert("삭제 할 글을 체크해주세요.");
    }
  });
});
</script>
</body>
</html>