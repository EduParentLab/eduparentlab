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
			
			String email = request.getParameter("email");
	        String password = request.getParameter("password");
	        String passwordConfirm = request.getParameter("passwordConfirm");
	        String nickname = request.getParameter("nickname");
	        
	        String gender = request.getParameter("gender");
	        if ("남".equals(gender)) gender = "M";
	        else if ("여".equals(gender)) gender = "F";
	        
	        String strBirth = request.getParameter("birth"); //getParameter는 String만 가능해. 근데 우리는 birth를 Date로 정했으니 변환을 해줘야해. 
	        Date birth = Date.valueOf(strBirth); //이 형식으로 변환한다. 
	        String name = request.getParameter("name");
	        String phone = request.getParameter("phone");
	        int role_num = 2; //기본값은 2 (기본회원)
	        
	        //비밀번호같은지확인(백엔드)
	        if(password != null && !password.equals(passwordConfirm)) {
	            request.setAttribute("registerResult", false); // 실패
	            request.setAttribute("registerErrorMsg", "비밀번호가 일치하지 않습니다.");
	            RequestDispatcher rd = request.getRequestDispatcher("/register/result.jsp");
	            rd.forward(request, response);
	            return; // 더 진행 안 함
	        }
	        
	        User dto = new User (email, password, nickname, gender, birth, name, phone, null, role_num);
			
	        //이제 Service 호출
			RegisterService service = RegisterService.getInstance(); 
			boolean result = service.insert(dto);
			
			//JSP로 포워딩을 해야해!! 결과를 전달해야해.
			request.setAttribute("registerResult", result);
	        RequestDispatcher rd = request.getRequestDispatcher("/register/result.jsp");
	        rd.forward(request, response);	
		}
	}


