package com.hh.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;

public class Chapter implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ExcelIgnore
	private String id;
	@Excel(name="标题")
	private String title;
	@Excel(name="大小")
	private String size;
	@Excel(name="时长")
	private String duration;
	@Excel(name="日期")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date pubDate;
	@Excel(name="地址")
	private String url;
	@ExcelIgnore
	private Album album;
	
	
	
	public Chapter() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Chapter(String id, String title, String size, String duration, Date pubDate, String url, Album album) {
		super();
		this.id = id;
		this.title = title;
		this.size = size;
		this.duration = duration;
		this.pubDate = pubDate;
		this.url = url;
		this.album = album;
	}
	@Override
	public String toString() {
		return "Chapter [id=" + id + ", title=" + title + ", size=" + size + ", duration=" + duration + ", pubDate="
				+ pubDate + ", url=" + url + ", album=" + album + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public Date getPubDate() {
		return pubDate;
	}
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Album getAlbum() {
		return album;
	}
	public void setAlbum(Album album) {
		this.album = album;
	}
	
	
}
