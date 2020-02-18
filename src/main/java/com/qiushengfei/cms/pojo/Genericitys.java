package com.qiushengfei.cms.pojo;

public enum Genericitys {

	
	HTML(0,"是文本内容"),IMAGE(1,"不是文本内容而是图片");
	//ID
	private int id;
	//文本类型
	private String genre;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	private Genericitys(int id, String genre) {
		this.id = id;
		this.genre = genre;
	}
	private Genericitys() {
	}

	
}
