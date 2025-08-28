<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style>
    table, th, td {
        border: 1px solid black;
        border-collapse: collapse;
        padding: 5px;
    }
</style>
</head>
<body onload="document.f.email.focus()">
<center>
   <h1>회원가입</h1>

   <form name="f" action="/educationlab/register/register.do" method="post"> 
   
       <table border="1" width="500">
          <tr>
             <th>이메일</th> 
             <td>
             <input name="email1" size="10"> @ <input name="email2" size="10">
             <input type="button" value="중복확인"/>
             </td>
          </tr>
          <tr>
             <th>비밀번호</th> 
             <td><input type = "password" name="password" size="20"></td>
          </tr>
          <tr>
             <th>닉네임</th> 
             <td>
             <input name="nickname" size="20">
             <input type="button" value="중복확인"/>
             </td>
          </tr>
             <tr>
             <th>이름</th> 
             <td><input name="name" size="20"></td>
          </tr>
          <tr>
             <th>성별</th> 
             <td>
             <input type = "radio" name="gender" value="m">
             <label for="m">남</label>
             <input type = "radio" name="gender" value="f">
             <label for="f">여</label>
             </td>
          </tr>
          <tr>
             <th>생년월일</th> 
             <td>
    		 <input type="date" name="birth"">
 			 </td>
          </tr>
          <tr>
             <th>전화번호</th> 
             <td><input type="text" name="phone" size="20"></td>
          </tr>
          <tr>
             <td colspan="2" align="center">
                 <input type="submit" value="가입">
                 <input type="reset" value="취소">
             </td>
          </tr>
       </table>
   </form>
</center>
</body>
</html>
