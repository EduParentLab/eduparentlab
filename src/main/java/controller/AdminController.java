package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.service.PostService;
import model.service.UserService;
import domain.Post;
import domain.User;
import java.io.IOException;
import java.util.*;
import java.sql.Date;

@WebServlet("/admin/admin.do")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
           
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
    	HttpSession session = request.getSession(false);
        User loginUser = (session != null) ? (User) session.getAttribute("loginOkUser") : null;
        String role = (session != null) ? (String) session.getAttribute("role") : null;
        
        if (loginUser == null || !"admin".equals(role)) {
            response.sendRedirect(request.getContextPath() + "/post.do?m=list&error=unauthorized");
            return;
        }  
        
	    String m = request.getParameter("m");	  
	    if(m == null) {
	    	m = "notice";
	    	RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
	    	rd.forward(request, response);
	    	return;
        }	    
	    switch(m) {
	    case "notice": getNotice(request, response); break;	    
	    case "user_list": getUser(request, response); break;	
	    case "withdrawn_list": getGhost(request, response); break;	
	    case "statistics": getStatistics(request, response); break;	 	    				   				      
	    case "delete": delete(request, response); break;	 
	    case "mypage": getMypageWithdrawnUser(request, response); break;	 
	    default: getNotice(request, response); break;
	    }   	
	}
    private void getNotice(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {    	
	    PostService service = PostService.getInstance();
	    ArrayList<Post> list = service.listNoticeS();	  	  
       
	    request.setAttribute("notice", list);	  		   
	    
	    String view = "notice.jsp";
	    RequestDispatcher rd = request.getRequestDispatcher(view);
	    rd.forward(request, response);	  
	}
    private void getUser(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    UserService service = UserService.getInstance();
	    ArrayList<User> list = service.listS();	   
	    request.setAttribute("user", list);	   	
	    String view = "user_list.jsp"; 
	    RequestDispatcher rd = request.getRequestDispatcher(view);
	    rd.forward(request, response);
	}
    private void getGhost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    UserService service = UserService.getInstance();
	    ArrayList<User> list = service.listGhostUserS();	   
	    request.setAttribute("ghost", list);	
	    String view = "withdrawn_list.jsp";
	    RequestDispatcher rd = request.getRequestDispatcher(view);
	    rd.forward(request, response);
	}
    private void getStatistics(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
    	countPost(request, response); 
    	countUser(request, response);    	
    	RequestDispatcher rd = request.getRequestDispatcher("statistics.jsp");
		rd.forward(request, response); 	  
    }
    private void countPost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {    	
    	PostService service = PostService.getInstance();
 	    LinkedHashMap<String, Integer> postCount = service.countPostS();	   
 	    request.setAttribute("postCount", postCount);   	    	  
	}
    private void countUser(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
    	UserService service = UserService.getInstance();
    	LinkedHashMap<Date, Integer> userCount = service.countUserS();
    	request.setAttribute("userCount", userCount);          	
    }
    private void delete(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
    	String[] chk = request.getParameterValues("chk");
    	System.out.println("delete:"+chk);
    	if (chk != null) {
            PostService postService = PostService.getInstance();
            for (String numStr : chk) {
                int post_num = Integer.parseInt(numStr);
                postService.deleteS(post_num);
            }
        }
    	String useremail = request.getParameter("email");    	
    	if(useremail != null) {
    		response.sendRedirect(request.getContextPath() + "/admin/admin.do?m=mypage&email="+useremail);//해당 이메일주소를 가진 사람의 마이페이지로 이동
    	}else {
    		response.sendRedirect(request.getContextPath() + "/admin/admin.do");//관리자페이지 메인으로 이동
    	}    
    }
    private void getMypageWithdrawnUser(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {    	
    	String email = request.getParameter("email");
    	UserService userService = UserService.getInstance();
    	User user = userService.getUserByEmailS(email);       
    	PostService postService = PostService.getInstance();
        int pageNum = 1;
        int pageSize = 10; 
        String strPage = request.getParameter("page");
        if (strPage != null) {
            try {
                pageNum = Integer.parseInt(strPage);
            } catch (NumberFormatException e) {
                pageNum = 1;
            }
        }
        int totalPosts = postService.mypagePostCountS(email);
        int totalPages = (int)Math.ceil((double)totalPosts / pageSize);
        if (pageNum > totalPages) pageNum = totalPages;
        if (pageNum < 1) pageNum = 1;        
    	List<Post> list = postService.mypagePostListPagingS(email, pageNum, pageSize);
 	    int mypagePostCount = postService.mypagePostCountS(email); 	   
 	    int mypostLike = postService.mypageLikeCountS(email);
 	    int mypageCommentCount = postService.mypageCommentCountS(email);
 	    request.setAttribute("mypost", list);
 	    request.setAttribute("mypostcount", mypagePostCount);
 	    request.setAttribute("mypostlike", mypostLike);
 	    request.setAttribute("mycommentcount", mypageCommentCount);
 	    request.setAttribute("loginOkUser", user); 	
 	    request.setAttribute("pageNum", pageNum); 
 	    request.setAttribute("pageSize", pageSize); 
 	    request.setAttribute("totalPages", totalPages); 
 	    request.setAttribute("fromAdmin", true); 
 	    String view = "/mypage/mypage.jsp";        
	    RequestDispatcher rd = request.getRequestDispatcher(view);
	    rd.forward(request, response);        
    }    
}
