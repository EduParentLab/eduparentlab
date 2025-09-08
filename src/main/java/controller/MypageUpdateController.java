package controller;
import java.io.IOException;
import java.sql.Date;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.service.MypageUpdateService;
import domain.User;

@WebServlet("/mypage/mypage_update.do")
public class MypageUpdateController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginOkUser");
        String email = request.getParameter("email");
        String nickname = request.getParameter("nickname");
        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("passwordConfirm");
        String name = request.getParameter("name");
        String birthYear = request.getParameter("birth");
        String birthMonth = request.getParameter("birth2");
        String birthDay = request.getParameter("birth3");
        String strBirth = null;
        if (birthYear != null && !birthYear.isBlank()
                && birthMonth != null && !birthMonth.isBlank()
                && birthDay != null && !birthDay.isBlank()) {
            strBirth = String.format("%s-%02d-%02d",
                    birthYear,
                    Integer.parseInt(birthMonth),
                    Integer.parseInt(birthDay));
        }
        String phone1 = request.getParameter("phone1");
        String phone2 = request.getParameter("phone2");
        String phone3 = request.getParameter("phone3");
        String phone = null;
        if (phone1 != null && phone2 != null && phone3 != null &&
                !phone1.isBlank() && !phone2.isBlank() && !phone3.isBlank()) {
            phone = phone1 + "-" + phone2 + "-" + phone3;
        }
        if (password != null && !password.isBlank()) {
            if (!password.equals(passwordConfirm)) {
                request.setAttribute("mode", "update");
                request.setAttribute("result", false);
                request.setAttribute("errorMsg", "비밀번호가 일치하지 않습니다.");
                RequestDispatcher rd = request.getRequestDispatcher("/mypage/msg.jsp");
                rd.forward(request, response);
                return;
            }
        } else {
            password = loginUser.getPassword();
        }
        if (nickname == null || nickname.isBlank()) {
            nickname = loginUser.getNickname();
        }
        Date birth = null;
        if (strBirth == null || strBirth.isBlank()) {
            birth = loginUser.getBirth();
        } else {
            try {
                birth = Date.valueOf(strBirth);
            } catch (IllegalArgumentException e) {
                request.setAttribute("mode", "update");
                request.setAttribute("result", false);
                request.setAttribute("errorMsg", "생년월일 형식이 잘못되었습니다. (예: 1999-01-01)");
                RequestDispatcher rd = request.getRequestDispatcher("/mypage/msg.jsp");
                rd.forward(request, response);
                return;
            }
        }
        if (phone == null || phone.isBlank()) {
            phone = loginUser.getPhone();
        } else {
            if (!phone.matches("^010-\\d{4}-\\d{4}$")) {
                request.setAttribute("mode", "update");
                request.setAttribute("result", false);
                request.setAttribute("errorMsg", "전화번호는 010-1111-2222 형식으로 입력해야 합니다.");
                RequestDispatcher rd = request.getRequestDispatcher("/mypage/msg.jsp");
                rd.forward(request, response);
                return;
            }
        }
        User updatedUser = new User();
        updatedUser.setEmail(email);
        updatedUser.setNickname(nickname);
        updatedUser.setPassword(password);
        updatedUser.setName(name);
        updatedUser.setBirth(birth);
        updatedUser.setPhone(phone);
        updatedUser.setGender(loginUser.getGender());
        updatedUser.setCdate(loginUser.getCdate());
        MypageUpdateService service = MypageUpdateService.getInstance();
        boolean result = service.updateS(updatedUser);
        if (result) {
            session.setAttribute("loginOkUser", updatedUser);
        }
        request.setAttribute("mode", "update");
        request.setAttribute("result", result);
        RequestDispatcher rd = request.getRequestDispatcher("/mypage/msg.jsp");
        rd.forward(request, response);
    }
}
