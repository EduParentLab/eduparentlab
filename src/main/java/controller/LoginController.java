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
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean flag = isValidEmail(email);
        System.out.println("isValidEmail: "+flag);
        LoginService service = LoginService.getInstance();
        int result = service.check(email, password);
        System.out.println("@LoginController result: " + result);
        if(result == YES_ID_PWD) {
            User u = service.getUserS(email);
            HttpSession session = request.getSession();
            session.setAttribute("loginOkUser", u);
            String role = "guest";
            switch(u.getRole_num()) {
            	case 1: role= "admin"; break;
            	case 2: role = "user"; break;
            	case 3: role ="guest"; break;
            }
            session.setAttribute("role", role);
            response.setContentType("text/plain; charset=UTF-8");
            response.getWriter().write("success");
            System.out.println("loginUser: " + u);
            System.out.println("role: " + role);
            System.out.println("sessionId: "+ session.getId());
        }
        else {
        	response.setContentType("text/plain; charset=UTF-8");
            response.getWriter().write("fail");
        }
        request.setAttribute("result", result);
        String view ="msg.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(view);
        rd.forward(request,response);
    }
    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("../main/main.do");
    }
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&-]+(?:\\.[a-zA-Z0-9_+&-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
}


