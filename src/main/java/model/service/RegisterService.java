package model.service;

import domain.User;
import model.dao.RegisterDAO;

public class RegisterService {
	private RegisterDAO dao;
	private static final RegisterService instance = new RegisterService();
	private RegisterService(){
		dao = new RegisterDAO();
	}
	public static RegisterService getInstance() {
		return instance;
	}
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
