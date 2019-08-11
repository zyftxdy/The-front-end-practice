package com.zhang.model;

/**
 * 模型层--权限人员类
 * @author 12443
 *
 */
public class AuthorityUser {

	private int id;
	private String adminName;
	private String adminPwd;
	private int authority;
	private String create_date;
	
	public AuthorityUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AuthorityUser(String adminName, String adminPwd, int authority) {
		super();
		this.adminName = adminName;
		this.adminPwd = adminPwd;
		this.authority = authority;
	}

	public AuthorityUser(int id, String adminName, String adminPwd, int authority, String create_date) {
		super();
		this.id = id;
		this.adminName = adminName;
		this.adminPwd = adminPwd;
		this.authority = authority;
		this.create_date = create_date;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminPwd() {
		return adminPwd;
	}
	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}
	public int getAuthority() {
		return authority;
	}
	public void setAuthority(int authority) {
		this.authority = authority;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	@Override
	public String toString() {
		return "AuthorityUser [id=" + id + ", adminName=" + adminName + ", adminPwd=" + adminPwd + ", authority="
				+ authority + ", create_date=" + create_date + "]";
	}
	
}
