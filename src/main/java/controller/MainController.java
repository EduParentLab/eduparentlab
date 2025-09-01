package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.service.PostService;

import java.io.IOException;
import java.util.*;

import domain.Post;


@WebServlet("/main/main.do")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PostService service = PostService.getInstance();
        ArrayList<Post> popularList = service.listS("views");
        ArrayList<Post> latestList = service.listS("latest");
        ArrayList<Post> list = service.listNoticeS();	   
	    request.setAttribute("notice", list);	  	
        request.setAttribute("popularList", popularList);
        request.setAttribute("latestList", latestList);
        
        RequestDispatcher rd = request.getRequestDispatcher("/main/main_page.jsp");
        rd.forward(request, response);
    }

}
