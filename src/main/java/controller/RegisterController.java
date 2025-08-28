package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.service.RegisterService;
import domain.User;
import java.sql.*;

	
	@WebServlet("/register/register.do")
	public class RegisterController extends HttpServlet {
		private static final long serialVersionUID = 1L;
		
		public void service(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
			
			String email = request.getParameter("email1") + "@" + request.getParameter("email2");
	        String password = request.getParameter("password");
	        String nickname = request.getParameter("nickname");
	        String gender = request.getParameter("gender");
	        String strBirth = request.getParameter("birth"); //getParameter는 String만 가능해. 근데 우리는 birth를 Date로 정했으니 변환을 해줘야해. 
	        Date birth = Date.valueOf(strBirth); //이 형식으로 변환한다. 
	        String name = request.getParameter("name");
	        String phone = request.getParameter("phone");
	        int role_num = 2; //기본값은 2 (기본회원)
	        
	        User dto = new User (email, password, nickname, gender, birth, name, phone, null, role_num);
			
	        //이제 Service 호출
			RegisterService service = RegisterService.getInstance(); 
			boolean result = service.insert(dto);
			
			//JSP로 포워딩을 해야해!! 결과를 전달해야해.
			request.setAttribute("result", result);
	        RequestDispatcher rd = request.getRequestDispatcher("/register/result.jsp");
	        rd.forward(request, response);	
		}
	}


