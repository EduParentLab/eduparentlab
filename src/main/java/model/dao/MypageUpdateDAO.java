package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import domain.User;
import model.sql.MypageUpdateSQL;

public class MypageUpdateDAO {
	private DataSource ds;
	
	public MypageUpdateDAO() {
		try{
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/TestDB");
		}catch(NamingException ne){}
	}
	
	public boolean update(User dto) {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    
	    try {
	        con = ds.getConnection();
	        
	        // 비밀번호를 바꾸면 UPDATE_WITH_PW, 안 바꾸고 그대로 두면 UPDATE_NO_PW 사용
	        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
	            pstmt = con.prepareStatement(MypageUpdateSQL.UPDATE_WITH_PW);
	            pstmt.setString(1, dto.getNickname());
	            pstmt.setString(2, dto.getPassword());
	            pstmt.setDate(3, dto.getBirth());
	            pstmt.setString(4, dto.getGender());
	            pstmt.setString(5, dto.getPhone());
	            pstmt.setString(6, dto.getEmail());
	        } else {
	            pstmt = con.prepareStatement(MypageUpdateSQL.UPDATE_NO_PW);
	            pstmt.setString(1, dto.getNickname());
	            pstmt.setDate(2, dto.getBirth());
	            pstmt.setString(3, dto.getGender());
	            pstmt.setString(4, dto.getPhone());
	            pstmt.setString(5, dto.getEmail());
	        }

	        int i = pstmt.executeUpdate();
	        return i > 0;
	    } catch (SQLException se) {
	        se.printStackTrace();
	        return false;
	    } finally {
	        try {
	            if (pstmt != null) pstmt.close();
	            if (con != null) con.close();
	        } catch (SQLException se) {}
	    }
	}

	
	public boolean delete(String email) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = MypageUpdateSQL.DELETE;
		
		try{	
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			int i = pstmt.executeUpdate();
			if(i > 0) return true;
			else return false; 
		}catch(SQLException se){
	        se.printStackTrace();
	        return false;
		}
	}
}
