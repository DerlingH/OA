
package com.hh.entity;

import java.io.Serializable;
import java.util.List;

public class Menu implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
    private String title;
    private String iconcls;
    private String href;
    private Menu menu;
    private List<Menu> menus;
	public Menu() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Menu(Integer id, String title, String iconcls, String href, Menu menu, List<Menu> menus) {
		super();
		this.id = id;
		this.title = title;
		this.iconcls = iconcls;
		this.href = href;
		this.menu = menu;
		this.menus = menus;
	}
	@Override
	public String toString() {
		return "Menu [id=" + id + ", title=" + title + ", iconcls=" + iconcls + ", href=" + href + ", menu=" + menu
				+ ", menus=" + menus + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIconcls() {
		return iconcls;
	}
	public void setIconcls(String iconcls) {
		this.iconcls = iconcls;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
    
}
