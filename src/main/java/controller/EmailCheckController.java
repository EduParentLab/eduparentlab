package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.service.RegisterService;

@WebServlet("/register/emailCheck.do")
public class EmailCheckController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        RegisterService service = RegisterService.getInstance();
        boolean exists = service.emailCheckS(email);
        response.setContentType("text/plain;charset=UTF-8");
        if (exists) {
            response.getWriter().write("DUPLICATE");
        } else {
            response.getWriter().write("AVAILABLE");
        }
    }
}
