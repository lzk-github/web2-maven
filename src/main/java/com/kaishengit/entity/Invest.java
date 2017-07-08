package com.kaishengit.entity;

import java.sql.Timestamp;

public class Invest {
	public static final int INVEST_STATE_NORMAL = 1;
	public static final int INVEST_STATE_UNUSE = 2;
	public static final int INVEST_STATE_END = 3;
	
	private int id;
	private int customerId;
	private int projectId;
	private int accId;
	private double rate;
	private int months;
	private int investMoney;
	private Timestamp createTime;
	private String signDate;
	private String endDate;
	
	private String accName;
	private String proName;
	private String custName;
	
	private int state = INVEST_STATE_NORMAL;
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getAccId() {
		return accId;
	}
	public void setAccId(int accId) {
		this.accId = accId;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public int getMonths() {
		return months;
	}
	public void setMonths(int months) {
		this.months = months;
	}
	public int getInvestMoney() {
		return investMoney;
	}
	public void setInvestMoney(int investMoney) {
		this.investMoney = investMoney;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getSignDate() {
		return signDate;
	}
	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
}
