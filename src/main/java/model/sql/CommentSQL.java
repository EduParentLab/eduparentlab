package model.sql;

public class CommentSQL {
	public final static String LIST = "select c.comment_num, c.email, c.comment_content, c.comment_date, u.nickname from comments c join user u on c.email=u.email where c.post_num = ? and c.comment_num = c.group_num LIMIT ?, ?";
	public final static String LIST_L = "select c.comment_num, c.email, c.comment_content, c.comment_date, u.nickname from comments c join user u on c.email=u.email where c.post_num = ? and c.comment_num = c.group_num order by c.comment_num desc LIMIT ?,?";
	public final static String SELECT = "select c.*, u.nickname from comments c join user u on c.email=u.email where c.comment_num = ?";
	public final static String INSERT = "insert into comments (comment_content, comment_date, group_num, group_order, email, post_num) values(?,now(),?,?,?,?)";
	public final static String DELETE = "delete from comments where comment_num = ?";
	public final static String UPDATE = "update comments set comment_content = ? , comment_date = now() where comment_num = ?";
	public final static String RECOMMENT = "select IFNULL(MAX(group_order),0) from comments where group_num = ?";
	public final static String LIST_R = "SELECT c.*, u.nickname FROM comments c join user u on c.email=u.email WHERE c.group_num = ? AND c.comment_num <> c.group_num ORDER BY c.comment_date ASC";
	
}
