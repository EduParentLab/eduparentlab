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
		
		HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginOkUser");
        String email = loginUser.getEmail();       
        
        //이미 likes가 되어있는지 아닌지 검증
	    int result = service.checkLikesS(email, post_num);
	    //이미 likes 되어있다면 likes 취소
	    if(result == EXISTENCE) {			
			service.deleteLikesS(email, post_num);			
		}
	    //likes 안되어있다면 likes 추가
		else if(result == NONEXISTENCE) {			
			service.addLikesS(email, post_num);			
		}  	
	    //likes 결과 서버로 전달
	    int updatedLikes = service.countLikesS(post_num);
	    response.setContentType("application/json;charset=UTF-8");
	    response.getWriter().write("{\"likes\":" + updatedLikes + "}");	    
	}	
}
