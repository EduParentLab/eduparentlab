package model.service;

import domain.User;
import model.dao.MypageUpdateDAO;

public class MypageUpdateService {
	private MypageUpdateDAO dao;
	private static final MypageUpdateService instance = new MypageUpdateService();
	private MypageUpdateService(){
		dao = new MypageUpdateDAO();
	}
	public static MypageUpdateService getInstance() {
		return instance;
	}
	public boolean updateS(User dto) {
		return dao.update(dto);
	}
	public boolean deleteS(String email) {
		return dao.delete(email);
	}
}
