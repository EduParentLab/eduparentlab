package model.service;

import domain.User;
import static model.constant.LoginConst.*;
import model.dao.LoginDAO;


public class LoginService {
	private LoginDAO dao;
	
	private static final LoginService instance = new LoginService();
	private LoginService() {
		dao = new LoginDAO();
	}
	public static LoginService getInstance() {
		return instance;
	}
	
	public User getUserS(String email) {
		User u = dao.getUser(email);
		u.setPassword("");
		
		return u;
	}
	public int check(String email, String password) {
		User u = dao.getUser(email);
		if(u == null) {
			return NO_ID; //그런 email을 가진 회원이 없음
		}else {
			String dbPwd = u.getPassword();
			if(dbPwd != null) dbPwd = dbPwd.trim();
			
			if(!dbPwd.equals(password)) {
				return NO_PWD; //email은 존재하지만 pwd는 틀림 
			}else {
				return YES_ID_PWD; //email과 pwd가 일치 
			}
		}
	}
}
