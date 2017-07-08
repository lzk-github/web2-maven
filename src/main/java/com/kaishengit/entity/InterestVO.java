package com.kaishengit.entity;

public class InterestVO {

	private int id;
	private String custName;
	private String tel;
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	private int investMoney;
	private double interestMoney;
	private int state = Interest.INTEREST_BEFORE_TOOKDATE;
	private String interestsendday;
	private String sendtime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public int getInvestMoney() {
		return investMoney;
	}
	public void setInvestMoney(int investMoney) {
		this.investMoney = investMoney;
	}
	public double getInterestMoney() {
		return interestMoney;
	}
	public void setInterestMoney(double interestMoney) {
		this.interestMoney = interestMoney;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getInterestsendday() {
		return interestsendday;
	}
	public void setInterestsendday(String interestsendday) {
		this.interestsendday = interestsendday;
	}
	public String getSendtime() {
		return sendtime;
	}
	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}
	
}
