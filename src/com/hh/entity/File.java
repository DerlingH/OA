package com.hh.entity;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

public class File {
    private Integer id;
    private String fileName;
    private String fileSize;
    @JSONField(format="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private java.util.Date fileUploadtime;
    private String fileSavepath;
    private Integer userid;
    public File() {
        super();
    }
    public File(Integer id,String fileName,String fileSize,java.util.Date fileUploadtime,String fileSavepath,Integer userid) {
        super();
        this.id = id;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.fileUploadtime = fileUploadtime;
        this.fileSavepath = fileSavepath;
        this.userid = userid;
    }
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return this.fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public java.util.Date getFileUploadtime() {
        return this.fileUploadtime;
    }

    public void setFileUploadtime(java.util.Date fileUploadtime) {
        this.fileUploadtime = fileUploadtime;
    }

    public String getFileSavepath() {
        return this.fileSavepath;
    }

    public void setFileSavepath(String fileSavepath) {
        this.fileSavepath = fileSavepath;
    }

    public Integer getUserid() {
        return this.userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
	@Override
	public String toString() {
		return "File [id=" + id + ", fileName=" + fileName + ", fileSize=" + fileSize + ", fileUploadtime="
				+ fileUploadtime + ", fileSavepath=" + fileSavepath + ", userid=" + userid + "]";
	}
    
}
