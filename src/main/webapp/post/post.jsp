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
        .category-menu { margin-bottom: 20px; }
        .category-menu a { margin-right: 10px; font-weight: bold; }
    </style>
</head>
<body>

<h2 style="text-align:center">게시판 목록</h2>
<a href="index.jsp">인덱스로</a>


<div class="category-menu">
    <a href="<%=request.getContextPath()%>/post.do?m=list&category_num=1">공지게시판</a>
    <a href="<%=request.getContextPath()%>/post.do?m=list&category_num=2">입시게시판</a>
    <a href="<%=request.getContextPath()%>/post.do?m=list&category_num=3">자유게시판</a>
</div>


<div class="search-bar">
    <form action="<%=request.getContextPath()%>/post.do" method="get">
        <input type="hidden" name="m" value="list"/>
        <input type="hidden" name="category_num" value="${category_num}"/>

        <select name="type">
            <option value="title_content" ${param.type == 'title_content' ? 'selected' : ''}>제목+내용</option>
            <option value="title" ${param.type == 'title' ? 'selected' : ''}>제목</option>
            <option value="writer" ${param.type == 'writer' ? 'selected' : ''}>작성자</option>
        </select>
        <input type="text" name="keyword" value="${param.keyword}" placeholder="검색어 입력"/>

        <button type="submit">🔍</button>

        <select name="rows" onchange="this.form.submit()">
            <option value="10" ${param.rows == '10' ? 'selected' : ''}>10개씩</option>
            <option value="20" ${param.rows == '20' ? 'selected' : ''}>20개씩</option>
            <option value="50" ${param.rows == '50' ? 'selected' : ''}>50개씩</option>
        </select>

        <select name="sort" onchange="this.form.submit()">
            <option value="latest" ${param.sort == 'latest' ? 'selected' : ''}>최신순</option>
            <option value="views" ${param.sort == 'views' ? 'selected' : ''}>조회수순</option>
        </select>
    </form>
</div>


<div class="top-bar">
    <a href="<%=request.getContextPath()%>/post.do?m=input&category_num=${category_num}">➕ 글쓰기</a>
</div>


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
            <tr><td colspan="5">등록된 게시글이 없습니다.</td></tr>
        </c:when>
        <c:otherwise>
            <c:forEach var="dto" items="${list}">
                <tr>
                    <td>${dto.post_num}</td>
                    <td>
                        <a href="<%=request.getContextPath()%>/post.do?m=content&seq=${dto.post_num}&category_num=${category_num}">
                            ${dto.post_subject}
                        </a>
                    </td>
                    <td>${dto.nickname}</td>
                    <td>${dto.post_date}</td>
                    <td>${dto.post_view}</td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</table>


<div class="pagination">
    <c:if test="${paging.hasPrev()}">
        <a href="post.do?m=list&page=${paging.startPage - 1}&sort=${param.sort}&rows=${param.rows}&type=${param.type}&keyword=${param.keyword}&category_num=${category_num}">◀</a>
    </c:if>

    <c:forEach var="i" begin="${paging.startPage}" end="${paging.endPage}">
        <a href="post.do?m=list&page=${i}&sort=${param.sort}&rows=${param.rows}&type=${param.type}&keyword=${param.keyword}&category_num=${category_num}"
           class="${i == paging.currentPage ? 'active' : ''}">${i}</a>
    </c:forEach>

    <c:if test="${paging.hasNext()}">
        <a href="post.do?m=list&page=${paging.endPage + 1}&sort=${param.sort}&rows=${param.rows}&type=${param.type}&keyword=${param.keyword}&category_num=${category_num}">▶</a>
    </c:if>
</div>

</body>
</html>
