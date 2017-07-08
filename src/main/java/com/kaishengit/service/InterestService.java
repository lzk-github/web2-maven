package com.kaishengit.service;

import java.util.List;

import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.kaishengit.dao.InterestDao;
import com.kaishengit.entity.Interest;
import com.kaishengit.entity.InterestVO;
import com.kaishengit.util.DbHelp;
import com.kaishengit.util.Page;

/**
 * 利息模块逻辑控制
 * @author lzk
 *
 */
public class InterestService {
	
	private InterestDao interestDao = new InterestDao();

	/**
	 * 根据投资信息生成n笔利息流水
	 * @param custId
	 *
	 */
	public void addMultiplyData(String custId, int investId, int accId, String months, String investMoney,
			String repayRate, String signDate, String endDate) {
		Interest interest = new Interest();
		interest.setCustid(Integer.parseInt(custId));
		interest.setInvestid(investId);
		interest.setAccid(accId);
		interest.setState(Interest.INTEREST_BEFORE_TOOKDATE);
		double interestMoney = getInterestMoney(investMoney,repayRate);
		interest.setInterestmoney(interestMoney);
		
		for(int i = 1; i <= Integer.parseInt(months); i++) {
			String interestSendDay = getSendDate(i,signDate);
			interest.setInterestsendday(interestSendDay);
			interestDao.insert(interest);
		}
		
	}

	private static String getSendDate(int i, String signDate) {
		String result = null;
		
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
		DateTime jodaTime = DateTime.parse(signDate);
		result = jodaTime.plusMonths(i).toString(formatter);
		
		return result;
	}

	
	private double getInterestMoney(String investMoney, String repayRate) {
		
		return Double.parseDouble(investMoney) * Double.parseDouble(repayRate);
	}

	public int findCountByParams(String investId, int state) {
		String sql = "select count(*) from t_interest where investid=? and state=?";
		return DbHelp.query(sql, new ScalarHandler<Long>(), investId,state).intValue();
	}

	public void delByInvestId(String investId) {
		String sql = "delete from t_interest where investid=?";
		DbHelp.update(sql, investId);
		
	}

	public Page<InterestVO> findListByParams(String p, String key, String value) {
		int pageNo = 1;
		if(StringUtils.isNumeric(p)) {
			pageNo = Integer.parseInt(p);
		}
		
		int count = interestDao.findCountByParams(key,value);
		Page<InterestVO> pageObj = new Page<>(pageNo,count);
		int start = pageObj.getStart();
		int pageSize = pageObj.getPageSize();
		List<InterestVO> items = interestDao.findListByParams(key,value,start,pageSize);
		pageObj.setItems(items);
		
		return pageObj;
	}

	

}
