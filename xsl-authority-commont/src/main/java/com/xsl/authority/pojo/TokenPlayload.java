package com.xsl.authority.pojo;

public class TokenPlayload <T>{

	private String Exp;
	private long roleid;
	private String rolename;
	public String getExp() {
		return Exp;
	}
	public void setExp(String exp) {
		Exp = exp;
	}
	public long getRoleid() {
		return roleid;
	}
	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
}
