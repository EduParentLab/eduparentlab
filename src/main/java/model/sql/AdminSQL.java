package model.sql;

public class AdminSQL {
	final public static String USERLIST = "select * from USER order by CDATE";
	final public static String NEWSLIST = "select * from POST where category_num=4 order by post_num desc";
	final public static String GHOSTUSERLIST = "select * from USER where role_num=3 order by CDATE";
	final public static String COUNTUSER = "select date(cdate) as 'cdate', count(*) 'user_count' from user where cdate >= DATE_SUB(CURDATE(), INTERVAL 7 DAY) group by date(cdate) order by date(cdate)";
	final public static String COUNTPOST = "select C.category_name, count(P.post_num) as 'post_count' from post P join category C on P.category_num=C.category_num group by C.category_num";
}
