package domain;

import java.sql.Date;

public class Post {
	private int post_num;
	private String post_subject;
	private String post_content;
	private Date post_date;
	private String post_view;
	private int category_num;
	private String email;
	
	public Post(int post_num, String post_subject, String post_content, Date post_date, String post_view, int category_num, String email) {
		super();
		this.post_num = post_num;
		this.post_subject = post_subject;
		this.post_content = post_content;
		this.post_date = post_date;
		this.post_view = post_view;
		this.category_num = category_num;
		this.email = email;	
	}
}
