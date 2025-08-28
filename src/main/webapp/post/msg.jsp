<%@ page contentType="text/html;charset=utf-8"%>

<script>
<%
    boolean flag = (Boolean)request.getAttribute("flag");
    String kind = (String)request.getAttribute("kind");
    if(kind.equals("insert")){
%>
	if(<%=flag%>){
    	alert("입력성공");
	} else {
    	alert("입력실패");
	}
<%
	} else if("update".equals(kind)){
%>
	if(<%=flag%>){
    	alert("수정성공");
	} else {
  		 alert("수정실패");
}
<%
	} else if("delete".equals(kind)){
%>
	if(<%=flag%>){
    alert("삭제성공");
	} else {
    alert("삭제실패");
}
<%
}
%>
location.href="post.do";
</script>