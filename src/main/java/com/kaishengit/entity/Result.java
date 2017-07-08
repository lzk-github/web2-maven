package com.kaishengit.entity;

public class Result {

	private String state;
	private String message;
	private Object data;
	
	
	public Result(String state, String message) {
		this.state = state;
		this.message = message;
	}
	public Result(String state) {
		this.state = state;
	}
	public Result() {
	}
	public Result(String state, Object data) {
		this.state = state;
		this.data = data;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
