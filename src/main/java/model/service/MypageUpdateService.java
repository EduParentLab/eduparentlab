package model.service;

import domain.User;
import model.dao.MypageUpdateDAO;

public class MypageUpdateService {
	private MypageUpdateDAO dao;
	
	//SingleTon Object Model
	private static final MypageUpdateService instance = new MypageUpdateService();
	private MypageUpdateService(){
		dao = new MypageUpdateDAO();
	}
	public static MypageUpdateService getInstance() {
		return instance;
	} //여기까지는 기본이다!!
	
	//여기부터는 RegisterDAO를 보고 해야지!
	public boolean updateS(User dto) {
		return dao.update(dto);
	}
	
	public boolean deleteS(String email) {
		return dao.delete(email);
	}
	
}
