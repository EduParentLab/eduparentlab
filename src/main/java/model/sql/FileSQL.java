package model.sql;

public class FileSQL {
	    public final static String INSERT = "insert into post_file(file_name, file_oname, file_path, post_num) values(?, ?, ?, ?)";
	    public final static String LIST_BY_POST ="select file_num, file_name, file_oname, file_path from post_file where post_num=?";
	    public final static String DELETE_BY_POST = "delete from post_file where post_num=?";
}
