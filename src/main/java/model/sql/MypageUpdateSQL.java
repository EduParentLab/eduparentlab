package model.sql;

public class MypageUpdateSQL {
	public static String UPDATE = "update user set nickname=?, password=?, birth=?, gender=?, phone=? where email=?";
	public static String DELETE = "update user set role_num = 3 where email = ?";
}
