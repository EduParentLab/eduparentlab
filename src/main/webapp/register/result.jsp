<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<script>
    const result = ${registerResult}; // true or false
    const errorMsg = "${registerErrorMsg}";
    if(result){
        alert("회원가입이 성공적으로 완료되었습니다.");
        location.href="${pageContext.request.contextPath}/login/login.do?m=form";
    }else{
        if(errorMsg){ 
            alert(errorMsg);
        }else{
            alert("회원가입에 실패했습니다. 다시 시도해주세요.");
        }
        location.href="register.jsp";
    }
</script>