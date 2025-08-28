<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>처리결과</title>
</head>
<body>
<center>
    <h1>처리결과</h1>

<c:if test="${not empty result}">
    <c:choose>
        <c:when test="${result}">
            <h2 style="color:green;">정보수정이 성공적으로 완료되었습니다</h2>
            <a href="mypage.jsp">마이페이지로</a> |
        </c:when>
        <c:otherwise>
            <h2 style="color:red;">정보수정에 실패했습니다</h2>
            <p>다시 시도해주세요.</p>
            <a href="mypage_update.jsp">정보수정 다시하기</a>
        </c:otherwise>
    </c:choose>
</c:if>


<c:if test="${not empty deleteResult}">
    <c:choose>
        <c:when test="${deleteResult}">
            <h2 style="color:green;">회원탈퇴가 완료되었습니다</h2>
            <a href="../index.jsp">홈으로</a>
        </c:when>
        <c:otherwise>
            <h2 style="color:red;">회원탈퇴에 실패했습니다</h2>
            <a href="mypage.jsp">마이페이지로</a>
        </c:otherwise>
    </c:choose>
</c:if>

</center>
</body>
</html>