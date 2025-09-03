package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.service.CommentService;
import util.PagingUtil;

import java.io.IOException;
import java.util.*;
import domain.Comment;
import domain.User;


@WebServlet("/comment/comment.do")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String m = request.getParameter("m");
		if(m != null) {
			m = m.trim();
			switch(m) {
				case "insert":insert(request,response); break;
				case "delete" :delete(request,response); break;
				case "update" : update(request,response); break;
				case "recomment" : recomment(request,response); break;
				case "checkUpdateAuth":checkUpdateAuth(request,response); break;
				case "checkReplyAuth":checkReplyAuth(request,response); break;
				default: list(request,response); break;
			}
		}else {
			list(request,response);
		}	
		
	}
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommentService service = CommentService.getInstance();
		
		int page = 1; //현재 페이지
    	int pageSize = 5; //한 페이지에 보여줄 글 수
    	int pageBlock = 5; //한 번에 보여줄 페이지 번호 개수
		
    	String strPage = request.getParameter("page");
    	System.out.println("@comment> strPage: "+strPage);
    	if(strPage != null) {
    		page = Integer.parseInt(strPage);
    	}
    	String strPost_num = request.getParameter("post_num");
    	System.out.println("@post_num: " + strPost_num);
		int post_num = (strPost_num != null && !strPost_num.isEmpty()) ? Integer.parseInt(strPost_num) : 1; // 기본값 1
		
    	int totalCount = service.getTotalComments(post_num);
    	PagingUtil paging = new PagingUtil(totalCount, page, pageSize, pageBlock);
    	
		
		String strLatest = request.getParameter("latest");
		System.out.println("@댓글정렬: "+strLatest);
		boolean latestFirst = "true".equals(strLatest);
		ArrayList<Comment> list = service.selectedByPostNum(post_num, latestFirst, paging.getStartRow(), pageSize);
		System.out.println("@list()호출, post_num="+post_num);
		System.out.println("list size: "+list.size());
		
		request.setAttribute("post_num", post_num);
		request.setAttribute("comment", list);
		request.setAttribute("paging", paging);
		
	
		RequestDispatcher rd = request.getRequestDispatcher("/post/js/comment.jsp");
		rd.forward(request, response);
		
	}
	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User loginUser = checkAuth(request, response, "write", null);
	    if (loginUser == null) return;
	    
	    System.out.println("loginUser.email: "+ loginUser.getEmail());
	    
		CommentService service = CommentService.getInstance();

		String strPost_num = request.getParameter("post_num");
		if(strPost_num == null || strPost_num.isEmpty()) {
		    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "post_num is required");
		    return;
		}
		int post_num = Integer.parseInt(strPost_num);
		String content = request.getParameter("content");
		Comment comment = new Comment(-1, content, null, 0, 0, loginUser.getEmail(), post_num);
		System.out.println("@insert()호출, post_num = "+post_num+", content = "+content+", email = "+loginUser.getEmail());
		int result = service.insert(comment, post_num);
		if(result == 1) {
			System.out.println("댓글 등록 성공");
			response.sendRedirect(request.getContextPath() + "/post.do?m=content&seq=" + post_num);
		}
		else {
			System.out.println("댓글 등록 실패");
		}
	}
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommentService service = CommentService.getInstance();
		String strComment_num = request.getParameter("comment_num");
		int comment_num = Integer.parseInt(strComment_num);
		String writerEmail = service.getCommentWriterEmail(comment_num);
		
		User loginUser = checkAuth(request, response, "delete", writerEmail);
		if (loginUser == null) return;
	    System.out.println("@delete loginUser.email: "+ loginUser.getEmail());
	    
		int result = service.delete(comment_num);
		if(result == 1) {
			System.out.println("댓글 삭제 성공");
		    response.getWriter().write("success"); 
			response.setStatus(HttpServletResponse.SC_OK);
		}
		else {
			System.out.println("댓글 삭제 실패");
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "댓글 삭제 실패");
		}
	}
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommentService service = CommentService.getInstance();
		String comment_content = request.getParameter("comment_content");
		String strComment_num = request.getParameter("comment_num");
		int comment_num = Integer.parseInt(strComment_num);
		String writerEmail = service.getCommentWriterEmail(comment_num);
		
		User loginUser = checkAuth(request, response, "update", writerEmail);
	    if (loginUser == null) return;
	    System.out.println("@update loginUser.email: "+ loginUser.getEmail());
		
		int result = service.update(comment_content, comment_num);
		if(result == 1) {
			System.out.println("댓글 수정 성공");
			response.setStatus(HttpServletResponse.SC_OK);
		}
		else {
			System.out.println("댓글 수정 실패");
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "댓글 수정 실패");
		}
	}
	private void checkUpdateAuth(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
	    if(session == null) {
	        response.sendError(HttpServletResponse.SC_UNAUTHORIZED); // 401
	        return;
	    }

	    User loginUser = (User) session.getAttribute("loginOkUser");
	    String role = (String) session.getAttribute("role");
	    
	    System.out.println("@checkUpdateAuth email: " +loginUser.getEmail());
	    
	    if(loginUser == null || role == null || "guest".equals(role)) {
	        response.sendError(HttpServletResponse.SC_FORBIDDEN); // 403
	        return;
	    }

	    String strComment_num = request.getParameter("comment_num");
	    int comment_num;
	    try {
	        comment_num = Integer.parseInt(strComment_num);
	    } catch(NumberFormatException e) {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
	        return;
	    }

	    // 관리자면 무조건 허용
	    if("admin".equals(role)) {
	        response.setStatus(HttpServletResponse.SC_OK);
	        return;
	    }

	    boolean allowed = CommentService.getInstance().canUpdate(comment_num, loginUser.getEmail());
	    if(allowed) {
	        response.setStatus(HttpServletResponse.SC_OK);
	    } else {
	        response.sendError(HttpServletResponse.SC_FORBIDDEN);
	    }
	}
	private void recomment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User loginUser = checkAuth(request, response, "write", null);
	    if (loginUser == null) return;
    	
		CommentService service = CommentService.getInstance();
		
		String strPost_num = request.getParameter("post_num");
		int post_num = Integer.parseInt(strPost_num);
		String content = request.getParameter("content");
		String strParent_num = request.getParameter("parent_num");
		if(strParent_num == null || strParent_num.trim().isEmpty()) {
			System.out.println("parent_num이 null입니다.");
			return;
		}
		int parent_num = Integer.parseInt(strParent_num);

		Comment recomment = new Comment();
		recomment.setComment_content(content);
		recomment.setEmail(loginUser.getEmail());
		recomment.setPost_num(post_num);
		recomment.setGroup_num(parent_num);
		
		int result = service.recomment(recomment, post_num);
		System.out.println("recommnet의 result: "+result);
		if(result == 1) {
			// 새 답댓글 정보를 JSON으로 반환
	        response.setContentType("application/json;charset=UTF-8");
	        String json = String.format("{\"comment_num\": %d, \"email\": \"%s\", \"comment_content\": \"%s\"}", 
	                                     recomment.getComment_num(), recomment.getEmail(), recomment.getComment_content());
	        response.getWriter().write(json);
		}
		else {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "답댓글 등록 실패");
		}
	}
	private void checkReplyAuth(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
	    User loginUser = (session != null) ? (User) session.getAttribute("loginOkUser") : null;

	    if (loginUser == null) {
	        response.sendError(HttpServletResponse.SC_FORBIDDEN); // 403
	        return;
	    }
	    response.setStatus(HttpServletResponse.SC_OK);
	}
	
	private User checkAuth(HttpServletRequest request, HttpServletResponse response,
            String action, String writerEmail) throws IOException, ServletException {

		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect(request.getContextPath() + "/login/login.jsp?error=unauthorized");
			return null;
		}
		
		User loginUser = (User) session.getAttribute("loginOkUser");
		String role = (String) session.getAttribute("role");
		
		if (loginUser == null || "guest".equals(role)) {
			response.sendRedirect(request.getContextPath() + "/login/login.jsp?error=unauthorized");
			return null;
		}
		
		String email = loginUser.getEmail();
		System.out.println("@checkAuth email: " +email);
		// 관리자: 모든 권한 허용
		if ("admin".equals(role)) return loginUser;
		
		// 일반회원: write는 모두 가능, update/delete는 본인 글만 가능
		if ("user".equals(role)) {
			switch (action) {
			 case "write":
			     return loginUser;
			 case "update":
				 if (email.equals(writerEmail)) {
			         return loginUser;
			     } else {
			         response.sendRedirect(request.getContextPath() + "/post.do?m=list&error=unauthorized");
			         return null;
			     }
			 case "delete":
			     if (email.equals(writerEmail)) {
			         return loginUser;
			     } else {
			         response.sendRedirect(request.getContextPath() + "/post.do?m=list&error=unauthorized");
			         return null;
			     }
			 default:
			     return null;
			}
		}
		
		return null;
	}
}
