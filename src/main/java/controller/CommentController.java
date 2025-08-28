package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.service.CommentService;
import util.PagingUtil;

import java.io.IOException;
import java.util.*;
import domain.Comment;


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
		int post_num = (strPost_num != null && !strPost_num.isEmpty()) ? Integer.parseInt(strPost_num) : 1; // 기본값 1
		
    	int totalCount = service.getTotalComments(post_num);
    	PagingUtil paging = new PagingUtil(totalCount, page, pageSize, pageBlock);
    	
		
		String strLatest = request.getParameter("latest");
		System.out.println("@댓글정렬: "+strLatest);
		boolean latestFirst = "true".equals(strLatest);
		ArrayList<Comment> list = service.selectedByPostNum(post_num, latestFirst, paging.getStartRow(), pageSize);
		System.out.println("@list()호출, post_num="+post_num);
		System.out.println("list size: "+list.size());
		
		request.setAttribute("comment", list);
		request.setAttribute("paging", paging);
		
	
		RequestDispatcher rd = request.getRequestDispatcher("/comment/comment.jsp");
		rd.forward(request, response);
		
	}
	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommentService service = CommentService.getInstance();

		String strPost_num = request.getParameter("post_num");
		int post_num = Integer.parseInt(strPost_num);
		String content = request.getParameter("content");
		String email = request.getParameter("email");
		Comment comment = new Comment(-1, content, null, 0, 0, email, post_num);
		System.out.println("@insert()호출, post_num = "+post_num+", content = "+content+", email = "+email);
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
		
		int result = service.delete(comment_num);
		if(result == 1) {
			System.out.println("댓글 삭제 성공");
			response.setStatus(HttpServletResponse.SC_OK);
		}
		else {
			System.out.println("댓글 등록 실패");
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "댓글 등록 실패");
		}
	}
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommentService service = CommentService.getInstance();
		String comment_content = request.getParameter("comment_content");
		String strComment_num = request.getParameter("comment_num");
		int comment_num = Integer.parseInt(strComment_num);
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
	private void recomment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommentService service = CommentService.getInstance();
		
		String strPost_num = request.getParameter("post_num");
		int post_num = Integer.parseInt(strPost_num);
		String content = request.getParameter("content");
		String email = request.getParameter("email");
		String strParent_num = request.getParameter("parent_num");
		if(strParent_num == null || strParent_num.trim().isEmpty()) {
			System.out.println("parent_num이 null입니다.");
			return;
		}
		int parent_num = Integer.parseInt(strParent_num);

		Comment recomment = new Comment();
		recomment.setComment_content(content);
		recomment.setEmail(email);
		recomment.setPost_num(post_num);
		recomment.setGroup_num(parent_num);
		
		int result = service.recomment(recomment, post_num);
		System.out.println("recommnet의 result: "+result);
		if(result == 1) {
			response.sendRedirect(request.getContextPath() + "/post.do?m=content&seq=" + post_num);
			System.out.println("댓글 등록 성공");
		}
		else {
			System.out.println("댓글 등록 실패");
		}
	}

}
