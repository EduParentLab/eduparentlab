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
	<a>공감수: ${dto.likes}</a> &nbsp&nbsp
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
    <th>내용</th>
    <td style="height:200px; text-align:left;">${dto.post_content}</td>
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
  <tr>
    <th>첨부파일</th>
    <td>
      <c:choose>
        <c:when test="${empty fileList}">
          첨부파일 없음
        </c:when>
        <c:otherwise>
          <c:forEach var="file" items="${fileList}">
            <div>
              <a href="<%=request.getContextPath()%>${file.file_path}" 
                 download="${file.file_oname}">
                ${file.file_oname}
              </a>
            </div>
          </c:forEach>
        </c:otherwise>
      </c:choose>
    </td>
  </tr>
</table>

<div class="btns">
  <a href="<%=request.getContextPath()%>/post.do?m=edit&seq=${dto.post_num}">수정</a>
  <a href="<%=request.getContextPath()%>/post.do?m=delete&seq=${dto.post_num}"
     onclick="return confirm('정말 삭제할까요?');">삭제</a>
  <a href="<%=request.getContextPath()%>/post.do?m=list">목록</a>
</div>

	<form id="sortForm" method="get" action="post.do">
	    <input type="hidden" name="m" value="view">
	    <input type="hidden" name="seq" value="${dto.post_num}">
	    <select name="latest" onchange="this.form.submit()">
	    	<option value="false" ${param.latest=='false' ? 'selected' : ''}>오래된순</option>
	        <option value="true" ${param.latest=='true' ? 'selected' : ''}>최신순</option>
	        
	    </select>
	</form>

	<div id ="commentArea" style="width:600px; margin:auto; margin-top:30px"></div>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script>
	$(function(){
	    const postNum = ${dto.post_num};
	
	    function loadComments(page = 1){
	        const latest = $("select[name='latest']").val() || "true";
	
	        $.get("${pageContext.request.contextPath}/comment/comment.do", 
	            { m: 'list', post_num: postNum, latest: latest, page: page }, 
	            function(data){
	                $("#commentArea").html(data);
	            }
	        );
	    }
	
	    // 페이지 선택 시
	    $(document).on("click", ".pagination a", function(e){
	        e.preventDefault();
	        const page = $(this).data("page");
	        loadComments(page);
	    });
	
	    // 초기 로딩
	    loadComments();
	});
	</script>
	
</body>
</html>
<script>
	function likes(){
		
	}
</script>
