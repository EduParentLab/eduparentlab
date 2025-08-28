package model.dao;

import javax.naming.*;
import javax.sql.*;
import java.sql.*;
import domain.User;
import model.sql.LoginSQL;

public class LoginDAO {
	private DataSource ds;
	public LoginDAO() {
		try{
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/TestDB");
		}catch(NamingException ne){}
	}
	
	public User getUser(String email) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(LoginSQL.SEL); 
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				//String email = rs.getString(1);
				String password = rs.getString(2);
				String nickname = rs.getString(3);
				String gender = rs.getString(4);
				Date birth = rs.getDate(5);
				String name = rs.getString(6);
				String phone = rs.getString(7);
				Date Cdate = rs.getDate(8);
				int role_num = rs.getInt(9);
				
				return new User(email, password, nickname, gender, birth, name, phone, Cdate, role_num);
			}else {
				return null;
			}
		}catch(SQLException se) {
			return null;
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {}
		}
	}
}

