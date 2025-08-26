package model.sql;

public class CommentSQL {
	public final static String LIST = "select email, comment_content, comment_date from comments where post num = ?";
	public final static String INSERT = "insert into comments (comment_num, comment_content, comment_date, group_num, group_order, email, post_num) vlaues(?,?,now(),?,?,?,?)";
}
