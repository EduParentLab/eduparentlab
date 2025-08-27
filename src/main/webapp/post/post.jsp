<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>게시판 목록</title>
    <style>
        body { font-family: '맑은 고딕', sans-serif; padding: 40px; }
        table { border-collapse: collapse; width: 100%; margin-top: 10px; }
        th, td { border: 1px solid #aaa; padding: 10px; text-align: center; }
        th { background-color: #f0f0f0; }
        a { text-decoration: none; color: #0077cc; }
        .top-bar { width: 100%; margin-bottom: 15px; text-align: right; }
        .pagination { text-align: center; margin-top: 30px; }
        .pagination a { text-decoration: none; margin: 0 5px; color: #333; }
        .search-bar { text-align: right; margin-bottom: 10px; }
        .search-bar select, .search-bar input { padding: 5px; margin-left: 5px; }
        .search-bar button { padding: 6px 12px; margin-left: 5px; }
    </style>
</head>
<body>

<h2 style="text-align:center">게시판 목록</h2>
 <a href="index.jsp">인덱스로</a>
<!-- 🔍 검색 영역 -->
<div class="search-bar">
    <form action="<%=request.getContextPath()%>/post.do" method="get">
        <input type="hidden" name="m" value="list"/>
        <select name="type">
            <option value="title_content">제목+내용</option>
            <option value="title">제목</option>
            <option value="writer">작성자</option>
        </select>
        <input type="text" name="keyword" placeholder="검색어를 입력하세요"/>
        <button type="submit">🔍</button>
        <select name="rows">
            <option value="10">10개씩</option>
            <option value="20">20개씩</option>
            <option value="50" selected>50개씩</option>
        </select>
    </form>
</div>

<!-- ✏ 글쓰기 버튼 -->
<div class="top-bar">
    <a href="<%=request.getContextPath()%>/post.do?m=input">➕ 글쓰기</a>
</div>

<!-- 📋 게시글 목록 -->
<table>
    <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>작성일</th>
        <th>조회수</th>
    </tr>

    <c:choose>
        <c:when test="${empty list}">
            <tr>
                <td colspan="5">등록된 게시글이 없습니다.</td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach var="dto" items="${list}">
                <tr>
                    <td>${dto.post_num}</td>
                    <td>
                        <a href="<%=request.getContextPath()%>/post.do?m=content&seq=${dto.post_num}">
                            ${dto.post_subject}
                        </a>
                    </td>
                    <td>${dto.email}</td>
                    <td>${dto.post_date}</td>
                    <td>${dto.post_view}</td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</table>

<!-- ◀▶ 페이지네이션 -->
<div class="pagination">
    <a href="#">◀</a>
    <c:forEach var="i" begin="1" end="15">
        <a href="<%=request.getContextPath()%>/post.do?m=list&page=${i}">${i}</a>
    </c:forEach>
    <a href="#">▶</a>
</div>

</body>
</html>
