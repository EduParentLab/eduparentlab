package model.dao;

import java.sql.*;
import java.util.*;
import java.sql.Date;
import javax.naming.*;
import javax.sql.DataSource;
import domain.User;
import static model.sql.AdminSQL.*;

public class UserDAO {
	private DataSource ds;
	public UserDAO() {
		try{
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/TestDB");
		}catch(NamingException ne){
		}
	}
	public ArrayList<User> list(){
		ArrayList<User> list = new ArrayList<User>();
		Connection con = null;
		Statement stmt = null;
	    ResultSet rs = null;
	    try{
			con = ds.getConnection();
			stmt = con.createStatement();
	        rs = stmt.executeQuery(USERLIST);
	        while(rs.next()){
	        	String email = rs.getString(1);
	        	String password = rs.getString(2);
	        	String nickname = rs.getString(3);
	        	String gender = rs.getString(4);
	        	java.sql.Date birth = rs.getDate(5);
	        	String name = rs.getString(6);
	        	String phone = rs.getString(7);
	        	java.sql.Date cdate = rs.getDate(8);
	        	int role_num = rs.getInt(9);
	            
	            list.add(new User(email, password, nickname, gender, birth, name, phone, cdate, role_num));
	        }
	        return list;
        }catch(SQLException se){
            return null;
        }finally{
            try{
                rs.close();
                stmt.close();
                con.close();
            }catch(SQLException se){}
        }		
	}
	public ArrayList<User> listGhostUser(){
		ArrayList<User> list = new ArrayList<User>();
		Connection con = null;
		Statement stmt = null;
	    ResultSet rs = null;
	    try{
			con = ds.getConnection();
			stmt = con.createStatement();
	        rs = stmt.executeQuery(GHOSTUSERLIST);
	        while(rs.next()){
	        	String email = rs.getString(1);
	        	String password = rs.getString(2);
	        	String nickname = rs.getString(3);
	        	String gender = rs.getString(4);
	        	java.sql.Date birth = rs.getDate(5);
	        	String name = rs.getString(6);
	        	String phone = rs.getString(7);
	        	java.sql.Date cdate = rs.getDate(8);
	        	int role_num = rs.getInt(9);
	            
	            list.add(new User(email, password, nickname, gender, birth, name, phone, cdate, role_num));
	        }
	        return list;
        }catch(SQLException se){
            return null;
        }finally{
            try{
                rs.close();
                stmt.close();
                con.close();
            }catch(SQLException se){}
        }		
	}
	public LinkedHashMap<Date, Integer> countUser() {
		LinkedHashMap<Date, Integer> map = new LinkedHashMap<>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(COUNTUSER);
			while(rs.next()) {
				Date cdate = rs.getDate("cdate");
				int count = rs.getInt("user_count");
				map.put(cdate, count);
			}
		}catch(SQLException se){
			
		}finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			}catch(SQLException se) {}
		}return map;
	}
	public User getUserByEmail(String email) {
		User user = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(USERBYEMAIL);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if(rs.next()) {				
	        	String password = rs.getString(2);
	        	String nickname = rs.getString(3);
	        	String gender = rs.getString(4);
	        	java.sql.Date birth = rs.getDate(5);
	        	String name = rs.getString(6);
	        	String phone = rs.getString(7);
	        	java.sql.Date cdate = rs.getDate(8);
	        	int role_num = rs.getInt(9);	
				return new User(email, password, nickname, gender, birth, name, phone, cdate, role_num);
			}
		}catch(SQLException se) {
			se.printStackTrace();			
		}finally {
			try {
				pstmt.close();
				con.close();
			}catch(Exception e) {}
		}return user;
	}
}
