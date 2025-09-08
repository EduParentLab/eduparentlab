package controller;

import java.io.IOException;
import java.sql.Date;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.service.RegisterService;
import domain.User;
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
	        String name = request.getParameter("name");      
	        String birth1 = request.getParameter("birth");
	        String birth2 = request.getParameter("birth2");
	        String birth3 = request.getParameter("birth3");
	        String strBirth = (birth1 != null && birth2 != null && birth3 != null)
	                ? birth1 + "-" + birth2 + "-" + birth3
	                : null;
	        String phone1 = request.getParameter("phone1");
	        String phone2 = request.getParameter("phone2");
	        String phone3 = request.getParameter("phone3");
	        String phone = (phone1 != null && phone2 != null && phone3 != null)
	                ? phone1 + "-" + phone2 + "-" + phone3
	                : null;
	        int role_num = 2;
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
	        if (!password.equals(passwordConfirm)) {
	            request.setAttribute("registerResult", false);
	            request.setAttribute("registerErrorMsg", "비밀번호가 일치하지 않습니다.");
	            RequestDispatcher rd = request.getRequestDispatcher("/register/result.jsp");
	            rd.forward(request, response);
	            return;
	        }
	        if ("male".equals(gender)) gender = "M";
	        else if ("female".equals(gender)) gender = "F";
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
	        if (!phone.matches("^010-\\d{4}-\\d{4}$")) {
	            request.setAttribute("registerResult", false);
	            request.setAttribute("registerErrorMsg", "전화번호는 010-1111-2222 형식으로 입력해야 합니다.");
	            RequestDispatcher rd = request.getRequestDispatcher("/register/result.jsp");
	            rd.forward(request, response);
	            return;
	        }
	        User dto = new User (email, password, nickname, gender, birth, name, phone, null, role_num);
			RegisterService service = RegisterService.getInstance(); 
			boolean result = service.insert(dto);
	        request.setAttribute("registerResult", result);
	        if (!result) {
	            request.setAttribute("registerErrorMsg", "회원가입 중 오류가 발생했습니다.");
	        }
	        RequestDispatcher rd = request.getRequestDispatcher("/register/result.jsp");
	        rd.forward(request, response);
		}
	}