package com.hh.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

public class Leave {
    private Integer id;
    @JSONField(format="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private java.util.Date leaveDate;
    @JSONField(format="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private java.util.Date leaveStarttime;
    @JSONField(format="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private java.util.Date leaveEndttime;
    private String reason;
    private Integer leaveStatus;//0未审核   1 通过   2 未通过
    private User leaveUser;//申请人
    private User examineUser;//审批人
    public Leave() {
        super();
    }
	public Leave(Integer id, Date leaveDate, Date leaveStarttime, Date leaveEndttime, String reason,
			Integer leaveStatus, User leaveUser, User examineUser) {
		super();
		this.id = id;
		this.leaveDate = leaveDate;
		this.leaveStarttime = leaveStarttime;
		this.leaveEndttime = leaveEndttime;
		this.reason = reason;
		this.leaveStatus = leaveStatus;
		this.leaveUser = leaveUser;
		this.examineUser = examineUser;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public java.util.Date getLeaveDate() {
		return leaveDate;
	}
	public void setLeaveDate(java.util.Date leaveDate) {
		this.leaveDate = leaveDate;
	}
	public java.util.Date getLeaveStarttime() {
		return leaveStarttime;
	}
	public void setLeaveStarttime(java.util.Date leaveStarttime) {
		this.leaveStarttime = leaveStarttime;
	}
	public java.util.Date getLeaveEndttime() {
		return leaveEndttime;
	}
	public void setLeaveEndttime(java.util.Date leaveEndttime) {
		this.leaveEndttime = leaveEndttime;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Integer getLeaveStatus() {
		return leaveStatus;
	}
	public void setLeaveStatus(Integer leaveStatus) {
		this.leaveStatus = leaveStatus;
	}
	public User getLeaveUser() {
		return leaveUser;
	}
	public void setLeaveUser(User leaveUser) {
		this.leaveUser = leaveUser;
	}
	public User getExamineUser() {
		return examineUser;
	}
	public void setExamineUser(User examineUser) {
		this.examineUser = examineUser;
	}
	@Override
	public String toString() {
		return "Leave [id=" + id + ", leaveDate=" + leaveDate + ", leaveStarttime=" + leaveStarttime
				+ ", leaveEndttime=" + leaveEndttime + ", reason=" + reason + ", leaveStatus=" + leaveStatus
				+ ", leaveUser=" + leaveUser + ", examineUser=" + examineUser + "]";
	}
    
}
