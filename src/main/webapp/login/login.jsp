<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>학부모정보통</title>
  <link rel="stylesheet" href="../main/layout.css" />
  <link rel="stylesheet" href="login.css" />
		<script src="trim.js"></script>
		<script>
		  const contextPath = "<%= request.getContextPath() %>";
		</script>
		<script language="javascript"> 
		function check(){
		    var emailval = f.email.value;   // email
		    emailval = trim(emailval);
		    if(emailval.length == 0){
		        alert("아이디를 넣어주세요");
		        f.email.value = "";
		        f.email.focus();
		        return false;
		    }else{
		        pass = checkByteLen(emailval, 50);
		        if(!pass){
		            alert("아이디가 너무 길어요");
		            f.email.focus();
		            return false;
		        }
		    }
		    
		    var pwdval = f.password.value;   // password
		    pwdval = trim(pwdval);
		    if(pwdval.length == 0){
		        alert("비번을 넣어주세요");
		        f.password.value = "";
		        f.password.focus();
		        return false;
		    }else{
		        pass = checkByteLen(pwdval, 30);
		        if(!pass){
		            alert("비번이 너무 길어요");
		            f.password.focus();
		            return false;
		        }
		    }
		
		    f.submit();
			}
		
		    function checkByteLen(str, size){
		        var byteLen = getByteLen(str);
				if(byteLen <= size){
					return true;
				}else{
					return false;
				}
			}
			function getByteLen(str){
			   return str.replace(/[\0-\x7f]|([0-\u07ff]|(.))/g,"$&$1$2").length;
		    }
			
			function enterCheck(elm){
				if(event.keyCode == 13){
					if(elm == f.email){
						f.password.focus();
					}else{
						check();
					}
				}
			}
		</script>
</head>


<body onload="document.f.email.focus()">
  <div class="wrapper">
	<div id="headerArea"></div>

    <main>
      <div class="center-wrapper">
        <div class="logincontainer">
            <div class="login-title">
                <h2>Login</h2>
            </div>
            <form name="f" action="login.do?m=check" method="post">
            <div class="login-input">
            <input type="text" name="email" placeholder="아이디(이메일)" onkeydown="enterCheck(this)" />
            </div>
            <div class="password-input">
            <input type="password" name="password" placeholder="비밀번호" onkeydown="enterCheck(this)" />
            </div>
            <button type="submit" class="login-btn" onclick="check()">로그인</button>
            </form>
        </div>
      </div>
    </main>
    <div id="footerArea"></div>
  </div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="search.js"></script>
<script src="login.js"></script>



</body>
</html>
