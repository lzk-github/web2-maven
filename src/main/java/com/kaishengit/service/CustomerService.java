package com.kaishengit.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.dao.CustomerDao;
import com.kaishengit.dao.SettingsDao;
import com.kaishengit.entity.Customer;
import com.kaishengit.entity.Settings;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.Page;

public class CustomerService {

	private CustomerDao cd = new CustomerDao();
	private SettingsDao settingsDao = new SettingsDao();
	
	public List<Customer> findAllCustomers() {
		
		return cd.findAll();
	}
	public void updateWithPoint(String custId, String investMoney) {
		Customer cust = cd.findById(custId);
		int point = getPointByInvestMoney(investMoney);
		cust.setPoint(point);
		cd.update(cust);
	}
	private int getPointByInvestMoney(String investMoney) {
		int res = 0;
		Settings settings = settingsDao.findByName("giftPointRate");
		double giftPointRate = Double.parseDouble(settings.getValue());
		res = (int)(giftPointRate * Double.parseDouble(investMoney));
		return res;
	}
	public Page<Customer> findBirthDaysAndPage(String days, String p) {
		int pageNo = 1;
		if(StringUtils.isNumeric(p)) {
			pageNo = Integer.parseInt(p);
		}
		
		int count = cd.findCountBydays(days);
		Page<Customer> pageObj = new Page<>(pageNo,count);
		int start = pageObj.getStart();
		int pageSize = pageObj.getPageSize();
		List<Customer> list = null;
		if(StringUtils.isNotEmpty(days)) {
			list = cd.findByParams(days,start,pageSize);
		} else {
			list = cd.findBypage(start,pageSize);
		}
		pageObj.setItems(list);
		return pageObj;
	}
	public Page<Customer> findByAttrAndPage(String p, String attr, String value) {
		Page<Customer> pageObj = null;
		List<Customer> list = null;
		int count = 0;
		
		int pageNo = 1;
		if(StringUtils.isNumeric(p)) {
			pageNo = Integer.parseInt(p);
		}
		
		
		if(StringUtils.isNotEmpty(attr) && StringUtils.isNotEmpty(value)) {
			count = cd.findCountByAttr(attr, value);
			pageObj = new Page<>(pageNo,count);
			int start = pageObj.getStart();
			int pageSize = pageObj.getPageSize();
			list = cd.findByPageAndAttr(attr,value,start,pageSize);
		} else {
			count = cd.findallCount();
			pageObj = new Page<>(pageNo,count);
			int start = pageObj.getStart();
			int pageSize = pageObj.getPageSize();
			list = cd.findBypage(start, pageSize);
		}
		
		pageObj.setItems(list);
		
		
		return pageObj;
	}
	public void save(String name, String idNo, String tel, String bankNo, String bankName, String birthday,
			String address, String remark) {
		Customer customer = cd.findByIdNo(idNo);
		if(customer != null) {
			throw new ServiceException("身份证号已存在!");
		} else {
			customer = new Customer();
			customer.setName(name);
			customer.setIdNo(idNo);
			customer.setTel(tel);
			customer.setBankName(bankName);
			customer.setBankNo(bankNo);
			customer.setBirthday(birthday);
			customer.setAddress(address);
			customer.setRemark(remark);
			
			cd.save(customer);
		}
		
	}
	public Customer findById(String id) {
		if(StringUtils.isNumeric(id)) {
			return cd.findById(id);
		}
		return null;
	}
	public void edit(String custId, String idNo, String tel, String bankNo, String bankName, String birthday,
			String address, String remark) {
		Customer cust = cd.findById(custId);
		if(cust == null) {
			throw new ServiceException("不存在该customer-id");
		} else {
			cust.setIdNo(idNo);
			cust.setTel(tel);
			cust.setBankNo(bankNo);
			cust.setBankName(bankName);
			cust.setBirthday(birthday);
			cust.setAddress(address);
			cust.setRemark(remark);
			
			cd.update(cust);
		}
		
	}
	
	/**
	 * 根据客户id和投资金额还原客户积分
	 * @param customerId
	 * @param investMoney
	 */
	public void reUpPointByCustIdAndInvestMoney(int customerId, int investMoney) {
		Customer cust = cd.findById(String.valueOf(customerId));
		Settings settings = settingsDao.findByName("giftPointRate");
		String value = settings.getValue();
		double giftPointRate = Double.parseDouble(value);
		int needToReUpPoint = (int) (giftPointRate * investMoney);
		cust.setPoint(cust.getPoint() - needToReUpPoint);
		cd.update(cust);
	}

}





