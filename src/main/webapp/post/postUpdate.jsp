<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>학부모정보통 - 글 수정</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/main/layout.css" />
  <link rel="stylesheet" href="<%=request.getContextPath()%>/main/all_search.css" />
  <script>
		  const contextPath = "<%= request.getContextPath() %>";
  </script>
</head>

<body>
  <div class="wrapper">
    <div id="headerArea"></div>
    
    <main>
      <div class="center-wrapper">
        <form action="<%=request.getContextPath()%>/post.do?m=update" 
              method="post" 
              enctype="multipart/form-data">   
              
          <input type="hidden" name="path" value="${path}"><!-- 관리자페이지용 -->
    
          <div style="border-top:2px solid black;border-bottom:2px solid black;padding-top:20px;padding-bottom:20px;">
            <label style="font-size:46px; margin:10px; margin-bottom:0px">
              <c:choose>
                <c:when test="${dto.category_num eq 1}">자유게시판</c:when>
                <c:when test="${dto.category_num eq 2}">입시정보 게시판</c:when>
                <c:when test="${dto.category_num eq 3}">고등학교 게시판</c:when>
                <c:when test="${dto.category_num eq 4}">공지사항</c:when>
                <c:otherwise>본 게시판입니다.</c:otherwise>
              </c:choose>
            </label>
          </div>

          <input type="hidden" name="post_num" value="${dto.post_num}">
          <input type="hidden" name="category_num" value="${dto.category_num}">
          <input type="hidden" name="email" value="${dto.email}">

          <div class="divide-block" style="width:100%;height:30px;background-color: white;"></div>

         <div style="height: 20px;padding:10px; display:flex;">
            <input type="text" name="post_subject" value="${dto.post_subject}" 
                   style="width:100%;height:100%;" required>
          </div> 

          <div style="height: 20px;padding:10px; display:flex;">
            <label>음란물, 차별, 비하 혐오 및 초상권, 저작권 침해 게시물은 민·형사상의 책임을 질 수 있습니다.</label>
          </div>

          <div style="height: 20px;padding:10px; display:flex;">
            <img src="<%=request.getContextPath()%>/post/assets/file.svg" style="width:20px;height:20px;">
            <label for="files" style="color:blue;text-decoration: underline;cursor:pointer;">파일첨부</label>
            <input type="file" id="files" name="files" multiple style="display:none;">
          </div>

          <div style="width:100%;height: 400px;padding:10px; display:flex;">
            <textarea name="post_content" style="width:100%;" required>${dto.post_content}</textarea>
          </div>

          <div style="width:100%;height: 100px;padding:10px; display:flex;justify-content: flex-end;gap:20px;">
			            <button type="button" 
			        style="
			            background-color:rgb(82, 82, 95);
			            color:white;
			            font-size: 20px;
			            padding: 0;
			            border-radius: 15px;
			            height: 60px;
			            width: 130px;
			            text-align: center;
			            display: flex;
			            align-items: center;
			            justify-content: center;"
			        onclick="history.back()">
			  취소하기
			</button>
			
			<button type="submit" 
			        style="
			            background-color:blue;
			            color:white;
			            font-size: 20px;
			            padding: 0;
			            border-radius: 15px;
			            height: 60px;
			            width: 130px;
			            text-align: center;
			            display: flex;
			            align-items: center;
			            justify-content: center;
			        ">
			  수정하기
			</button>
          </div>
        </form>
      </div>
    </main>

    <footer>
      <p>회사소개 | 이용약관 | 개인정보처리방침 등등</p>
      <p>© 1999 - 2025 dcinside. All rights reserved.</p>
    </footer>
  </div>

  <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
  <script src="<%=request.getContextPath()%>/post/js/postUpdate.js"></script>
</body>
</html>