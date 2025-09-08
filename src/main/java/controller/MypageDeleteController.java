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

@WebServlet("/mypage/delete.do")
public class MypageDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		 HttpSession session = request.getSession();
		 User loginUser = (User) session.getAttribute("loginOkUser");
		 String bb = loginUser.getEmail();
		 MypageUpdateService service = MypageUpdateService.getInstance();
		 boolean deleteResult = service.deleteS(bb);
		 if(deleteResult) {
			 session.invalidate();
			 request.setAttribute("mode", "delete");
			 request.setAttribute("result", deleteResult);
		     RequestDispatcher rd = request.getRequestDispatcher("/mypage/msg.jsp");
		     rd.forward(request, response);
		 }
	}
}
