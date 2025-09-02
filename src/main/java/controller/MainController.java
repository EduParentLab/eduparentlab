package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.service.MainService;
import model.service.PostService;

import java.io.IOException;
import java.util.*;

import domain.Post;


@WebServlet("/main/main.do")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MainService mainService = MainService.getInstance();
		PostService postService = PostService.getInstance();
		
        ArrayList<Post> popularList = mainService.listS("views");
        ArrayList<Post> latestList = mainService.listS("latest");
        ArrayList<Post> noticeList = postService.listNoticeS();
        
        String keyword = request.getParameter("keyword");
        ArrayList<Post> searchList = new ArrayList<>();
        
        if(keyword != null && !keyword.isBlank()) {
        	searchList = mainService.searchWithKeyword(keyword);
        	request.setAttribute("searchList", searchList);
        	RequestDispatcher rd = request.getRequestDispatcher("/main/all_search.jsp");
            rd.forward(request, response);
        }
   
	    request.setAttribute("notice", noticeList);	  	
        request.setAttribute("popularList", popularList);
        request.setAttribute("latestList", latestList);
        
        
        RequestDispatcher rd = request.getRequestDispatcher("/main/main_page.jsp");
        rd.forward(request, response);
    }

}
