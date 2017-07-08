package com.kaishengit.service;

import java.util.List;

import com.kaishengit.dao.SalaryDao;
import com.kaishengit.dao.SettingsDao;
import com.kaishengit.entity.Salary;
import com.kaishengit.entity.SalarySum;
import com.kaishengit.entity.Settings;
import com.kaishengit.util.Page;
import com.kaishengit.util.StringUtils;

public class SalaryService {
	
	private SettingsDao settingsDao = new SettingsDao();
	private SalaryDao salaryDao = new SalaryDao();
	
	public void addSalaryData(int accId, int investId, String investMoney) {
		double commission = getCommissionByInvestMoney(investMoney);
		Salary salary = new Salary();
		salary.setAccid(accId);
		salary.setInvestid(investId);
		salary.setCommission(commission);
		salaryDao.save(salary);
	}

	private double getCommissionByInvestMoney(String investMoney) {
		double res = 0D;
		Settings settings = settingsDao.findByName("commission");
		String commissionRate = settings.getValue();
		res = Double.parseDouble(investMoney) * Double.parseDouble(commissionRate);
		
		return res;
	}

	

	public void delByInvestId(int investId) {
		salaryDao.delByInvestId(investId);
		
	}

	public Page<SalarySum> findPageByParams(String p, String key, String value) {
		int pageNo = 1;
		if(StringUtils.isNumeric(p)) {
			pageNo = Integer.parseInt(p);
		}
		
		int count = salaryDao.findSalarySumCountByParams(key,value);
		Page<SalarySum> pageObj = new Page<>(pageNo,count);
		int start = pageObj.getStart();
		int pageSize = pageObj.getPageSize();
		List<SalarySum> items = salaryDao.findListSalarySumByParams(key,value,start,pageSize);
		pageObj.setItems(items);
		return pageObj;
	}

}






