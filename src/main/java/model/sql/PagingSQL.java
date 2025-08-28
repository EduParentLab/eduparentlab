package model.sql;

public class PagingSQL {
	public final static String BOARD = "select * from board order by post_num desc LIMIT ?, ?";
	public final static String COMMENT= "select * from comments where post_num = ? order by comment_num desc LIMIT ?, ?";
}
