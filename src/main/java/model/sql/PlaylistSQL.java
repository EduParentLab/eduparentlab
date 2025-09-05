package model.sql;

public class PlaylistSQL {

    // 전체 목록 조회
    public final static String SELECT_ALL = 
        "SELECT id, title, youtube_url, thumbnail, description, uploader, reg_date " +
        "FROM playlist ORDER BY reg_date DESC";

    // 단일 항목 조회
    public final static String SELECT_BY_ID = 
        "SELECT id, title, youtube_url, thumbnail, description, uploader, reg_date " +
        "FROM playlist WHERE id = ?";

    // 새 항목 추가
    public final static String INSERT = 
        "INSERT INTO playlist (title, youtube_url, thumbnail, description, uploader) " +
        "VALUES (?, ?, ?, ?, ?)";

}
