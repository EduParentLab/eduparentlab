package model.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import domain.Likes;
import static model.sql.LikesSQL.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class LikesDAO {
	private DataSource ds;
	public LikesDAO() {
		try{
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/TestDB");
		}catch(NamingException ne){}
	}
	public void countLikes(int post_num) {
		Connection con = null;
		PreparedStatement pstmt = null;		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(COUNTLIKES);
			pstmt.setInt(1, post_num);
			pstmt.executeQuery();			
		}catch(SQLException se){
			se.printStackTrace();			
		}finally {
			try {				
				pstmt.close();
				con.close();
			}catch(Exception e) {}
		}
	}
	public int checkLikes(String email, int post_num) {
		Connection con = null;
		PreparedStatement pstmt = null;		
		try {
			con = ds.getConnection();			
			pstmt = con.prepareStatement(CHECKEXISTENCE);
			pstmt.setString(1, email);
			pstmt.setInt(2, post_num);
			ResultSet rs = pstmt.executeQuery();			
			if(rs.next()) {
				return EXISTENCE;
			}			
		}catch(SQLException se){			
			se.printStackTrace();
			return NONEXISTENCE;
		}finally {
			try {				
				pstmt.close();
				con.close();
			}catch(Exception e) {}
		}return NONEXISTENCE;
	}	
	public void addLikes(String email, int post_num) {
		Connection con = null;
		PreparedStatement pstmt = null;		
		try {
			con = ds.getConnection();					
			pstmt = con.prepareStatement(ADDLIKES);
			pstmt.setString(1, email);
			pstmt.setInt(2, post_num);
			pstmt.executeUpdate();			
		}catch(SQLException se){
			se.printStackTrace();			
		}finally {
			try {				
				pstmt.close();
				con.close();
			}catch(Exception e) {}
		}
	}	
	public void deleteLikes(String email, int post_num) {
		Connection con = null;
		PreparedStatement pstmt = null;		
		try {
			con = ds.getConnection();					
			pstmt = con.prepareStatement(DELETELIKES);
			pstmt.setString(1, email);
			pstmt.setInt(2, post_num);
			pstmt.executeUpdate();			
		}catch(SQLException se){
			se.printStackTrace();			
		}finally {
			try {				
				pstmt.close();
				con.close();
			}catch(Exception e) {}
		}
	}	
}
