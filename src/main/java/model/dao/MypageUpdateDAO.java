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
		String sql = MypageUpdateSQL.UPDATE;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getNickname());
			pstmt.setString(2, dto.getPassword());
			pstmt.setDate(3, dto.getBirth());
			pstmt.setString(4, dto.getGender());
			pstmt.setString(5, dto.getPhone());
			pstmt.setString(6, dto.getEmail());
			int i = pstmt.executeUpdate();
			if(i > 0) return true;
			else return false;
		}catch(SQLException se){
			return false;
		}finally{
			try{
				pstmt.close();
				con.close();
			}catch(SQLException se) {}
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
