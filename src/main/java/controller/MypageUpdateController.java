package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.service.MypageUpdateService;
import domain.User;
import java.sql.*;


@WebServlet("/mypage/mypage_update.do")
public class MypageUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
        String email = request.getParameter("email");  // readonly지만 값은 넘어옴
        String nickname = request.getParameter("nickname");
        String password = request.getParameter("password");
        String name = request.getParameter("name"); // readonly
        String birth = request.getParameter("birth");
        String gender = request.getParameter("gender");
        String phone = request.getParameter("phone");
        String cdate = request.getParameter("cdate"); // readonly
        
        User aa = new User();
        aa.setEmail(email);
        aa.setNickname(nickname);
        aa.setPassword(password);
        aa.setName(name);
        aa.setBirth(Date.valueOf(birth));
        aa.setGender(gender);
        aa.setPhone(phone);
        aa.setCdate(Date.valueOf(cdate));
        
        //이제 Service 호출
        MypageUpdateService service = MypageUpdateService.getInstance(); 
		boolean result = service.updateS(aa);
		
		// 세션 갱신
		if(result) {
		    HttpSession session = request.getSession();
		    session.setAttribute("loginOkUser", aa);
		
		//jsp로 포워드 
		request.setAttribute("result", result);
		RequestDispatcher rd = request.getRequestDispatcher("/mypage/msg.jsp");
		rd.forward(request, response);
		}
	}
}
