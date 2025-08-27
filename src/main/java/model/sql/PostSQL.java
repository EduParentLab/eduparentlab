package model.sql;

public class PostSQL {
	final public static String INSERT =
		    "insert into post (post_num, post_subject, post_content, post_date, post_view, category_num, email) "
		  + "values (?, ?, ?, now(), 0, ?, ?)";
	final public static String DELETE = "delete from post where post_num=?";
	final public static String UPDATE = "update post set post_subject=?, post_content=?, category_num=? where post_num=?";
	final public static String POST = "select * from POST order by post_num desc";
	final public static String GET = "select * from post where post_num=?";
	final public static String HIT = "update post set post_view = post_view+1 where post_num=?";
	
	final public static String USERLIST = "select * from USER order by CDATE";
	final public static String NEWSLIST = "select * from POST where category_num=2 order by post_num desc";//공지사항 카테고리 넘버 모르겠다 나중에 숫자 바꾸기	
	final public static String GHOSTUSERLIST = "select * from USER where role_num=3 order by CDATE";
	
}
