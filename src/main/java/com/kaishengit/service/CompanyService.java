package com.kaishengit.service;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.dao.AccDao;
import com.kaishengit.dao.CompanyDao;
import com.kaishengit.entity.Company;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.Config;
import com.kaishengit.util.Page;

public class CompanyService {

	private CompanyDao cd = new CompanyDao();
	private AccDao ad = new AccDao();
	public List<Company> findAll() {
		
		return cd.findAll();
	}
	public List<String> findAllDept() {
		String[] depts = Config.get("dept.name.list").split("-");
		return Arrays.asList(depts);
	}
	public void delById(String id) {
		if(StringUtils.isNumeric(id)) {
			Company com = cd.findById(Integer.parseInt(id));
			if(com != null) {
				
				int idInAcc = Integer.parseInt(id);
				int count = ad.findCountByCompanyId(idInAcc);
				if(count == 0) {
					cd.delById(Integer.parseInt(id));
				} else {
					throw new ServiceException("该公司旗下还有员工,删除失败!");
				}
				
				
				
			} else {
				throw new ServiceException("未找到该条公司数据");
			}
		} else {
			throw new ServiceException("传入参数异常!");
		}
		
	}
	public List<String> findAllCity() {
		String cities = Config.get("company.city");
		if(cities != null) {
			return Arrays.asList(cities.split("-"));
		} else {
			return null;
		}
	}
	public Boolean checkByTel(String tel) {
		if(StringUtils.isEmpty(tel)) {
			return true;
		}
		Company com = cd.findByTel(tel);
		if(com == null) {
			return true;
		}
		
		return false;
	}
	public void save(String companyName, String companyTel, String city, String controller, String remark) {
		Company com = cd.findByCompanyName(companyName);
		if(com == null) {
			com = new Company();
			com.setCompanyName(companyName);
			com.setCompanyTel(companyTel);
			com.setCity(city);
			com.setController(controller);
			com.setRemark(remark);
			cd.save(com);
		} else {
			throw new ServiceException("该公司已存在");
		}
												
	}
	public Page<Company> findByparams(int pageNo, String key, String value) {
		//目的:为了获取Page<Company>集合,需要获取start和pageSize,可以根据page和Total
		//获取Page对象,那么首先获取总数据数目
		int count = cd.findByParams(key,value);
		
		Page<Company> pageList = new Page<>(pageNo,count);
		int start = pageList.getStart();
		int pageSize = pageList.getPageSize();
		//得到了page对象,还需要给page对象塞值items,是一个List<Company>集合,获取list集合吧
		List<Company> comList = cd.findListByParams(key,value,start,pageSize);
		pageList.setItems(comList);
		
		return pageList;
	}
	public Company editById(int id) {
		Company com = cd.findById(id);
		if(com != null) {
			return com;
		} else {
			throw new ServiceException("传入参数异常!");
		}
		
	}
	public void editByParams(String companyName, String tel, String city, String controller, String remark) {
		Company com = cd.findByCompanyName(companyName);
		
		if(com != null) {
			com.setCompanyTel(tel);
			com.setCity(city);
			com.setController(controller);
			com.setRemark(remark);
			
			cd.update(com);
		} else {
			throw new ServiceException("公司不存在!");
		}
	}

	
}
