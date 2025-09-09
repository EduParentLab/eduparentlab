package controller;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.service.RegisterService;

@WebServlet("/register/nicknameCheck.do")
public class NicknameCheckController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nickname = request.getParameter("nickname");
        RegisterService service = RegisterService.getInstance();
        boolean exists = service.nicknameCheckS(nickname);
        response.setContentType("text/plain; charset=UTF-8");
        response.getWriter().write(exists ? "DUPLICATE" : "AVAILABLE");
    }
}

