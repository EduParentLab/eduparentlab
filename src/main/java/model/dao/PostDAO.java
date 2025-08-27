package model.dao;

import javax.naming.*;
import javax.sql.*;
import java.sql.*;
import java.util.*;
import domain.Post;
import static model.sql.AdminSQL.*;

public class PostDAO {
	private DataSource ds;
	public PostDAO() {
		try{
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:comp/env");
			ds = (DataSource)envContext.lookup("jdbc/TestDB");
		}catch(NamingException ne){
		}
	}
	public ArrayList<Post> list(){
		ArrayList<Post> list = new ArrayList<Post>();
		Connection con = null;
		Statement stmt = null;
	    ResultSet rs = null;
	    try{
			con = ds.getConnection();
			stmt = con.createStatement();
	        rs = stmt.executeQuery(NEWSLIST);
	        while(rs.next()){
	            int post_num = rs.getInt(1);
	            String post_subject = rs.getString(2);	   
				String post_content = rs.getString(3);
				java.sql.Date post_date = rs.getDate(4);
				String post_view  = rs.getString(5);
				int category_num  = rs.getInt(6);
				String email = rs.getString(7);
	            
	            list.add(new Post(post_num, post_subject, post_content, post_date, post_view, category_num, email));
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
}
