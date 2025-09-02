package model.dao;

import static model.sql.PostSQL.POST;

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

import domain.Post;

public class MainDAO {
	private DataSource ds;

    public MainDAO() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context)initContext.lookup("java:/comp/env");
            ds = (DataSource)envContext.lookup("jdbc/TestDB");
        } catch (NamingException ne) {
            ne.printStackTrace();
        }
    }
    
    public ArrayList<Post> list(String sort) {
        ArrayList<Post> list = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        StringBuilder sql = new StringBuilder(POST);
        
        if("views".equals(sort)) {
        	sql.append(" ORDER BY p.post_view DESC LIMIT 0, 5");
        }else if("latest".equals(sort)){
        	sql.append(" ORDER BY p.post_date DESC LIMIT 0, 5");
        }
        try {
            con = ds.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql.toString());

            while (rs.next()) {
                int post_num = rs.getInt(1);
                String post_subject = rs.getString(2);
                String post_content = rs.getString(3);
                java.sql.Date post_date = rs.getDate(4);
                int post_view = rs.getInt(5);
                int category_num = rs.getInt(6);
                String email = rs.getString(7);
                String nickname = rs.getString(8);
                int likes = rs.getInt(9);

                list.add(new Post(post_num, post_subject, post_content,
                                  post_date, post_view, category_num, email, nickname, likes));
            }
            
            return list;

        } catch (SQLException se) {
            se.printStackTrace();
            return null;

        } finally {
            try { 
            	rs.close(); 
            	stmt.close(); 
            	con.close(); 
            } catch (Exception e) {}
        }
    }
    public ArrayList<Post> searchPost(String keyword, int category_num) {
    	ArrayList<Post> list = new ArrayList<>();
        
        String sql = "SELECT p.post_num, p.post_subject, p.post_content, p.post_date, " +
                "p.post_view, p.category_num, p.email, u.nickname, p.likes " +
                "FROM Post p LEFT JOIN User u ON p.email = u.email " +
                "WHERE (p.post_subject LIKE ? OR p.post_content LIKE ?) AND p.category_num = ? " +
                "ORDER BY p.post_date DESC";
        
        try (Connection con = ds.getConnection();
        		PreparedStatement pstmt = con.prepareStatement(sql)) {

               pstmt.setString(1, "%" + keyword + "%");
               pstmt.setString(2, "%" + keyword + "%");
               pstmt.setInt(3, category_num);
               
               ResultSet rs = pstmt.executeQuery();

               while (rs.next()) {
                   Post dto = new Post();
                   dto.setPost_num(rs.getInt("post_num"));
                   dto.setPost_subject(rs.getString("post_subject"));
                   dto.setPost_content(rs.getString("post_content"));
                   dto.setPost_date(rs.getDate("post_date"));
                   dto.setPost_view(rs.getInt("post_view"));
                   dto.setCategory_num(rs.getInt("category_num"));
                   dto.setEmail(rs.getString("email"));
                   dto.setNickname(rs.getString("nickname"));
                   dto.setLikes(rs.getInt("likes"));
                   list.add(dto);
               }
               return list;
        }catch(SQLException se) {
        	se.printStackTrace();
        	return null;
        }
        
    }

}
