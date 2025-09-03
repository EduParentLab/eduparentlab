<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page = "../login/login_check_modul.jsp"/>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>학부모정보통</title>
  <link rel="stylesheet" href="../main/layout.css" />
  <link rel="stylesheet" href="../mypage/edit_info.css" />
</head>


<body>
  <div class="wrapper">

<div id="headerArea"></div>

    <main>
      <div class="center-wrapper">
        <div class="resistcontainer">
        
            <div class="reist-title">
                <h2>개인정보수정</h2>
            </div>
          
        <form action="mypage_update.do" method="post">
            <div class="form-input">
                <input type="text" name="email" value="${loginOkUser.email}" readonly>
            </div>
            <div class="form-input">
                <input type="text" name="nickname" value="${loginOkUser.nickname}" placeholder="닉네임">
            </div>
            <div class="form-input">
                <input type="password" name="password" placeholder="새 비밀번호 입력">
            </div>
            <div class="form-input">
                <input type="password" name="passwordConfirm" placeholder="비밀번호 확인">
            </div>
            <div class="form-input">
                <input type="text" name="name" value="${loginOkUser.name}" readonly>
            </div>
            
            
   
            <div class="form-input">
                <input type="text" name="birth" value="${loginOkUser.birth}" placeholder="생년월일 (예: 1999-01-01)">
            </div>
            <div class="form-input">
                <input type="text" name="gender"
         		value="${loginOkUser.gender eq 'M' ? '남' : (loginOkUser.gender eq 'F' ? '여' : '')}" placeholder="성별 (예: 남 / 여)">
            </div>

            
            
            <div class="form-input">
                <input type="text" name="phone" value="${loginOkUser.phone}" placeholder="전화번호 (예: 010-1111-2222)">
            </div>
            <div class="form-input">
                <input type="text" name="cdate" value="${loginOkUser.cdate}" readonly >
            </div>
           
                <button type="submit" class="resist-btn">수정완료</button>
         </form>
                           
         <form action="${pageContext.request.contextPath}/mypage/delete.do" method="post">
    			<button type="submit" class="withdraw-btn">회원탈퇴</button>
		 </form>
           
            </div>
        </div>
    </main>


    <footer>
      <p>회사소개 | 이용약관 | 개인정보처리방침 등등</p>
      <p>© 1999 - 2025 dcinside. All rights reserved.</p>
    </footer>
  </div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="search.js"></script>
<script src="login.js"></script>
<script src="edit_info.js"></script>
<script> const contextPath = "<%=request.getContextPath()%>";</script>



</body>
</html>
