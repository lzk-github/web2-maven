package com.kaishengit.entity;

public class Settings {

	private int id;
	private String key;
	private String value;
	
	@Override
	public String toString() {
		return "Settings [id=" + id + ", key=" + key + ", value=" + value + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
