<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page = "../login/login_check_modul.jsp"/>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

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
          
            <!-- 아이디(이메일) -->
			<div class="form-input">
			  <label for="email">아이디</label>
			  <input type="text" name="email" id="email" value="${loginOkUser.email}" readonly>
			</div>
            
            <!-- 닉네임 -->
            <div class="form-input">
            <label for="nickname">닉네임</label>
              <input type="text" name="nickname" id="nickname" value="${loginOkUser.nickname}" placeholder="닉네임">
              <button type="button" class="id-check-btn" onclick="checkNickname()">중복확인</button>
            </div>
            
            <!-- 비밀번호 -->
            <div class="form-input">
            <label for="password">비밀번호</label>
              <input type="password" name="password" placeholder="새 비밀번호 입력">
            </div>
            <div class="form-input">
            <label  for="passwordConfirm">비밀번호 확인</label>
              <input  type="password" name="passwordConfirm" placeholder="비밀번호 확인">
            </div>

            <!-- 이름 -->
            <div class="form-input">
            <label for="name">이름</label>
              <input type="text" name="name" value="${loginOkUser.name}" readonly>
            </div>

            <!-- 생년월일 (분리된 입력) -->
            <div class="form-input birth-inputs">
  			<label class="birth-label" style="font-size:20px;">생년월일</label>
              <input type="text" name="birth"  placeholder="YYYY" value="${fn:substring(loginOkUser.birth,0,4)}" style="text-align:center; border:1px solid black" />
              <input type="text" name="birth2" placeholder="MM"   value="${fn:substring(loginOkUser.birth,5,7)}" style="text-align:center; border:1px solid black"/>
              <input type="text" name="birth3" placeholder="DD"   value="${fn:substring(loginOkUser.birth,8,10)}" style="text-align:center; border:1px solid black"/>
            </div>

            <!-- 전화번호 (분리된 입력) -->
            <div class="form-input phone-inputs">
            <label class="phone-label" style="font-size:20px;">전화번호</label>
              <input type="text" name="phone1" value="${fn:substring(loginOkUser.phone,0,3)}" placeholder="010" style="text-align:center; border:1px solid black" />
              <input type="text" name="phone2" value="${fn:substring(loginOkUser.phone,4,8)}" placeholder="1234" style="text-align:center; border:1px solid black"/>
              <input type="text" name="phone3" value="${fn:substring(loginOkUser.phone,9,13)}" placeholder="5678" style="text-align:center; border:1px solid black"/>
            </div>
           
            <button type="submit" class="resist-btn">수정완료</button>
          </form>
                           
          <form action="${pageContext.request.contextPath}/mypage/delete.do" method="post">
            <button type="submit" class="withdraw-btn" onclick="return confirm('정말 회원탈퇴 하시겠습니까?');">회원탈퇴</button>
          </form>
        </div>
      </div>
    </main>
    <div id="footerArea"></div>
  </div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="edit_info.js"></script>
<script src="../register/register.js"></script>
<script> const contextPath = "<%=request.getContextPath()%>";</script>
</body>
</html>
