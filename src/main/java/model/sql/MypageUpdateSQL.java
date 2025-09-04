package model.sql;

public class MypageUpdateSQL {
	public static String UPDATE_WITH_PW =
	        "UPDATE user SET nickname=?, password=?, birth=?, gender=?, phone=? WHERE email=?";
	
	public static String UPDATE_NO_PW =
	        "UPDATE user SET nickname=?, birth=?, gender=?, phone=? WHERE email=?";
	
	public static String DELETE = "update user set role_num = 3 where email = ?";
}
