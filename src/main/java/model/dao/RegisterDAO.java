package model.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import domain.User;
import model.sql.RegisterSQL;

public class RegisterDAO {
	private DataSource ds;
	
	public RegisterDAO() {
		try{
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/TestDB");
		}catch(NamingException ne){}
	}
	
	public boolean insert(User dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = RegisterSQL.INSERT;
		
		  try {
	            con = ds.getConnection();
	            pstmt = con.prepareStatement(sql);
	            pstmt.setString(1, dto.getEmail());
	            pstmt.setString(2, dto.getPassword());
	            pstmt.setString(3, dto.getNickname());
	            pstmt.setString(4, dto.getGender());
	            pstmt.setDate(5, dto.getBirth());    
	            pstmt.setString(6, dto.getName());
	            pstmt.setString(7, dto.getPhone());
	            pstmt.setInt(8, dto.getRole_num());
	            
	    		int i = pstmt.executeUpdate();
				if(i > 0) return true;
				else return false; 
			}catch(SQLException se){
				return false;
			}finally{
				try{
					pstmt.close();
					con.close();
				}catch(SQLException se){}
		}
	
	}
}
