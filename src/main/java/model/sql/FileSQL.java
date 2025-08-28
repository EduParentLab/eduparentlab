package model.sql;

public class FileSQL {
    public final static String INSERT = "INSERT INTO File(file_name, file_origin_name, file_path, post_num) VALUES(?, ?, ?, ?)";
    public final static String LIST_BY_POST ="SELECT file_num, file_name, file_origin_name AS file_oname, file_path FROM File WHERE post_num=?";
    public final static String DELETE_BY_POST = "DELETE FROM File WHERE post_num=?";
}
