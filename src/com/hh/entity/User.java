package com.hh.entity;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

public class User implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
    private String name;
    private String password;
    private String phone;
    private String email;
    @JSONField(format="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private java.util.Date jointime;
    private String departmentId;
    private String imagepath;
    private String hobbies;
    public User() {
        super();
    }
    public User(Integer id,String name,String password,String phone,String email,java.util.Date jointime,String departmentId,String imagepath,String hobbies) {
        super();
        this.id = id;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.jointime = jointime;
        this.departmentId = departmentId;
        this.imagepath = imagepath;
        this.hobbies = hobbies;
    }
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public java.util.Date getJointime() {
        return this.jointime;
    }

    public void setJointime(java.util.Date jointime) {
        this.jointime = jointime;
    }

    public String getDepartmentId() {
        return this.departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getImagepath() {
        return this.imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public String getHobbies() {
        return this.hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", phone=" + phone + ", email=" + email
				+ ", jointime=" + jointime + ", departmentId=" + departmentId + ", imagepath=" + imagepath
				+ ", hobbies=" + hobbies + "]";
	}
	
	}
