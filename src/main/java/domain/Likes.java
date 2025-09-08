package domain;

public class Likes { 
	private String email;
	private int post_num;
		
	public Likes(String email, int post_num) {		
		this.email = email;
		this.post_num = post_num;
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
