package model.service;

import java.util.*;
import java.sql.Date;
import domain.User;
import model.dao.UserDAO;

public class UserService {
	private UserDAO dao;
	private static final UserService instance = new UserService();
	private UserService() {
		dao = new UserDAO();
	}
	public static UserService getInstance() {
		return instance;
	}
	public ArrayList<User> listS(){
		return dao.list();
	}
	public ArrayList<User> listGhostUserS(){
		return dao.listGhostUser();
	}
	public LinkedHashMap<Date, Integer> countUserS(){
		return dao.countUser();
	}
	public User getUserByEmailS(String email) {
		return dao.getUserByEmail(email);
	}
}
