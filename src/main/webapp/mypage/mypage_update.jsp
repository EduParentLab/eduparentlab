<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page = "../login/login_check_modul.jsp"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인정보 수정</title>
</head>
<body>
    <h1>나의 정보 수정</h1>
    
     <form action="mypage_update.do" method="post">
     <table border="1">
       <tr>
          <th>이메일</th>
          <td><input type="text" name="email" value="${loginOkUser.email}" readonly style="background-color: lightgreen;"></td>
       </tr>
       <tr>
          <th>닉네임</th>
          <td>
          	 <input type="text" name="nickname" value="${loginOkUser.nickname}">
          	 <button>중복확인</button>        
          </td>
       </tr>
       <tr>
          <th>비밀번호</th>
          <td><input type="password" name="password" placeholder="새 비밀번호 입력"></td>
       </tr>
       <tr>
          <th>이름</th>
          <td><input type="text" name="name" value="${loginOkUser.name}" readonly style="background-color: lightgreen;"></td>
       </tr>
       <tr>
          <th>생년월일</th>
          <td><input type="date" name="birth" value="${loginOkUser.birth}"></td>
       </tr>
       <tr>
		  <th>성별</th>
		  <td>
		      <input type="radio" name="gender" value="M" ${loginOkUser.gender eq 'M' ? 'checked' : ''}> 남
		      <input type="radio" name="gender" value="F" ${loginOkUser.gender eq 'F' ? 'checked' : ''}> 여
		  </td>
	   </tr>
       <tr>
          <th>전화번호</th>
          <td><input type="text" name="phone" value="${loginOkUser.phone}"></td>
       </tr>
       <tr>
          <th>가입일</th>
          <td><input type="date" name="cdate" value="${loginOkUser.cdate}" readonly style="background-color: lightgreen;"></td>
       </tr>
     </table>
     <button type="submit">수정완료</button>
     </form>
       
     <br><br>
       
     <form action="./delete.do" method="post" onsubmit="return confirm('정말 탈퇴하시겠습니까?');">
     <button type="submit" style="color:red;">회원탈퇴</button>
     </form>
     
</body>
</html>
     