<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="model.constant.LoginConst"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<script>
	const result = ${result};
	
	if(result === <%=LoginConst.NO_ID%>){
		alert("이메일이 맞지 않습니다.");
		location.href="login.do?m=form";
	}else if(result === <%=LoginConst.NO_PWD%>){
		alert("비밀번호가 맞지 않습니다.")
		location.href="login.do?m=form";
	}else{
		alert("로그인 성공");
		location.href="${pageContext.request.contextPath}/main_page/headerBox.jsp";
	}
</script>
