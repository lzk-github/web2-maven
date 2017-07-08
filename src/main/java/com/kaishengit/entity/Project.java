package com.kaishengit.entity;

import java.sql.Timestamp;

public class Project {
	private int id;
	private String projectName;
	private double repayRate;
	private double sumMoney;
	private double remainMoney;
	private int months;
	private String startDate;
	private String endDate;
	private Timestamp createTime;
	private int state = 1;
	private String remark;
	
	public double getRemainMoney() {
		return remainMoney;
	}
	public void setRemainMoney(double remainMoney) {
		this.remainMoney = remainMoney;
	}
	public int getMonths() {
		return months;
	}
	public void setMonths(int months) {
		this.months = months;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public double getRepayRate() {
		return repayRate;
	}
	public void setRepayRate(double repayRate) {
		this.repayRate = repayRate;
	}
	public double getSumMoney() {
		return sumMoney;
	}
	public void setSumMoney(double sumMoney) {
		this.sumMoney = sumMoney;
	}
	
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
