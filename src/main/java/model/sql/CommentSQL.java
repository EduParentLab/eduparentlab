package model.sql;

public class CommentSQL {
	public final static String LIST = "select comment_num, email, comment_content, comment_date from comments where post_num = ? LIMIT ?, ?";
	public final static String LIST_L = "select comment_num, email, comment_content, comment_date from comments where post_num = ? order by comment_num desc LIMIT ?,?";
	public final static String SELECT = "select * from comments where comment_num = ?";
	public final static String INSERT = "insert into comments (comment_content, comment_date, group_num, group_order, email, post_num) values(?,now(),?,?,?,?)";
	public final static String DELETE = "delete from comments where comment_num = ?";
	public final static String UPDATE = "update comments set comment_content = ? , comment_date = now() where comment_num = ?";
	public final static String RECOMMENT = "select IFNULL(MAX(group_order),0) from comments where group_num = ?";
}
