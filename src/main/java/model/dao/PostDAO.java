package model.dao;

import javax.naming.*;
import javax.sql.DataSource;

import java.sql.*;
import java.util.*;
import domain.Post;
import static model.sql.PostSQL.*;
import static model.sql.AdminSQL.*;
import model.service.*;
import model.sql.PostSQL;

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
    
    public List<Post> listWithPaging(int startRow, int pageSize) {
        List<Post> list = new ArrayList<>();
        String sql = "SELECT * FROM Post ORDER BY post_num DESC LIMIT ?, ?";

        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, startRow);
            pstmt.setInt(2, pageSize);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int post_num = rs.getInt(1);
                    String post_subject = rs.getString(2);
                    String post_content = rs.getString(3);
                    java.sql.Date post_date = rs.getDate(4);
                    int post_view = rs.getInt(5);
                    int category_num = rs.getInt(6);
                    String email = rs.getString(7);

                    list.add(new Post(
                        post_num, post_subject, post_content,
                        post_date, post_view, category_num, email
                    ));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

            
    public int getTotalCount() {
        int total = 0;
        String sql = "SELECT COUNT(*) FROM post";
        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) total = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
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
    
    
    public int insertInt(Post dto) {
        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(PostSQL.INSERT, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, dto.getPost_subject());
            pstmt.setString(2, dto.getPost_content());
            pstmt.setInt(3, dto.getCategory_num());
            pstmt.setString(4, dto.getEmail());

            int result = pstmt.executeUpdate();
            if (result > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }


    public boolean delete(int post_num){
        Connection con = null;
        PreparedStatement pstmt = null;
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(DELETE);
            pstmt.setInt(1, post_num);
            int i = pstmt.executeUpdate();
            return i > 0;
        }catch(SQLException se){
            se.printStackTrace();
            return false;
        }finally{
            try{ 
            	pstmt.close(); 
            	con.close(); 
        	}catch(Exception e){}
        }
    }
    
    public boolean update(Post dto){
        Connection con = null;
        PreparedStatement pstmt = null;
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(UPDATE);
            pstmt.setString(1, dto.getPost_subject());
            pstmt.setString(2, dto.getPost_content());
            pstmt.setInt(3, dto.getCategory_num());
            pstmt.setInt(4, dto.getPost_num());
            int i = pstmt.executeUpdate();
            return i > 0;
            
        }catch(SQLException se){
            se.printStackTrace();
            return false;
        }finally{
            try{
            	pstmt.close();
            	con.close();
            }catch(Exception e){}
        }
    }
 
    public Post get(int post_num){
        java.sql.Connection con = null;
        java.sql.PreparedStatement pstmt = null;
        java.sql.ResultSet rs = null;
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(GET);
            pstmt.setInt(1, post_num);
            rs = pstmt.executeQuery();
            if(rs.next()){
                return new Post(
                    rs.getInt("post_num"),
                    rs.getString("post_subject"),
                    rs.getString("post_content"),
                    rs.getDate("post_date"),
                    rs.getInt("post_view"),
                    rs.getInt("category_num"),
                    rs.getString("email")
                );
            }
            return null;
        }catch
        	(java.sql.SQLException se){
            	se.printStackTrace();
            	return null;
        }finally{
            try{
	            rs.close(); 
	            pstmt.close(); 
	            con.close(); 
            }catch(Exception e){}
        }
    }
    
  
	public LinkedHashMap<String, Integer> countPost(){
		LinkedHashMap<String, Integer> map= new LinkedHashMap<>();
		Connection con = null;
		Statement stmt = null;
	    ResultSet rs = null;
	    try{
	    	con = ds.getConnection();
			stmt = con.createStatement();
	        rs = stmt.executeQuery(COUNTPOST);
	        while(rs.next()) {
	        	String category  = rs.getString("category_name");
	        	int count = rs.getInt("post_count");
	        	map.put(category, count);
	        }
	    }catch(SQLException se) {
	    	
	    }finally{
            try{
                rs.close();
                stmt.close();
                con.close();
            }catch(SQLException se){}
        }return map;	
	} 	
	
	public void hit(int post_num){
	    java.sql.Connection con = null;
	    java.sql.PreparedStatement pstmt = null;
	    try{
	        con = ds.getConnection();
	        pstmt = con.prepareStatement(HIT);
	        pstmt.setInt(1, post_num);
	        pstmt.executeUpdate();
	    }catch(java.sql.SQLException se){
	        se.printStackTrace();
	    }finally{
	        try{
	        	pstmt.close(); 
	        	con.close(); 
	        	
	        }catch(Exception e){}
	    }
	}
}
