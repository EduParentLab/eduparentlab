<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
<style>
 body { font-family: '맑은 고딕', sans-serif; padding:40px; }
 table { border-collapse:collapse; width:600px; margin:auto; }
 td { border:1px solid #aaa; padding:10px; }
 td.label { width:25%; background:#f7f7f7; font-weight:bold; }
 .btns { text-align:center; margin-top:15px; }
 a { text-decoration:none; color:#0077cc; }
</style>
</head>
<body>

<h2 style="text-align:center">게시글 수정</h2>

<form method="post" action="<%=request.getContextPath()%>/post.do?m=update">
    <input type="hidden" name="post_num" value="${dto.post_num}"/>
    <table>
        <tr>
            <td class="label">제목</td>
            <td><input type="text" name="post_subject" value="${dto.post_subject}" style="width:95%" required></td>
        </tr>
        <tr>
            <td class="label">내용</td>
            <td><textarea name="post_content" style="width:95%; height:150px" required>${dto.post_content}</textarea></td>
        </tr>
        <tr>
            <td class="label">카테고리</td>
            <td>
                <input type="number" name="category_num" value="${dto.category_num}" min="1" max="3" required>
            </td>
        </tr>
        <tr>
            <td class="label">작성자(이메일)</td>
            <td>${dto.email}</td>
        </tr>
    </table>
    <div class="btns">
        <input type="submit" value="수정완료">
        <a href="<%=request.getContextPath()%>/post.do?m=list">목록</a>
    </div>
</form>

</body>
</html>
