package domain;

import java.sql.Date;

public class Comment {
	private int comment_num;
	private String comment_contet;
	private Date comment_date;
	private int group_num;
	private int group_order;
	private String email;
	private int post_num;
	
	public Comment() {
		
	}

	public Comment(int comment_num, String comment_contet, Date comment_date, int group_num, int group_order,
			String email, int post_num) {
		this.comment_num = comment_num;
		this.comment_contet = comment_contet;
		this.comment_date = comment_date;
		this.group_num = group_num;
		this.group_order = group_order;
		this.email = email;
		this.post_num = post_num;
	}

	public int getComment_num() {
		return comment_num;
	}

	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}

	public String getComment_contet() {
		return comment_contet;
	}

	public void setComment_contet(String comment_contet) {
		this.comment_contet = comment_contet;
	}

	public Date getComment_date() {
		return comment_date;
	}

	public void setComment_date(Date comment_date) {
		this.comment_date = comment_date;
	}

	public int getGroup_num() {
		return group_num;
	}

	public void setGroup_num(int group_num) {
		this.group_num = group_num;
	}

	public int getGroup_order() {
		return group_order;
	}

	public void setGroup_order(int group_order) {
		this.group_order = group_order;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPost_num() {
		return post_num;
	}

	public void setPost_num(int post_num) {
		this.post_num = post_num;
	}
	
	
	
	
}
