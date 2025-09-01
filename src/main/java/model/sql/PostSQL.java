package model.sql;

public class PostSQL {
	public final static String INSERT ="insert into post (post_subject, post_content, post_date, post_view, category_num, email) " + "values (?, ?, now(), 0, ?, ?)";
	public final static String DELETE = "delete from post where post_num=?";
	public final static String UPDATE = "update post set post_subject=?, post_content=?, category_num=? where post_num=?";
	public final static String POST = "select * from POST order by post_num desc";
	public final static String GET = "select * from post where post_num=?";
	public final static String HIT = "update post set post_view = post_view+1 where post_num=?";
}
