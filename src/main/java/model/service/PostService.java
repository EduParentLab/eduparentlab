package model.service;

import java.util.ArrayList;
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
 
	public List<Post> listPagingS(int startRow, int pageSize){
		return dao.listWithPaging(startRow, pageSize);
	}
	public int getTotalPosts() {
	    return dao.getTotalCount();
	}
    public boolean insertS(Post dto) {
        return dao.insert(dto);  
    }
    public int insertInt(Post dto) {
        return dao.insertInt(dto);
    }
    public boolean deleteS(int post_num) {
        return dao.delete(post_num);
    }
    
    public boolean updateS(Post dto) {
        return dao.update(dto);
    }
    
    public Post get(int post_num){
        return dao.get(post_num);
    }
    
    public void hit(int post_num){
        dao.hit(post_num);
    }
    
	public LinkedHashMap<String, Integer> countPostS(){
		return dao.countPost();
	} 
}

