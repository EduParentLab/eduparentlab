package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import domain.Comment;
import static model.sql.CommentSQL.*;
public class CommentDAO {
	private DataSource ds;
	public CommentDAO() {
		try{
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:comp/env");
			ds = (DataSource)envContext.lookup("jdbc/TestDB");
			System.out.println("db연결됨");
		}catch(NamingException ne){
			System.out.println("db연결되지 않음");
			ne.printStackTrace();
		}
		
	}

	public int insert(Comment dto,int post_num) {
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(INSERT);){
			pstmt.setInt(1, dto.getComment_num());
			pstmt.setString(2, dto.getComment_contet());
			pstmt.setInt(3, dto.getGroup_num());
			pstmt.setInt(4, dto.getGroup_order());
			pstmt.setInt(5, post_num);
			int i = pstmt.executeUpdate();
			if(i>0) return 0;
			else return 1;
		}catch(SQLException se) {
			se.printStackTrace();
			return 1;
		}
	}

	public ArrayList<Comment> selectedById(int post_num) {
		ArrayList<Comment> clist = new ArrayList<Comment>();

		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(LIST);){
			pstmt.setInt(1, post_num);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String email = rs.getString("email");
				String content = rs.getString("comment_content");
				java.sql.Date date = rs.getDate("comment_date");
				clist.add(new Comment(-1, content, date, -1, -1, email, post_num));
			}
			return clist;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		}
	}
	
	public int delete(int comment_num) {
		
		return 0;
	}
	
}
