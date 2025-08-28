<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<meta charset="utf-8">
<c:if test="${empty loginOkUser}">
	<script>
		alert("회원서비스입니다. 먼저 로그인을 하고 오세요~");
		location.href="../login/login.do?m=form";
	</script>
</c:if>