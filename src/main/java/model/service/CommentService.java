package model.service;

import java.util.ArrayList;

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
	public ArrayList<Comment> list(int post_num){
		return dao.selectedByPostNum(post_num);
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
	
}
