<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>

<html lang="ko">
<head>
<meta charset="UTF-8">
<title>게시글 보기</title>
<style>
	 body { font-family:'맑은 고딕', sans-serif; padding:40px; }
	 table { border-collapse:collapse; width:600px; margin:auto; }
	 td { border:1px solid #aaa; padding:10px; }
	 th { background:#f0f0f0; width:25%; }
	 .btns { text-align:center; margin-top:15px; }
	 a { text-decoration:none; margin:0 10px; }
</style>
</head>
<body>

<h2 style="text-align:center">게시글 보기</h2>
<div style="text-align: center;">
	<a>공감수: </a> &nbsp&nbsp
	<a href="likes.do?m=add&post_num=${dto.post_num}">공감</a>
</div>
<table>
  <tr>
    <th>번호</th><td>${dto.post_num}</td>
  </tr>
  <tr>
    <th>제목</th><td>${dto.post_subject}</td>
  </tr>
  <tr>
    <th>내용</th><td style="height:200px; text-align:left;">${dto.post_content}</td>
  </tr>
  <tr>
    <th>작성자</th><td>${dto.email}</td>
  </tr>
  <tr>
    <th>작성일</th><td>${dto.post_date}</td>
  </tr>
  <tr>
    <th>조회수</th><td>${dto.post_view}</td>
  </tr>
  
</table>
	<div class="btns">
	  <a href="<%=request.getContextPath()%>/post.do?m=edit&seq=${dto.post_num}">수정</a>
	  <a href="<%=request.getContextPath()%>/post.do?m=delete&seq=${dto.post_num}"
	     onclick="return confirm('정말 삭제할까요?');">삭제</a>
	  <a href="<%=request.getContextPath()%>/post.do?m=list">목록</a>
	</div>
</body>
</html>
<script>
	function likes(){
		
	}
</script>
