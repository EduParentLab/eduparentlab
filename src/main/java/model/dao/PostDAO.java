package model.dao;

import javax.naming.*;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;
import domain.Post;
import static model.sql.PostSQL.*;

public class PostDAO {
    private DataSource ds;

    public PostDAO() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context)initContext.lookup("java:/comp/env");
            ds = (DataSource)envContext.lookup("jdbc/TestDB");
        } catch (NamingException ne) {
            ne.printStackTrace();
        }
    }

   
    public ArrayList<Post> list() {
        ArrayList<Post> list = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            con = ds.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(POST);

            while (rs.next()) {
                int post_num = rs.getInt(1);
                String post_subject = rs.getString(2);
                String post_content = rs.getString(3);
                java.sql.Date post_date = rs.getDate(4);
                int post_view = rs.getInt(5);
                int category_num = rs.getInt(6);
                String email = rs.getString(7);

                list.add(new Post(post_num, post_subject, post_content,
                                  post_date, post_view, category_num, email));
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


    public boolean insert(Post dto) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = ds.getConnection();

        
            int newPostNum = 1;
            pstmt = con.prepareStatement("SELECT IFNULL(MAX(post_num),0)+1 FROM post");
            rs = pstmt.executeQuery();
            if (rs.next()) newPostNum = rs.getInt(1);
            rs.close();
            pstmt.close();

       
            pstmt = con.prepareStatement(INSERT);
            pstmt = con.prepareStatement(INSERT);
            pstmt.setInt(1, newPostNum);                    
            pstmt.setString(2, dto.getPost_subject());      
            pstmt.setString(3, dto.getPost_content());     
            pstmt.setInt(4, dto.getCategory_num());         
            pstmt.setString(5, dto.getEmail());             

            int i = pstmt.executeUpdate();
            if (i > 0) return true;
            else return false;

        } catch (SQLException se) {
            se.printStackTrace();
            return false;

        } finally {
            try { 
            	
            rs.close(); 
            pstmt.close(); 
            con.close(); 
            }catch (Exception e) {}
        }
    }
}
