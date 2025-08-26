package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.service.CommentService;

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
				default: list(request,response);
			}
		}else {
			list(request,response);
		}	
		
	}
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommentService service = CommentService.getInstance();
		String strPost_num = request.getParameter("post_num");
		System.out.println(strPost_num);
		int post_num = (strPost_num != null && !strPost_num.isEmpty()) ? Integer.parseInt(strPost_num) : 1; // 기본값 1
		ArrayList<Comment> list = service.list(post_num);
		request.setAttribute("comment", list);
		
		String view = "comment.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
		
	}
	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommentService service = CommentService.getInstance();

		String strPost_num = request.getParameter("post_num");
		int post_num = Integer.parseInt(strPost_num);
		String content = request.getParameter("content");
		String email = request.getParameter("email");
		Comment comment = new Comment(-1, content, null, 0, 0, email, post_num);
		
		int result = service.insert(comment, post_num);
		if(result == 0) {
			System.out.println("댓글 등록 성공");
			response.sendRedirect("comment.do?m=list&post_num = 1");
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
		if(result == 0) {
			System.out.println("댓글 삭제 성공");
			response.sendRedirect("comment.do?m=list&post_num=1");
		}
		else {
			System.out.println("댓글 등록 실패");
		}
	}
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommentService service = CommentService.getInstance();
		String comment_content = request.getParameter("comment_content");
		String strComment_num = request.getParameter("comment_num");
		int comment_num = Integer.parseInt(strComment_num);
		int result = service.update(comment_content, comment_num);
		if(result ==0) {
			System.out.println("댓글 수정 성공");
			response.sendRedirect("comment.do?m=list&post_num=1");
		}
		else {
			System.out.println("댓글 수정 실패");
		}
	}
	private void recomment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommentService service = CommentService.getInstance();
		
		String strPost_num = request.getParameter("post_num");
		int post_num = Integer.parseInt(strPost_num);
		String content = request.getParameter("content");
		String email = request.getParameter("email");
		String strParent_num = request.getParameter("parent_num");
		if(strParent_num == null | strParent_num.trim().isEmpty()) {
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
		if(result == 0) {
			System.out.println("댓글 등록 성공");
			response.sendRedirect("comment.do?m=list&post_num = 1");
		}
		else {
			System.out.println("댓글 등록 실패");
		}
	}

}
