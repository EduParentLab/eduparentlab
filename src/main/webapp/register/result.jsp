<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 결과</title>
</head>
<body>
<center>
    <h1>회원가입 결과</h1>

    <c:choose>
        <c:when test="${result}">
            <h2 style="color:green;">회원가입이 성공적으로 완료되었습니다</h2>
            <a href="../index.jsp">메인으로</a> |
            <a href="../login/login.do?m=form">로그인 하러가기</a>
        </c:when>
        <c:otherwise>
            <h2 style="color:red;">회원가입에 실패했습니다</h2>
            <p>다시 시도해주세요.</p>
            <a href="register.jsp">회원가입 다시하기</a>
        </c:otherwise>
    </c:choose>

</center>
</body>
</html>

