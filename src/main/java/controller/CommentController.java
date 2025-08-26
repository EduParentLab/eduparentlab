package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.service.CommentService;

import java.io.IOException;

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
				case "form":form(request,response); break;
				default: list(request,response);
			}
		}else {
			list(request,response);
		}	
		
	}
	private void form(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("comment.jsp");
	}
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommentService service = CommentService.getInstance();
		String strPost_num = request.getParameter("post_num");
		int post_num = Integer.parseInt(strPost_num);
		String content = request.getParameter("comment_content");
		String strGroup_num = request.getParameter("group_num");
		int group_num = Integer.parseInt(strGroup_num);
		String strGroup_order = request.getParameter("group_order");
		int group_order = Integer.parseInt(strGroup_order);
		String email = request.getParameter("email");
		Comment comment = new Comment(-1, content, null, group_num, group_order, email, post_num);
		
		int result = service.insert(comment, post_num);
		if(result == 0) System.out.println("댓글 등록 성공");
		else System.out.println("댓글 등록 실패");
		response.sendRedirect("comment");
	}
	
}
