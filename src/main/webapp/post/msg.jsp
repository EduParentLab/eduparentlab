<%@ page contentType="text/html;charset=utf-8"%>

<script>
<%
    boolean flag = (Boolean)request.getAttribute("flag");
    String kind = (String)request.getAttribute("kind");
    String categoryNum = request.getParameter("category_num"); // 전달된 파라미터 꺼내기
    String email = request.getParameter("email");    
    String path = request.getParameter("path");
        
    if("insert".equals(kind)){
%>
    if(<%=flag%>){
        alert("입력성공");  
        
        if("<%=path%>" == "admin"){
        	location.href="admin/admin.do";    
        }else{
        	location.href="post.do?m=list&category_num=<%=categoryNum%>";  
       	}        
    } else {
        alert("입력실패");
        history.back();
    }
<%
    } else if("update".equals(kind)){
%>
    if(<%=flag%>){
        alert("수정성공");
    } else {
        alert("수정실패");
    }
    location.href="post.do?m=list&category_num=" + "<%=request.getParameter("category_num")%>";
<%
    } else if("delete".equals(kind)){
%>
    if(<%=flag%>){
        alert("삭제성공");
    } else {
        alert("삭제실패");
    }
    location.href="post.do?m=list&category_num=" + "<%=request.getParameter("category_num")%>";
<%
    }
%>
</script>
