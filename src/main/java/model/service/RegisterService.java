package model.service;

import domain.User;
import model.dao.RegisterDAO;

public class RegisterService {
	private RegisterDAO dao;
	
	//SingleTon Object Model
	private static final RegisterService instance = new RegisterService();
	private RegisterService(){
		dao = new RegisterDAO();
	}
	public static RegisterService getInstance() {
		return instance;
	} //여기까지는 기본이다!!
	
	
	//여기부터는 RegisterDAO를 보고 해야지!
	public boolean insert(User dto) {
		return dao.insert(dto);
	}
	

	public boolean emailCheckS(String email) {
		return dao.emailCheck(email);
	}
	
	public boolean nicknameCheckS(String nickname) {
		return dao.nicknameCheck(nickname);
	}
	
}
