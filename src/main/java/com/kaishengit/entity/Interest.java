package com.kaishengit.entity;

public class Interest {
	public static final int INTEREST_TOOK = 1;
	public static final int INTEREST_BEFORE_TOOKDATE = 2;
	public static final int INTEREST_NOT_TOOK = 3;
	
	private int id;
	private int custid;
	private int investid;
	private int accid;
	private String interestsendday;
	private double interestmoney;
	private int state = INTEREST_BEFORE_TOOKDATE;
	private String sendtime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustid() {
		return custid;
	}
	public void setCustid(int custid) {
		this.custid = custid;
	}
	public int getInvestid() {
		return investid;
	}
	public void setInvestid(int investid) {
		this.investid = investid;
	}
	public int getAccid() {
		return accid;
	}
	public void setAccid(int accid) {
		this.accid = accid;
	}
	public String getInterestsendday() {
		return interestsendday;
	}
	public void setInterestsendday(String interestsendday) {
		this.interestsendday = interestsendday;
	}
	public double getInterestmoney() {
		return interestmoney;
	}
	public void setInterestmoney(double interestmoney) {
		this.interestmoney = interestmoney;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getSendtime() {
		return sendtime;
	}
	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}
}
