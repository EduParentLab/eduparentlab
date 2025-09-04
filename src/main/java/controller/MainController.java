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
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String m = request.getParameter("m");
		if(m != null) {
			m = m.trim();
			switch(m) {
				case "search":search(request,response); break;
				default: main(request,response); break;
			}
		}else {
			main(request,response); 
		}
	
    }
	private void main(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MainService mainService = MainService.getInstance();
		
        ArrayList<Post> popularList = mainService.listS("views");
        ArrayList<Post> latestList = mainService.listS("latest");
        ArrayList<Post> noticeList = mainService.listNotice();
        
	    request.setAttribute("notice", noticeList);	  	
        request.setAttribute("popularList", popularList);
        request.setAttribute("latestList", latestList);
        

        RequestDispatcher rd = request.getRequestDispatcher("/main/main_page.jsp");
        rd.forward(request, response);
	}
	private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String keyword = request.getParameter("keyword");
		request.setAttribute("keyword", keyword);
        
        Map<Integer, ArrayList<Post>> searchMap = new HashMap<>();
        MainService mainService = MainService.getInstance();
        
        Map<Integer, String> categories = new LinkedHashMap<>();
        categories.put(1, "자유게시판");
        categories.put(2, "입시정보");
        categories.put(3, "고등게시판");
        request.setAttribute("categories", categories);
        
        if(keyword != null && !keyword.isBlank()) {
        	int[] categoryNums = {1,2,3};
        	
        	for(int cateNum : categoryNums) {
        		ArrayList<Post> searchList = mainService.searchWithKeyword(keyword, cateNum);
        		searchMap.put(cateNum, searchList);
        	}
        }
        request.setAttribute("searchMap", searchMap);
        
    	RequestDispatcher rd = request.getRequestDispatcher("/main/all_search.jsp");
        rd.forward(request, response);
	}

}
