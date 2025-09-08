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
    public List<Post> listPagingS(int startRow, int pageSize, String sort, int categoryNum) {
        return dao.listWithPaging(startRow, pageSize, sort, categoryNum);
    }
    public List<Post> searchWithPagingS(int startRow, int pageSize, String sort, String type, String keyword, int categoryNum) {
        return dao.searchWithPaging(startRow, pageSize, sort, type, keyword, categoryNum);
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
	public List<Post> mypagePostListPagingS(String email, int pageNum, int pageSize) {
	    return dao.mypagePostListPaging(email, pageNum, pageSize);
	}
	public int mypagePostCountS(String email) {
	    return dao.mypagePostCount(email);
	}
	public int mypageLikeCountS(String email) {
	    return dao.mypageLikeCount(email);
	}
	public int mypageCommentCountS(String email) {
	    return dao.mypageCommentCount(email);
	}
    public ArrayList<Post> listNoticeS() {
        return dao.listNotice();
    }
    public int getTotalPostsByCategory(int categoryNum) {
        return dao.getTotalCountByCategory(categoryNum);
    }
}
