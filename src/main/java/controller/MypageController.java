package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import domain.User;
import domain.Post;
import model.service.PostService;

@WebServlet("/mypage/mypage.do")
public class MypageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginOkUser");
        String m = request.getParameter("m");
        System.out.println("MypageController: m=" + m);
        if ("delete".equals(m)) {
            deleteMyPost(request, response);
            return;
        }
        getMyPost(request, loginUser.getEmail(), request, response);
        String view = "/mypage/mypage.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(view);
        rd.forward(request, response);
	}
	private void getMyPost(HttpServletRequest request, String email, 
            HttpServletRequest req, HttpServletResponse res) 
            		throws ServletException, IOException {
		PostService postService = PostService.getInstance();
		int pageNum = 1;
		String pageParam = req.getParameter("page");
		if (pageParam != null) {
		try {
			pageNum = Integer.parseInt(pageParam);
		} catch (NumberFormatException e) {
			pageNum = 1;
			}
		}
		int pageSize = 10;
		int totalPosts = postService.mypagePostCountS(email);
		int totalPages = (int)Math.ceil((double)totalPosts / pageSize);
		List<Post> list = postService.mypagePostListPagingS(email, pageNum, pageSize);
		int mypostLike = postService.mypageLikeCountS(email);
		int mypageCommentCount = postService.mypageCommentCountS(email);
		request.setAttribute("mypost", list);
		request.setAttribute("mypostcount", totalPosts);
		request.setAttribute("mypostlike", mypostLike);
		request.setAttribute("mycommentcount", mypageCommentCount);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("totalPages", totalPages);
		}
    private void deleteMyPost(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        String[] chk = request.getParameterValues("chk");
        if (chk != null) {
            PostService postService = PostService.getInstance();
            for (String numStr : chk) {
                int post_num = Integer.parseInt(numStr);
                postService.deleteS(post_num);
            }
        }
        response.sendRedirect(request.getContextPath() + "/mypage/mypage.do");
    }	
}
