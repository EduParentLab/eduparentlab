<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<meta charset="utf-8">

<script src="../js/trim.js"></script>
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


<body onload="document.f.email.focus()">
<center>
   <form name="f" action="login.do?m=check" method="post">
   <table border="0" width="300">
      <tr>
         <td colspan="2" align="center"><h2>로그인</h2></td> 
      </tr>
      <tr>
         <td colspan="2" align="center">
            <input type="text" name="email" size="25"
                   placeholder="아이디(이메일)" onkeydown="enterCheck(this)">
         </td>
      </tr>
      <tr>
         <td colspan="2" align="center">
            <input type="password" name="password" size="25"
                   placeholder="비밀번호" onkeydown="enterCheck(this)">
         </td>
      </tr>
      <tr>
         <td colspan="2" align="center">
            <input type="button" value="로그인" onclick="check()"/>
            <input type="reset" value="취소"/>
         </td> 
      </tr>
   </table>
</form>
</center>
</body>


