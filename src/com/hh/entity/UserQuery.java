package com.hh.entity;

public class UserQuery {
	
	
	private String uqname;
	private String depid;
	public UserQuery() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserQuery(String uqname, String depid) {
		super();
		this.uqname = uqname;
		this.depid = depid;
	}
	public String getuqname() {
		return uqname;
	}
	public void setuqname(String uqname) {
		this.uqname = uqname;
	}
	public String getDepid() {
		return depid;
	}
	public void setDepid(String depid) {
		this.depid = depid;
	}
	@Override
	public String toString() {
		return "UserQuery [uqname=" + uqname + ", depid=" + depid + "]";
	}
	
	
}
