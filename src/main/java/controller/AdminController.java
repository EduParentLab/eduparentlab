package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.service.PostService;
import domain.Post;
import java.io.IOException;
import java.util.ArrayList;



@WebServlet("admin/admin.do")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminController() {
        super();       
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String m = request.getParameter("m");
		if(m != null) {
			m = m.trim();
			switch(m){
				case "getNews": getNews(request, response); break;
				default: getNews(request, response);
			}
		}
	}
    private void getNews(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    PostService service = PostService.getInstance();
	    ArrayList<Post> list = service.listS();
	    request.setAttribute("list", list);
	    
	    String view = "list.jsp";
	    RequestDispatcher rd = request.getRequestDispatcher(view);
	    rd.forward(request, response);
	}

}
