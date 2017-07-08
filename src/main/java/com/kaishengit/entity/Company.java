package com.kaishengit.entity;

public class Company {

	private int id;
	private String companyName;
	private String companyTel;
	private String city;
	private String controller;
	private String remark;
	
	
	public String getCompanyTel() {
		return companyTel;
	}
	public void setCompanyTel(String companyTel) {
		this.companyTel = companyTel;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Company [id=" + id + ", companyName=" + companyName + ", tel=" + companyTel + ", city=" + city + ", controller="
				+ controller + ", remark=" + remark + "]";
	}
	public String getController() {
		return controller;
	}
	public void setController(String controller) {
		this.controller = controller;
	}
	
	
	
}
