package com.kaishengit.entity;

import java.sql.Timestamp;

public class Salary {

	private int id;
	private int accid;
	private int investid;
	private double commission;
	private Timestamp createtime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAccid() {
		return accid;
	}
	public void setAccid(int accid) {
		this.accid = accid;
	}
	public int getInvestid() {
		return investid;
	}
	public void setInvestid(int investid) {
		this.investid = investid;
	}
	public double getCommission() {
		return commission;
	}
	public void setCommission(double commission) {
		this.commission = commission;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
}
