package com.zhang.model;


/**
 * ��Ա�û�ģ�Ͳ�
 * @author 12443
 *
 */
public class User {
	
	private String userid;//�û�ID
	private String username;//�û���
	private String password;//�û�����
	private String email;//����
	private String telephone;//�绰
	private String question;//�ܱ�����
	private String answer;//�ܱ���
	
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
