package com.zhang.model;


/**
 * 会员用户模型层
 * @author 12443
 *
 */
public class User {
	
	private String userid;//用户ID
	private String username;//用户名
	private String password;//用户密码
	private String email;//邮箱
	private String telephone;//电话
	private String question;//密保问题
	private String answer;//密保答案
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String username, String password, String email, String telephone, String question, String answer) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.telephone = telephone;
		this.question = question;
		this.answer = answer;
	}
	public User(String userid, String username, String password, String email, String telephone, String question,
			String answer) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.email = email;
		this.telephone = telephone;
		this.question = question;
		this.answer = answer;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", telephone=" + telephone + ", question=" + question + ", answer=" + answer + "]";
	}
	
	
}
