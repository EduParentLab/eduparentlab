package model.service;


import java.util.*;
import model.dao.PostDAO;
import domain.Post;

public class PostService {
	private PostDAO dao;
	private static final PostService instance = new PostService();
	private PostService() {
		dao = new PostDAO();
	}
	public static PostService getInstance() {
		return instance;
	}
	public ArrayList<Post> listS(){
		return dao.list();
	}
	public LinkedHashMap<String, Integer> countPostS(){
		return dao.countPost();
	} 
}
