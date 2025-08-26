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
import java.util.ArrayList;



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
		String view = "admin.jsp";
	    RequestDispatcher rd = request.getRequestDispatcher(view);
	    rd.forward(request, response);
	}
    private void getNews(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    PostService service = PostService.getInstance();
	    ArrayList<Post> postlist = service.listS();	   
	    request.setAttribute("news", postlist);	    
	    
	}
    private void getUser(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    UserService service = UserService.getInstance();
	    ArrayList<User> userlist = service.listS();	   
	    request.setAttribute("user", userlist);	    
	    
	}
    private void getGhost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    UserService service = UserService.getInstance();
	    ArrayList<User> ghostlist = service.listGhostUserS();	   
	    request.setAttribute("ghost", ghostlist);	   
	}
}
