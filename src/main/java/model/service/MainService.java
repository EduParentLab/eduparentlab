package model.service;

import java.util.ArrayList;

import domain.Post;
import model.dao.MainDAO;

public class MainService {
	private MainDAO dao;
    private static final MainService instance = new MainService();
   
    private MainService() {
        dao = new MainDAO();
    }

    public static MainService getInstance() {
        return instance;
    }
	public ArrayList<Post> listS(String sort) {
        return dao.list(sort);
    }
	public ArrayList<Post> searchWithKeyword(String keyword){
		 if (keyword == null || keyword.isBlank()) {
		        return new ArrayList<>(); // 빈 리스트 반환
		    }
		return dao.searchPost(keyword);
	}
}
