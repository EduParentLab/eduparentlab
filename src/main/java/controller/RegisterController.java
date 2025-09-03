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
	        String strBirth = request.getParameter("birth");
	        String name = request.getParameter("name");
	        String phone = request.getParameter("phone");
	        int role_num = 2; //기본회원
	        
	        // 필수 입력값 체크
	        if (email == null || email.isBlank() ||
	            password == null || password.isBlank() ||
	            passwordConfirm == null || passwordConfirm.isBlank() ||
	            nickname == null || nickname.isBlank() ||
	            gender == null || gender.isBlank() ||
	            strBirth == null || strBirth.isBlank() ||
	            name == null || name.isBlank() ||
	            phone == null || phone.isBlank()) {

	            request.setAttribute("registerResult", false);
	            request.setAttribute("registerErrorMsg", "모든 항목을 입력해야 합니다.");
	            RequestDispatcher rd = request.getRequestDispatcher("/register/result.jsp");
	            rd.forward(request, response);
	            return;
	        }
	        
	        // 비밀번호 일치 여부 확인
	        if (!password.equals(passwordConfirm)) {
	            request.setAttribute("registerResult", false);
	            request.setAttribute("registerErrorMsg", "비밀번호가 일치하지 않습니다.");
	            RequestDispatcher rd = request.getRequestDispatcher("/register/result.jsp");
	            rd.forward(request, response);
	            return;
	        }
	        
	        // gender 변환
	        if ("남".equals(gender)) gender = "M";
	        else if ("여".equals(gender)) gender = "F";
	        
	        // 생년월일 변환
	        Date birth = null;
	        try {
	            birth = Date.valueOf(strBirth);
	        } catch (IllegalArgumentException e) {
	            request.setAttribute("registerResult", false);
	            request.setAttribute("registerErrorMsg", "생년월일 형식이 잘못되었습니다. (예: 1999-01-01)");
	            RequestDispatcher rd = request.getRequestDispatcher("/register/result.jsp");
	            rd.forward(request, response);
	            return;
	        }
	        
	        // 전화번호 형식 확인 (010-XXXX-XXXX)
	        if (!phone.matches("^010-\\d{4}-\\d{4}$")) {
	            request.setAttribute("registerResult", false);
	            request.setAttribute("registerErrorMsg", "전화번호는 010-1111-2222 형식으로 입력해야 합니다.");
	            RequestDispatcher rd = request.getRequestDispatcher("/register/result.jsp");
	            rd.forward(request, response);
	            return;
	        }
	        
	        User dto = new User (email, password, nickname, gender, birth, name, phone, null, role_num);
			
	        //이제 Service 호출
			RegisterService service = RegisterService.getInstance(); 
			boolean result = service.insert(dto);
			
			//JSP로 포워딩을 해야해!! 결과를 전달해야해.
	        request.setAttribute("registerResult", result);
	        if (!result) {
	            request.setAttribute("registerErrorMsg", "회원가입 중 오류가 발생했습니다.");
	        }

	        RequestDispatcher rd = request.getRequestDispatcher("/register/result.jsp");
	        rd.forward(request, response);
		}
	}


