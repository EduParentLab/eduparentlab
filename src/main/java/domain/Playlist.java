package domain;

import java.sql.Timestamp;

public class Playlist {
    private int id;
    private String title;
    private String youtubeUrl;
    private String thumbnail;
    private String description;
    private String uploader;
    private Timestamp regDate;
    private String category;  // ✅ 추가
    private String link;

    public Playlist(int id, String title, String youtubeUrl, String thumbnail, String description, String uploader, Timestamp regDate, String category) {
        this.id = id;
        this.title = title;
        this.youtubeUrl = youtubeUrl;
        this.thumbnail = thumbnail;
        this.description = description;
        this.uploader = uploader;
        this.regDate = regDate;
        this.category = category;
    }

    // ✅ getter/setter 전부 추가
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getYoutubeUrl() { return youtubeUrl; }
    public String getThumbnail() { return thumbnail; }
    public String getDescription() { return description; }
    public String getUploader() { return uploader; }
    public Timestamp getRegDate() { return regDate; }
    public String getCategory() { return category; }
    public String getLink() {
        return link;
    }

    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setYoutubeUrl(String youtubeUrl) { this.youtubeUrl = youtubeUrl; }
    public void setThumbnail(String thumbnail) { this.thumbnail = thumbnail; }
    public void setDescription(String description) { this.description = description; }
    public void setUploader(String uploader) { this.uploader = uploader; }
    public void setRegDate(Timestamp regDate) { this.regDate = regDate; }
    public void setCategory(String category) { this.category = category; }
    public void setLink(String link) {
        this.link = link;
    }
}
