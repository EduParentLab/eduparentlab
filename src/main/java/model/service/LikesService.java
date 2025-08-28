package model.service;

import model.dao.LikesDAO;
import java.util.*;

public class LikesService {
	private LikesDAO dao;
	private static final LikesService instance = new LikesService();
	
	private LikesService() {
		dao = new LikesDAO();
	}
	public static LikesService getInstance() {
		return instance;
	}
	public int checkLikesS(String email, int post_num) {
		return dao.checkLikes(email, post_num);
	}
	public void addLikesS(String email, int post_num) {
		dao.addLikes(email, post_num);
	}
	public void deleteLikesS(String email, int post_num) {
		dao.deleteLikes(email, post_num);
	}
	public HashMap<Integer, Integer> countLikesS(){
		return dao.countLikes();
	}
}
