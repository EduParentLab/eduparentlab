<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>처리 결과</title>
</head>
<body>
<script>
<%
    boolean flag = (Boolean)request.getAttribute("flag");
    String kind = (String)request.getAttribute("kind");
    String path = request.getParameter("path");

    Object categoryAttr = request.getAttribute("category_num");
    String categoryNum = null;
    
    if (categoryAttr != null) {
        categoryNum = String.valueOf(categoryAttr);
        
    } else {
        categoryNum = request.getParameter("category_num");
        
    }

    if (categoryNum == null || categoryNum.equals("null") || categoryNum.isEmpty()) {
        categoryNum = "1";
    }   
%>

<% if("insert".equals(kind)) { %>
    if (<%=flag%>) {
    	if("<%=path%>" == "admin"){
        	location.href="admin/admin.do";    
        }else{
        	location.href="post.do?m=list&category_num=<%=categoryNum%>";  
       	}  
    } else {
        alert("입력실패");
        history.back();
    }
<% } else if("update".equals(kind)) { %>
    if (<%=flag%>) {
        location.href="post.do?m=list&category_num=<%=categoryNum%>";
    } else {
        alert("수정실패");
        history.back();
    }
<% } else if("delete".equals(kind)) { %>
    if (<%=flag%>) {
        location.href="post.do?m=list&category_num=<%=categoryNum%>";
    } else {
        alert("삭제실패");
        history.back();
    }
<% } %>
</script>
</body>
</html>
