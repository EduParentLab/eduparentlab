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
import util.PagingUtil;
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
        System.out.println("@admin role: " +role);
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
    	int page = 1;
    	int pageSize = 2;//18
    	int pageBlock = 5;    
    	String sort = "latest";
    	
	    PostService service = PostService.getInstance();
	    //ArrayList<Post> list = service.listNoticeS();	  	  
        int totalCount = service.getTotalPostsByCategory(4);
        String strPage = request.getParameter("page");
        if (strPage != null) page = Integer.parseInt(strPage);
        
	    PagingUtil paging = new PagingUtil(totalCount, page, pageSize, pageBlock);
	    List<Post> list = service.listPagingS(paging.getStartRow(), pageSize, sort, 4);  
	    
	 
	    request.setAttribute("notice", list);	  	
	    request.setAttribute("paging", paging); 
	    
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
    	//System.out.println("0. email:"+useremail);
    	if(useremail != null) {
    		response.sendRedirect(request.getContextPath() + "/admin/admin.do?m=mypage&email="+useremail);
    	}else {
    		response.sendRedirect(request.getContextPath() + "/admin/admin.do");
    	}
    
    	// 현재 URL로 리다이렉트
    	//response.sendRedirect(currentUrl);
    	
        //response.sendRedirect(request.getRequestURI());
    }
    private void getMypageWithdrawnUser(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {    	
    	String email = request.getParameter("email");
    	UserService userService = UserService.getInstance();
    	User user = userService.getUserByEmailS(email);    	
    	//System.out.println("user: "+user);
    	PostService postService = PostService.getInstance();
 	    List<Post> list = postService.mypagePostListS(email);
 	    int mypagePostCount = postService.mypagePostCountS(email);
 	   // System.out.println("AdminController: email=" + email + ", 글 개수=" + list.size());
 	    int mypostLike = postService.mypageLikeCountS(email);
 	    int mypageCommentCount = postService.mypageCommentCountS(email);
	    
 	    request.setAttribute("mypost", list);
 	    request.setAttribute("mypostcount", mypagePostCount);
 	    request.setAttribute("mypostlike", mypostLike);
 	    request.setAttribute("mycommentcount", mypageCommentCount);
 	    request.setAttribute("loginOkUser", user);
 	    String view = "/mypage/mypage.jsp";        
	    RequestDispatcher rd = request.getRequestDispatcher(view);
	    rd.forward(request, response);        
    }    
}
