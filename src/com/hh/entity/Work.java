package com.hh.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

public class Work implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
    @JSONField(format="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private java.util.Date starttime;
    @JSONField(format="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private java.util.Date endtime;
    private String workname;
    private String workcontent;
    
    //设计表的时候不用心 就是这种结果   刚开始用id 想展示出来部门名称跟用户名称的时候就凉了
    
    private Dept dept;
    private User user;
    private Integer status;
	public Work() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Work(Integer id, Date starttime, Date endtime, String workname, String workcontent, Dept dept, User user,
			Integer status) {
		super();
		this.id = id;
		this.starttime = starttime;
		this.endtime = endtime;
		this.workname = workname;
		this.workcontent = workcontent;
		this.dept = dept;
		this.user = user;
		this.status = status;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public java.util.Date getStarttime() {
		return starttime;
	}
	public void setStarttime(java.util.Date starttime) {
		this.starttime = starttime;
	}
	public java.util.Date getEndtime() {
		return endtime;
	}
	public void setEndtime(java.util.Date endtime) {
		this.endtime = endtime;
	}
	public String getWorkname() {
		return workname;
	}
	public void setWorkname(String workname) {
		this.workname = workname;
	}
	public String getWorkcontent() {
		return workcontent;
	}
	public void setWorkcontent(String workcontent) {
		this.workcontent = workcontent;
	}
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Work [id=" + id + ", starttime=" + starttime + ", endtime=" + endtime + ", workname=" + workname
				+ ", workcontent=" + workcontent + ", dept=" + dept + ", user=" + user + ", status=" + status + "]";
	}
   
    
	
}
