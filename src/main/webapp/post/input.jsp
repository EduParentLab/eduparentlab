<%@ page contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>게시판 글쓰기</title>
    <style>
        body {
            font-family: 'Segoe UI', Arial, sans-serif;
            background: #f9f9f9;
            margin: 0;
            padding: 40px;
        }
        .container {
            width: 600px;
            margin: auto;
            background: #fff;
            border-radius: 10px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
            padding: 30px;
        }
        h2 {
            text-align: center;
            color: #0077cc;
            margin-bottom: 20px;
        }
        table { width: 100%; border-collapse: collapse; }
        td { padding: 10px; }
        td.label { width: 25%; text-align: right; font-weight: bold; color: #555; }
        input[type="text"], textarea, input[type="file"], select {
            width: 95%; padding: 8px; border: 1px solid #ccc; border-radius: 6px; font-size: 14px;
        }
        textarea { resize: none; height: 120px; }
        .buttons { text-align: center; margin-top: 20px; }
        .buttons input {
            padding: 8px 20px; margin: 0 5px; border: none; border-radius: 6px; cursor: pointer; font-size: 14px;
        }
        .btn-submit { background-color: #0077cc; color: white; }
        .btn-reset { background-color: #f0f0f0; color: #333; }
        .top-links { text-align: right; margin-bottom: 10px; }
        .top-links a { color: #0077cc; text-decoration: none; font-size: 14px; }
        .top-links a:hover { text-decoration: underline; }
    </style>
</head>
<body onload="document.input.email.focus()">

<div class="container">
    <div class="top-links">
        <a href="<%=request.getContextPath()%>/post.do?m=list">글목록</a>
    </div>
    <h2>게시글 작성</h2>


    <form name="input" method="post" action="<%=request.getContextPath()%>/post.do?m=insert" enctype="multipart/form-data">
        <table>
            <tr>
                <td class="label">이메일</td>
                <td><input type="text" name="email" required></td>
            </tr>
            <tr>
                <td class="label">글제목</td>
                <td><input type="text" name="post_subject" required></td>
            </tr>
            <tr>
                <td class="label">글내용</td>
                <td><textarea name="post_content" required></textarea></td>
            </tr>

            <tr>
                <td class="label">게시판</td>
                <td>
                    <select name="category_num" required>
                        <option value="1">공지게시판</option>
                        <option value="2">입시게시판</option>
                        <option value="3">자유게시판</option>
                    </select>
                </td>
            </tr>
         
            <tr>
                <td class="label">첨부파일</td>
                <td><input type="file" name="files" multiple></td>
            </tr>
        </table>
        <div class="buttons">
            <input type="submit" value="등록" class="btn-submit">
            <input type="reset" value="초기화" class="btn-reset">
        </div>
    </form>
</div>

</body>
</html>
