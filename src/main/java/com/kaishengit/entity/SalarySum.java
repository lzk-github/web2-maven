package com.kaishengit.entity;

public class SalarySum {

	private int id;
	private String accName;
	private String accTel;
	private double sumSalary;
	private String date;
	
	public String getAccTel() {
		return accTel;
	}
	@Override
	public String toString() {
		return "SalarySum [id=" + id + ", accName=" + accName + ", acctel=" + accTel + ", sumSalary=" + sumSalary
				+ ", date=" + date + "]";
	}
	public void setAccTel(String acctel) {
		this.accTel = acctel;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public double getSumSalary() {
		return sumSalary;
	}
	public void setSumSalary(double sumSalary) {
		this.sumSalary = sumSalary;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
