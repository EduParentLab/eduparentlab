<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<script>
    const mode   = "${mode}";   // "update" 또는 "delete"
    const result = ${result};   // true or false

    if(mode === "update"){
        if(result){
            alert("정보수정이 성공적으로 완료되었습니다.");
            location.href="${pageContext.request.contextPath}/mypage/mypage.do";
        }else{
            alert("정보수정에 실패했습니다. 다시 시도해주세요.");
            location.href="${pageContext.request.contextPath}/mypage/mypage.do";
        }
    }else if(mode === "delete"){
        if(result){
            alert("회원탈퇴가 완료되었습니다.");
            location.href="${pageContext.request.contextPath}/main/main.do";
        }else{
            alert("회원탈퇴에 실패했습니다. 다시 시도해주세요.");
            location.href="${pageContext.request.contextPath}/mypage/mypage.do";
        }
    }
</script>