package com.hh.entity;

import java.util.List;

//部门实体
public class Dept {
	private Integer id;
	private String deptname;
	private int count;
	private List<User> deptusers;
	public Dept() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Dept(Integer id, String deptname, int count, List<User> deptusers) {
		super();
		this.id = id;
		this.deptname = deptname;
		this.count = count;
		this.deptusers = deptusers;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<User> getDeptusers() {
		return deptusers;
	}
	public void setDeptusers(List<User> deptusers) {
		this.deptusers = deptusers;
	}
	@Override
	public String toString() {
		return "Dept [id=" + id + ", deptname=" + deptname + ", count=" + count + ", deptusers=" + deptusers + "]";
	}
	
	
}
