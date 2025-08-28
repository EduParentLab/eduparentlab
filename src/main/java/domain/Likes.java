package domain;

public class Likes { 
	private String eamil;
	private int post_num;
		
	public Likes(String eamil, int post_num) {		
		this.eamil = eamil;
		this.post_num = post_num;
	}
	public String getEamil() {
		return eamil;
	}

	public void setEamil(String eamil) {
		this.eamil = eamil;
	}

	public int getPost_num() {
		return post_num;
	}

	public void setPost_num(int post_num) {
		this.post_num = post_num;
	}	
}
