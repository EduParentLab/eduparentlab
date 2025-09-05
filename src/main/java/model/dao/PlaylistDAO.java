package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import domain.Playlist;

public class PlaylistDAO {
    private DataSource ds;

    public PlaylistDAO() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/TestDB");
        } catch (NamingException ne) {
            ne.printStackTrace();
        }
    }

    // 전체 영상 리스트
    public ArrayList<Playlist> selectAll() {
        ArrayList<Playlist> list = new ArrayList<>();

        String sql = "SELECT * FROM playlist ORDER BY id ASC";

        try (Connection con = ds.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Playlist p = makePlaylistFromResultSet(rs);
                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // 카테고리별 영상 리스트
    public ArrayList<Playlist> selectByCategory(String category) {
        ArrayList<Playlist> list = new ArrayList<>();

        String sql = "SELECT * FROM playlist WHERE category = ? ORDER BY id ASC";

        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, category);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Playlist p = makePlaylistFromResultSet(rs);
                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // 공통으로 Playlist 객체 생성
    private Playlist makePlaylistFromResultSet(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String title = rs.getString("title");
        String youtubeUrl = rs.getString("youtube_url");
        String thumbnail = rs.getString("thumbnail");
        String description = rs.getString("description");
        String uploader = rs.getString("uploader");
        Timestamp regDate = rs.getTimestamp("reg_date");
        String category = rs.getString("category");

        return new Playlist(id, title, youtubeUrl, thumbnail, description, uploader, regDate, category);
    }
}
