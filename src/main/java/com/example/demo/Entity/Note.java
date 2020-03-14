package com.example.demo.Entity;

import java.io.Serializable;

public class Note implements Serializable {
	private static final long serialVersionUID = 1L;
	Integer id;
	String note_date;
	String note_content;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNote_date() {
		return note_date;
	}
	public void setNote_date(String note_date) {
		this.note_date = note_date;
	}
	public String getNote_content() {
		return note_content;
	}
	public void setNote_content(String note_content) {
		this.note_content = note_content;
	}
	
}
