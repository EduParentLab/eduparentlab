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
import model.sql.AdminSQL;

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
    
    public List<Post> listWithPaging(int startRow, int pageSize, String sort, int categoryNum) {
        List<Post> list = new ArrayList<>();
        String sql = ("views".equals(sort)) ? PostSQL.LIST_PAGING_VIEWS : PostSQL.LIST_PAGING_LATEST;

        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, categoryNum);
            pstmt.setInt(2, startRow);
            pstmt.setInt(3, pageSize);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String nickname = rs.getString("nickname");
                    if (nickname == null || nickname.isBlank()) {
                        nickname = rs.getString("email");
                    }

                    list.add(new Post(
                        rs.getInt("post_num"),
                        rs.getString("post_subject"),
                        rs.getString("post_content"),
                        rs.getDate("post_date"),
                        rs.getInt("post_view"),
                        rs.getInt("category_num"),
                        rs.getString("email"),
                        nickname,
                        rs.getInt("likes")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Post> searchWithPaging(int startRow,
    		int pageSize, String sort, String type, String keyword, int categoryNum) {
    	
        List<Post> list = new ArrayList<>();

        String column;
        switch (type) {
            case "title": column = "p.post_subject"; break;
            case "writer": column = "u.nickname"; break;
            case "title_content": column = "CONCAT(p.post_subject,' ',p.post_content)"; break;
            default: column = "p.post_subject"; break;
        }

        String baseSql = ("views".equals(sort)) ? PostSQL.LIST_SEARCH_VIEWS : PostSQL.LIST_SEARCH_LATEST;
        String sql = String.format(baseSql, column);

        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, categoryNum);
            pstmt.setString(2, "%" + keyword + "%");
            pstmt.setInt(3, startRow);
            pstmt.setInt(4, pageSize);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String nickname = rs.getString("nickname");
                    if (nickname == null || nickname.isBlank()) nickname = rs.getString("email");

                    list.add(new Post(
                        rs.getInt("post_num"),
                        rs.getString("post_subject"),
                        rs.getString("post_content"),
                        rs.getDate("post_date"),
                        rs.getInt("post_view"),
                        rs.getInt("category_num"),
                        rs.getString("email"),
                        nickname,
                        rs.getInt("likes")
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
                    rs.getString("email"),
                    rs.getString("nickname"),
                    rs.getInt("likes")
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
	    	se.printStackTrace();	   	
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
	

	//민영 추가- 내가 쓴 글 목록
    public List<Post> mypagePostList(String email) {
        List<Post> list = new ArrayList<>();
        String sql = PostSQL.MYPAGEPOSTLIST;

        try (Connection con = ds.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {
               
               pstmt.setString(1, email);
               System.out.println("쿼리 실행 email = " + email); //확인
               try (ResultSet rs = pstmt.executeQuery()) {
                   while (rs.next()) {
                       Post post = new Post();
                       post.setPost_num(rs.getInt("post_num"));
                       post.setPost_subject(rs.getString("post_subject"));
                       post.setPost_date(rs.getDate("post_date"));
                       post.setPost_view(rs.getInt("post_view"));
                       post.setLikes(rs.getInt("likes")); //여기 추가
                       list.add(post);
                   }
               }
           } catch (SQLException e) {
               e.printStackTrace();
           }
           System.out.println("mypagePostList 결과 = " + list.size()); // 확인
           return list;
    }
    
    //민영 추가- 내가 쓴 글 총 개수
    public int mypagePostCount(String email) {
        int total = 0;
        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(PostSQL.MYPAGEPOSTCOUNT)) {
            
            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    total = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }
    
    //민영 추가- 내가 받은 총 공감 수
    public int mypageLikeCount(String email) {
        int total = 0;
        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(PostSQL.MYPAGELIKECOUNT)) {
            
            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    total = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }
    
    //민영 추가- 내가 쓴 댓글 수 
    public int mypageCommentCount(String email) {
        int total = 0;
        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(PostSQL.MYPAGECOMMENTCOUNT)) {
            
            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    total = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }
    

	public int getTotalCountByCategory(int categoryNum) {
	    int total = 0;
	    try (Connection con = ds.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(PostSQL.COUNT_BY_CATEGORY)) {
	        pstmt.setInt(1, categoryNum);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) total = rs.getInt(1);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return total;
	}
	//관리자페이지 공지사항 가져오기
	public ArrayList<Post> listNotice() {
        ArrayList<Post> list = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            con = ds.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(NOTICELIST);

            while (rs.next()) {
                int post_num = rs.getInt(1);
                String post_subject = rs.getString(2);
                String post_content = rs.getString(3);
                java.sql.Date post_date = rs.getDate(4);
                int post_view = rs.getInt(5);
                int category_num = rs.getInt(6);
                String email = rs.getString(7);               
                int likes = rs.getInt(8);
                String nickname = "관리자";

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

}
