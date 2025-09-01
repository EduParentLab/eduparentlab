package model.sql;

public class PostSQL {
	public final static String INSERT ="insert into post (post_subject, post_content, post_date, post_view, category_num, email) " + "values (?, ?, now(), 0, ?, ?)";
	public final static String DELETE = "delete from post where post_num=?";
	public final static String UPDATE = "update post set post_subject=?, post_content=?, category_num=? where post_num=?";
	public final static String POST = "SELECT p.post_num, p.post_subject, p.post_content, p.post_date, p.post_view, p.category_num, p.email, u.nickname, p.likes FROM Post p LEFT JOIN User u ON p.email = u.email ORDER BY p.post_num DESC";
	public final static String GET = "SELECT p.post_num, p.post_subject, p.post_content, p.post_date, p.post_view, p.category_num, p.email, u.nickname, p.likes FROM Post p LEFT JOIN User u ON p.email = u.email WHERE p.post_num=?";
	public final static String HIT = "update post set post_view = post_view+1 where post_num=?";
	
	public final static String USERLIST = "select * from USER order by CDATE";
	public final static String NEWSLIST = "select * from POST where category_num=2 order by post_num desc";//공지사항 카테고리 넘버 모르겠다 나중에 숫자 바꾸기	
	public final static String GHOSTUSERLIST = "select * from USER where role_num=3 order by CDATE";
	
	//민영 마이페이지
	public final static String MYPAGEPOSTLIST = "select post_num, post_subject, post_date, post_view, likes from post where email=? order by post_num desc";
	public final static String MYPAGEPOSTCOUNT = "select count(*) from post where email=?";
	public final static String MYPAGELIKECOUNT = "select sum(likes) from post where email=?";
	public final static String MYPAGECOMMENTCOUNT = "select count(*) from comments where email=?";

	public final static String LIST_PAGING_LATEST = "SELECT p.post_num, p.post_subject, p.post_content, p.post_date, p.post_view, p.category_num, p.email, u.nickname, p.likes FROM Post p LEFT JOIN User u ON p.email = u.email WHERE p.category_num=? ORDER BY p.post_date DESC LIMIT ?, ?";
	public final static String LIST_PAGING_VIEWS = "SELECT p.post_num, p.post_subject, p.post_content, p.post_date, p.post_view, p.category_num, p.email, u.nickname, p.likes FROM Post p LEFT JOIN User u ON p.email = u.email WHERE p.category_num=? ORDER BY p.post_view DESC LIMIT ?, ?";
	public final static String LIST_SEARCH_LATEST = "SELECT p.post_num, p.post_subject, p.post_content, p.post_date, p.post_view, p.category_num, p.email, u.nickname, p.likes FROM Post p LEFT JOIN User u ON p.email = u.email WHERE p.category_num=? AND %s LIKE ? ORDER BY p.post_date DESC LIMIT ?, ?";
	public final static String LIST_SEARCH_VIEWS = "SELECT p.post_num, p.post_subject, p.post_content, p.post_date, p.post_view, p.category_num, p.email, u.nickname, p.likes FROM Post p LEFT JOIN User u ON p.email = u.email WHERE p.category_num=? AND %s LIKE ? ORDER BY p.post_view DESC LIMIT ?, ?";
	public final static String COUNT_BY_CATEGORY ="SELECT COUNT(*) FROM Post WHERE category_num=?";


}
