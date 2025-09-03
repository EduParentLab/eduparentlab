package model.service;

import java.util.ArrayList;
import java.util.List;

import domain.Comment;
import model.dao.CommentDAO;

public class CommentService {
	private CommentDAO dao;
	
	private final static CommentService INSTANCE = new CommentService();
	private CommentService() {
		dao = new CommentDAO();
	}
	public static CommentService getInstance() {
		return INSTANCE;
	}
	public ArrayList<Comment> selectedByPostNum(int post_num, boolean latestFirst, int startRow, int pageSize){
		
		ArrayList<Comment> list = dao.selectedByPostNum(post_num, latestFirst, startRow, pageSize);

	    // 각 댓글에 답댓글 리스트 추가
	    for (Comment comment : list) {
	        List<Comment> replies = dao.getRecomments(comment.getComment_num());
	        // 확인용 출력
	        System.out.println("부모 댓글 번호: " + comment.getComment_num());
	        for (Comment r : replies) {
	            System.out.println("   답댓글 번호: " + r.getComment_num() + ", 내용: " + r.getComment_content());
	        }
	        comment.setRecomments(replies);
	    }

	    return list;
	}
	public int getTotalComments(int post_num) {
		return dao.getTotalCount(post_num);
	}
	public int insert(Comment dto, int post_num) {
		return dao.insert(dto, post_num);
	}
	public int delete(int comment_num) {
		return dao.delete(comment_num);
	}
	public int update(String comment_content, int comment_num) {
		return dao.update(comment_content, comment_num);
	}
	public int recomment(Comment dto,int post_num) {
		Comment parent = dao.selectedByCommentNum(dto.getGroup_num());
		if(parent == null) return 1;
		
		int group_order = parent.getGroup_order()+1;
		dto.setGroup_order(group_order);
		
		return dao.recomment(dto, post_num);
	}
	public boolean canUpdate(int comment_num, String userEmail) {
		Comment comment = dao.selectedByCommentNum(comment_num);
		if(comment == null) return false;
		
		return userEmail != null && userEmail.equals(comment.getEmail());
	}
	public String getCommentWriterEmail(int comment_num) {
	    Comment c = dao.selectedByCommentNum(comment_num);
	    return (c != null) ? c.getEmail() : null;
	}
	
	
}
