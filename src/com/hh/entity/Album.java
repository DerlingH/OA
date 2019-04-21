package com.hh.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;


@ExcelTarget(value="Album")
public class Album implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ExcelIgnore
	private String id;
	@Excel(name="标题")
	private	String	title;
	@Excel(name = "专辑图像", type = 2 ,width = 40 )
	private	String	coverImg;
	@Excel(name="作者")
	private	String	author;
	@Excel(name="配音")
	private	String	broadcaster;
	@ExcelIgnore
	private	String	score;
	@ExcelIgnore
	private	String	count;
	@ExcelIgnore
	private	String	desc;
	@Excel(name="日期")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private	Date  pubDate;
	
	@ExcelCollection(name="章节")
	private List<Chapter> children;
	public Album() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Album(String id, String title, String coverImg, String author, String broadcaster, String score,
			String count, String desc, Date pubDate, List<Chapter> children) {
		super();
		this.id = id;
		this.title = title;
		this.coverImg = coverImg;
		this.author = author;
		this.broadcaster = broadcaster;
		this.score = score;
		this.count = count;
		this.desc = desc;
		this.pubDate = pubDate;
		this.children = children;
	}
	@Override
	public String toString() {
		return "Album [id=" + id + ", title=" + title + ", coverImg=" + coverImg + ", author=" + author
				+ ", broadcaster=" + broadcaster + ", score=" + score + ", count=" + count + ", desc=" + desc
				+ ", pubDate=" + pubDate + ", children=" + children + "]";
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
	public String getCoverImg() {
		return coverImg;
	}
	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getBroadcaster() {
		return broadcaster;
	}
	public void setBroadcaster(String broadcaster) {
		this.broadcaster = broadcaster;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Date getPubDate() {
		return pubDate;
	}
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}
	public List<Chapter> getChildren() {
		return children;
	}
	public void setChildren(List<Chapter> children) {
		this.children = children;
	}
	
}
