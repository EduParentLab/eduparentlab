package model.sql;

public class FileSQL { 
        public final static String INSERT ="INSERT INTO `File` (file_name, file_origin_name, file_path, post_num) VALUES (?, ?, ?, ?)";
        public final static String LIST_BY_POST ="SELECT file_num, file_name, file_origin_name, file_path FROM `File` WHERE post_num=?";
        public final static String DELETE_BY_POST ="DELETE FROM `File` WHERE post_num=?"; 
        public final static String UPDATE_BY_FILE = "update post set post_subject=?, post_content=?, category_num=? where post_num=?";
}
