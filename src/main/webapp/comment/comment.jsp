<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form name="f" action="comment.do?m=insert" method="post">
	<table>
		<tr>
			<th>댓글</th>
			<td><textarea name ="content"></textarea></td>
			<td><button type="submit" >등록</button></td>
		</tr>
	</table>
		
	</form>
	<c:forEach var="c" items="${comment}" >
	<table>
		<tr>
			<th colspan='2'>댓글창</th>
		</tr>
		<tr>
			${c.comment_content}
			${c.comment_date}
			<a href="comment.do?m=delete">삭제</a>
		</tr>
	</table>
	</c:forEach>
</body>
</html>