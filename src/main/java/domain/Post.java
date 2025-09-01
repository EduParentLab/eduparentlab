package domain;

import java.sql.Date;

public class Post {
    private int post_num;
    private String post_subject;
    private String post_content;
    private Date post_date;
    private int post_view;   
    private int category_num;
    private String email;
    private int likes;
    private String nickname;   
    
    public Post(){}
    
    public Post(int post_num, String post_subject, String post_content,
                Date post_date, int post_view, int category_num, String email, String nickname, int likes) {
        this.post_num = post_num;
        this.post_subject = post_subject;
        this.post_content = post_content;
        this.post_date = post_date;
        this.post_view = post_view;
        this.category_num = category_num;
        this.email = email;
        this.nickname = nickname;
        this.likes = likes;
        
    }


	public int getPost_num() {
		return post_num;
	}

	public void setPost_num(int post_num) {
		this.post_num = post_num;
	}

	public String getPost_subject() {
		return post_subject;
	}

	public void setPost_subject(String post_subject) {
		this.post_subject = post_subject;
	}

	public String getPost_content() {
		return post_content;
	}

	public void setPost_content(String post_content) {
		this.post_content = post_content;
	}

	public Date getPost_date() {
		return post_date;
	}

	public void setPost_date(Date post_date) {
		this.post_date = post_date;
	}

	public int getPost_view() {
		return post_view;
	}

	public void setPost_view(int post_view) {
		this.post_view = post_view;
	}

	public int getCategory_num() {
		return category_num;
	}

	public void setCategory_num(int category_num) {
		this.category_num = category_num;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
