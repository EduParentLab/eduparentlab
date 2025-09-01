package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
	    case "statistics": countPost(request, response); 
	    				   countUser(request, response); 	    				   
					       RequestDispatcher rd = request.getRequestDispatcher("statistics.jsp");
						   rd.forward(request, response); break;	
	    default: getNotice(request, response); break;
	    }    			
	}
    private void getNotice(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    PostService service = PostService.getInstance();
	    ArrayList<Post> list = service.listS();	   
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
