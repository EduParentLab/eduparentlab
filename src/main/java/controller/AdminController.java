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
        System.out.println("@admin role: " +role);
        if (loginUser == null || !"admin".equals(role)) {
            response.sendRedirect(request.getContextPath() + "/post.do?m=list&error=unauthorized");
            return;
        }
    	getNews(request, response);
		getUser(request, response);
		getGhost(request, response);	
		countPost(request, response);
		countUser(request, response);
		String view = "admin.jsp";
	    RequestDispatcher rd = request.getRequestDispatcher(view);
	    rd.forward(request, response);
	}
    private void getNews(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    PostService service = PostService.getInstance();
	    ArrayList<Post> list = service.listS();	   
	    request.setAttribute("news", list);	    
	    
	}
    private void getUser(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    UserService service = UserService.getInstance();
	    ArrayList<User> list = service.listS();	   
	    request.setAttribute("user", list);	    
	    
	}
    private void getGhost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    UserService service = UserService.getInstance();
	    ArrayList<User> list = service.listGhostUserS();	   
	    request.setAttribute("ghost", list);	 	    
	}
    private void countPost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
    	PostService service = PostService.getInstance();
 	    LinkedHashMap<String, Integer> map = service.countPostS();	   
 	    request.setAttribute("postCount", map);	   	  
	}
    private void countUser(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
    	UserService service = UserService.getInstance();
    	LinkedHashMap<Date, Integer> map = service.countUserS();
    	request.setAttribute("userCount", map);    	
    }
}
