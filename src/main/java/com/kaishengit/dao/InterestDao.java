package com.kaishengit.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.kaishengit.entity.Interest;
import com.kaishengit.entity.InterestVO;
import com.kaishengit.util.DbHelp;
import com.kaishengit.util.StringUtils;

public class InterestDao {

	public void insert(Interest ir) {
		String sql = "insert into t_interest (custid,investid,accid,interestsendday,interestmoney,state) values (?,?,?,?,?,?)";
		DbHelp.update(sql, ir.getCustid(),ir.getInvestid(),ir.getAccid(),ir.getInterestsendday(),ir.getInterestmoney(),ir.getState());
	}

	public void delByInvestIdAndState(String investId, int state) {
		String sql = "delete from t_interest where investid = ? and state = ?";
		DbHelp.update(sql, investId,state);
	}

	public int findCountByParams(String key, String value) {
		String sql = "select count(*) from t_interest";
		List<String> list = new ArrayList<>();
		if(StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
			sql += " as it ,t_customer as cust where it.custid=cust.id and cust."+key+"=?";
			list.add(value);
		}
		
		
		return DbHelp.query(sql, new ScalarHandler<Long>(), list.toArray()).intValue();
	}

	public List<InterestVO> findListByParams(String key, String value, int start, int pageSize) {
		String sql = "SELECT cust.name as custName,tel,investMoney,interestmoney,it.state as state, interestsendday,sendtime from t_interest as it ,t_customer as cust,t_invest as iv WHERE it.custid=cust.id and it.investid=iv.id";
		List<Object> list = new ArrayList<>();
		if(StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
			sql += " and cust."+key+"=?";
			list.add(value);
		}
		sql += " limit ?,?";
		list.add(start);
		list.add(pageSize);
		return DbHelp.query(sql, new BeanListHandler<>(InterestVO.class), list.toArray());
	}

}













