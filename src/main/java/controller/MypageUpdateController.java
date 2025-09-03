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

        // 파라미터 읽기
        String email = request.getParameter("email"); // readonly라서 변하지 않음
        String nickname = request.getParameter("nickname");
        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("passwordConfirm");
        String name = request.getParameter("name"); // readonly
        String strBirth = request.getParameter("birth");
        String gender = request.getParameter("gender");
        String phone = request.getParameter("phone");
        String cdate = request.getParameter("cdate"); // readonly

        // 비밀번호 일치 확인 (비워뒀으면 기존 비밀번호 유지)
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
            password = loginUser.getPassword(); // 기존 비밀번호 유지
        }

        // 닉네임 빈칸이면 기존값 유지
        if (nickname == null || nickname.isBlank()) {
            nickname = loginUser.getNickname();
        }

        // 생년월일 빈칸이면 기존값 유지
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

        // 성별 빈칸이면 기존값 유지
        if (gender == null || gender.isBlank()) {
            gender = loginUser.getGender();
        } else {
            if ("남".equals(gender)) gender = "M";
            else if ("여".equals(gender)) gender = "F";
        }

        // 전화번호 빈칸이면 기존값 유지
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

        // DTO 만들기
        User updatedUser = new User();
        updatedUser.setEmail(email);
        updatedUser.setNickname(nickname);
        updatedUser.setPassword(password);
        updatedUser.setName(name);
        updatedUser.setBirth(birth);
        updatedUser.setGender(gender);
        updatedUser.setPhone(phone);
        updatedUser.setCdate(Date.valueOf(cdate));

        // Service 호출
        MypageUpdateService service = MypageUpdateService.getInstance();
        boolean result = service.updateS(updatedUser);

        if (result) {
            // 세션 갱신
            session.setAttribute("loginOkUser", updatedUser);
        }

        // 결과 전달
        request.setAttribute("mode", "update");
        request.setAttribute("result", result);
        RequestDispatcher rd = request.getRequestDispatcher("/mypage/msg.jsp");
        rd.forward(request, response);
    }
}
