package model.sql;

public class LikesSQL {
	final public static String CHECKEXISTENCE = "select * from LIKES where email =? and post_num=?";//이미 likes 되어있는지 아닌지 확인하기 위함
	final public static String ADDLIKES = "insert into LIKES values(?, ?)";
	final public static String DELETELIKES = "delete from LIKES where email =? and post_num=?";
	final public static String COUNTLIKES = "select count(post_num) from LIKES where post_num = ?";
	final public static int EXISTENCE = 1;
	final public static int NONEXISTENCE = 0;
}
