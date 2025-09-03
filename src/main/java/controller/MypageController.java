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
        System.out.println("MypageController: m=" + m); // 확인
        
        if ("delete".equals(m)) {   // 삭제 요청
            deleteMyPost(request, response);
            return; // 삭제 후 redirect 하니까 여기서 끝
        }

        //내 글 목록 불러오기
        getMyPost(request, loginUser.getEmail(), request, response);

        String view = "/mypage/mypage.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(view);
        rd.forward(request, response);
	}
	
    // 내가 쓴 글 목록 + 총 글 수 + 총 공감 수 + 총 댓글 수 
	private void getMyPost(HttpServletRequest request, String email, 
            HttpServletRequest req, HttpServletResponse res) 
            		throws ServletException, IOException {
		
		PostService postService = PostService.getInstance();
		
		// 페이지 번호, 기본값은 1
		int pageNum = 1;
		String pageParam = req.getParameter("page");
		if (pageParam != null) {
		try {
			pageNum = Integer.parseInt(pageParam);
		} catch (NumberFormatException e) {
			pageNum = 1;
			}
		}
		
		int pageSize = 10; // 한 페이지에 10개씩
		int totalPosts = postService.mypagePostCountS(email);
		int totalPages = (int)Math.ceil((double)totalPosts / pageSize);
		
		// 페이징된 글 목록 가져오기
		List<Post> list = postService.mypagePostListPagingS(email, pageNum, pageSize);
		
		int mypostLike = postService.mypageLikeCountS(email);
		int mypageCommentCount = postService.mypageCommentCountS(email);
		
		// JSP에 전달
		request.setAttribute("mypost", list);
		request.setAttribute("mypostcount", totalPosts);
		request.setAttribute("mypostlike", mypostLike);
		request.setAttribute("mycommentcount", mypageCommentCount);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("totalPages", totalPages);
		}
		    
	//내가 쓴 글 삭제
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
        // 삭제 끝나면 다시 마이페이지로 이동 (갱신)

        response.sendRedirect(request.getContextPath() + "/mypage/mypage.do");
    }	
	
}
