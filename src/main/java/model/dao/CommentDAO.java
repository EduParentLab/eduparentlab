package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
				PreparedStatement pstmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);){
			pstmt.setString(1, dto.getComment_content());
			pstmt.setInt(2, 0);
			pstmt.setInt(3, 0);
			pstmt.setString(4, dto.getEmail());
			pstmt.setInt(5, post_num);
			int result = pstmt.executeUpdate();
			if(result > 0) {
				ResultSet rs = pstmt.getGeneratedKeys();
                if(rs.next()) {
                    int comment_num = rs.getInt(1);
                    dto.setComment_num(comment_num);
                    String updateSql = "UPDATE comments SET group_num = ? WHERE comment_num = ?";
                    try (PreparedStatement upstmt = con.prepareStatement(updateSql)) {
                        upstmt.setInt(1, comment_num);
                        upstmt.setInt(2, comment_num);
                        upstmt.executeUpdate();
                    }
                }
                return 1;
            }
			else {
				return 0;
			}
		}catch(SQLException se) {
			se.printStackTrace();
			return 0;
		}
	}

	public ArrayList<Comment> selectedByPostNum(int post_num, boolean latestFirst, int startRow, int pageSize) {
		ArrayList<Comment> clist = new ArrayList<Comment>();
		String sql = latestFirst ? LIST_L : LIST;
		try (Connection con = ds.getConnection();
		         PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, post_num);
			pstmt.setInt(2,  startRow);
			pstmt.setInt(3, pageSize);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int comment_num = rs.getInt("comment_num");
				String email = rs.getString("email");
				String content = rs.getString("comment_content");
				Timestamp date = rs.getTimestamp("comment_date");
				String nickName = rs.getString("nickname");
				clist.add(new Comment(comment_num, content, date, -1, -1, email, post_num, nickName));
			}
			System.out.println("@DAO list comment_nums: " + 
				    clist.stream().map(Comment::getComment_num).collect(Collectors.toList()));
            for (Comment comment : clist) {
                List<Comment> replies = getRecomments(comment.getComment_num());
                comment.setRecomments(replies);
            }
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return clist;
	}
    public int getTotalCount(int post_num) {
        int total = 0;
        String sql = "SELECT COUNT(*) FROM comments where post_num = ? and group_num = comment_num";
        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)){
        	pstmt.setInt(1, post_num);
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
    public int getTotalCommentCountIncludingReplies(int post_num) {
        int total = 0;
        String sql = "SELECT COUNT(*) FROM comments WHERE post_num = ?";
        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, post_num);
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
	public Comment selectedByCommentNum(int comment_num) {
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(SELECT);){
			pstmt.setInt(1, comment_num);
	        ResultSet rs = pstmt.executeQuery();
	        if(rs.next()) {
	            return new Comment(
	                rs.getInt("comment_num"),
	                rs.getString("comment_content"),
	                rs.getTimestamp("comment_date"),
	                rs.getInt("group_num"),
	                rs.getInt("group_order"),
	                rs.getString("email"),
	                rs.getInt("post_num"),
	                rs.getString("nickname")
	            );
	        }
		}catch(SQLException se) {
			se.printStackTrace();
		}
		return null;
		
	}
	public int delete(int comment_num) {
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(DELETE);){
			pstmt.setInt(1, comment_num);
			int i = pstmt.executeUpdate();
			System.out.println("댓글 delete 된 쿼리의 수 : "+i);
			if(i>0) return 1; 
			else return 0;
		}catch(SQLException se) {
			se.printStackTrace();
			return 0;
		}
	}
	public int update(String comment_content,int comment_num) {
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(UPDATE);){
			pstmt.setString(1,  comment_content);
			pstmt.setInt(2, comment_num);
			int i = pstmt.executeUpdate();
			System.out.println("댓글 update 된 쿼리의 수 : "+i);
			if(i>0) return 1;
			else return 0;
		}catch(SQLException se) {
			se.printStackTrace();
			return 0;
		}
	}
	public int recomment(Comment dto,int post_num) {
		int maxOrder = 0;
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(RECOMMENT);
				PreparedStatement pstmt2 = con.prepareStatement(INSERT);){
			
			pstmt.setInt(1, dto.getGroup_num());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				maxOrder = rs.getInt(1);
			}
			int newGroupOrder = maxOrder +1;
			
			pstmt2.setString(1,dto.getComment_content());
			pstmt2.setInt(2, dto.getGroup_num());
			pstmt2.setInt(3, newGroupOrder);
			pstmt2.setString(4, dto.getEmail());
			pstmt2.setInt(5, post_num);
			int i = pstmt2.executeUpdate();
			System.out.println("답댓글 insert 된 쿼리의 수 : "+i);
			if(i>0) {
				return 1;
			}
			else {
				return 0;
			}
		}catch(SQLException se) {
			se.printStackTrace();
			return 0;
		}
	}
	public List<Comment> getRecomments(int groupNum){
		List<Comment> list = new ArrayList<>();
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(LIST_R);){
			
			
			pstmt.setInt(1, groupNum);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Comment c = new Comment();
                c.setComment_num(rs.getInt("comment_num"));
                c.setPost_num(rs.getInt("post_num"));
                c.setGroup_num(rs.getInt("group_num"));
                c.setComment_content(rs.getString("comment_content"));
                c.setEmail(rs.getString("email"));
                c.setComment_date(rs.getTimestamp("comment_date"));
                c.setNickName(rs.getString("nickname"));
                list.add(c);
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return list;
	}
	
}
