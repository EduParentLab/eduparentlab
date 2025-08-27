package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.service.LoginService;
import domain.User;

import static model.constant.LoginConst.*;
import java.io.IOException;

@WebServlet("/login/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String m = request.getParameter("m");
		if(m != null) {
			m = m.trim();
			switch(m) {
				case "form": form(request, response); break;
				case "check": check(request, response); break;
				case "logout": logout(request, response); break;
			}
		}else {
			response.sendRedirect("../");
		}
	}
	private void form(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String view = "login.jsp";
		response.sendRedirect(view);
	}
	private void check(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.User의 아이디와 패스워드 받음
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        //서버측 유효성 검사(Client:js, Server:java)
        boolean flag = isValidEmail(email);
        System.out.println("isValidEmail: "+flag);

        LoginService service = LoginService.getInstance();
        int result = service.check(email, password);
        System.out.println("@LoginController result: " + result);

        if(result == YES_ID_PWD) {
            User u = service.getUserS(email);
            HttpSession session = request.getSession();
            session.setAttribute("loginOkUser", u);
        }
        request.setAttribute("result", result);

        String view ="msg.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(view);
        rd.forward(request,response);
    }
    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //session.removeAttribute("loginOkUser"); 
        session.invalidate();

        response.sendRedirect("../");
    }
    private boolean isValidEmail(String email) {
        // 이메일 유효성 검사 정규표현식
        String emailRegex = "^[a-zA-Z0-9_+&-]+(?:\\.[a-zA-Z0-9_+&-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
}


