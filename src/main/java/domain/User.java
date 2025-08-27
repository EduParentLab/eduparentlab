package domain;

import java.sql.Date;

public class User {
	private String email;
	private String password;
	private String nickname;
	private String gender;   
	private Date birth;      
	private String name;
	private String phone;
	private Date Cdate;
	private int role_num;
	
	public User() {}
	public User(String email, String password, String nickname, String gender, Date birth, String name, String phone,
			Date Cdate, int role_num) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.gender = gender;
		this.birth = birth;
		this.name = name;
		this.phone = phone;
		this.Cdate = Cdate;
		this.role_num = role_num;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getCdate() {
		return Cdate;
	}
	public void setCdate(Date cdate) {
		Cdate = cdate;
	}
	public int getRole_num() {
		return role_num;
	}
	public void setRole_num(int role_num) {
		this.role_num = role_num;
	}
}
