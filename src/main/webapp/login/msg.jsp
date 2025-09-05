<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="model.constant.LoginConst"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<script>
    const result = ${result};
    
    if(result === <%=LoginConst.NO_ID%> || result === <%=LoginConst.NO_PWD%>){
        alert("회원정보가 틀렸습니다. 다시 확인해주세요.");
        location.href="login.do?m=form";
    }else{
        location.href="${pageContext.request.contextPath}/main/main.do";
    }
</script>