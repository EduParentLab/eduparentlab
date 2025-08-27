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



@WebServlet("/admin/admin.do")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminController() {
        super();       
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
	    getNews(request, response);
		getUser(request, response);
		getGhost(request, response);	
		countPost(request, response);	
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
 	   System.out.println(map);
	}
}
