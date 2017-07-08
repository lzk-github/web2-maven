package com.kaishengit.entity;

public class Gift {

	private int id;
	private String name;
	private double price;
	private int points;
	private int nums;
	private String createTime;
	private String remark;
	
	
	@Override
	public String toString() {
		return "Gift [id=" + id + ", name=" + name + ", price=" + price + ", points=" + points + ", nums=" + nums
				+ ", createTime=" + createTime + ", remark=" + remark + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getNums() {
		return nums;
	}
	public void setNums(int nums) {
		this.nums = nums;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
