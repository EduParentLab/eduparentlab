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
		return dao.selectedById(post_num);
	}
	public int insert(Comment dto, int post_num) {
		return dao.insert(dto, post_num);
	}
	public int delete(int post_num) {
		return dao.delete(post_num);
	}
}
