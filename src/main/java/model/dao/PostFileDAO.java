package model.dao;

import javax.naming.*;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import domain.PostFile;
import static model.sql.FileSQL.*;

public class PostFileDAO {
    private DataSource ds;
    public PostFileDAO() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context)initContext.lookup("java:/comp/env");
            ds = (DataSource)envContext.lookup("jdbc/TestDB");
        } catch (NamingException ne) {}
    }

    public boolean insert(PostFile f, long post_num) {
        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(INSERT)) {
            pstmt.setString(1, f.getFile_name());
            pstmt.setString(2, f.getFile_oname());
            pstmt.setString(3, f.getFile_path());
            pstmt.setLong(4, post_num);
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<PostFile> listByPost(long post_num) {
        ArrayList<PostFile> list = new ArrayList<>();
        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(LIST_BY_POST)) {
            pstmt.setLong(1, post_num);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new PostFile(
                        rs.getLong("file_num"),
                        rs.getString("file_name"),
                        rs.getString("file_oname"),
                        rs.getString("file_path")
                    ));
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public boolean deleteByPost(long post_num) {
        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(DELETE_BY_POST)) {
            pstmt.setLong(1, post_num);
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
        	
            e.printStackTrace();
            return false;
        }
    }
}
