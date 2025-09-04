package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.service.LikesService;
import static model.sql.LikesSQL.*;
import java.io.IOException;

import domain.User;

@WebServlet("/likes.do")
public class LikesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    	
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String m = request.getParameter("m");	
		System.out.println("likes컨트롤러 들어옴");
		if(m != null) {
			m = m.trim();
			switch(m) {
			case "add": add(request, response); break;
			}
		}		
	}
	private void add(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {	   
		LikesService service = LikesService.getInstance();	  
		String strPost_num = request.getParameter("post_num");
		int post_num = Integer.parseInt(strPost_num);
		System.out.println("글번호: "+strPost_num);
		
		HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginOkUser");
        String email = loginUser.getEmail();       
        
		//String email = "admin@edu_parent.com";//일단 테스트용, 나중에 세션에서 id값 받아와야함
	    int result = service.checkLikesS(email, post_num);
	    if(result == EXISTENCE) {			
			service.deleteLikesS(email, post_num);
			System.out.println("이미 좋아요 되어있어서 좋아요 취소함");
		}
		else if(result == NONEXISTENCE) {			
			service.addLikesS(email, post_num);
			System.out.println("좋아요 안되어있어서 좋아요 추가함");	
		}  	
	    response.sendRedirect("post.do?m=content&seq=" + post_num);
	}	
}
