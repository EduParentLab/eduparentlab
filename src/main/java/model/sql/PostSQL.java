package model.sql;

public class PostSQL {
	final static String INSERT="";
	final public static String USERLIST = "select * from USER order by CDATE desc";
	final public static String NEWSLIST = "select * from POST where category_num=2 order by post_num desc";//공지사항 카테고리 넘버 모르겠다 나중에 숫자 바꾸기	
}
