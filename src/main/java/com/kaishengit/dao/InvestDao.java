package com.kaishengit.dao;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.kaishengit.entity.Invest;
import com.kaishengit.util.DbHelp;

public class InvestDao {

	public int save(int accId, String custId, String projectId, String investMoney, String repayRate, String months,
			String signDate, String endDate) {
		String sql = "insert into t_invest (projectId,customerId,accId,rate,months,investMoney,signDate,endDate) values (?,?,?,?,?,?,?,?)";
		return DbHelp.insert(sql, new ScalarHandler<Long>(), projectId,custId,accId,repayRate,months,investMoney,signDate,endDate).intValue();
	}

	public int findCountByParams(String key, String value) {
		String sql = "select count(*) from t_invest as i,t_customer as c,t_acc as a,t_project as p where i.projectId=p.id and i.customerId=c.id and i.accId=a.id";
		if(StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
			if(key.equals("custName")) {
				sql += " and c.name=?";
			} else {
				sql = " and p.projectName=?";
			}
			return DbHelp.query(sql, new ScalarHandler<Long>(), value).intValue();
			
		} else {
			return DbHelp.query(sql, new ScalarHandler<Long>()).intValue();
		}
	}

	public List<Invest> findListByParams(String key, String value, int start, int pageSize) {
		String sql = "select i.*,p.projectName as proName,c.name as custName,a.name as accName from t_invest i,t_acc a,t_project p,t_customer c where i.projectId=p.id and i.customerId=c.id and i.accId=a.id";
		List<Object> list = Lists.newArrayList();
		if(StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
			if(key.equals("custName")) {
				sql += " and c.name=?";
			} else {
				sql += " and p.projectName=?";
			}
			list.add(value);
		} 
		
		sql += " limit ?,?";
		list.add(start);
		list.add(pageSize);
		
		
		return DbHelp.query(sql, new BeanListHandler<>(Invest.class), list.toArray());
	}

	public Invest findById(String investId) {
		String sql = "select * from t_invest where id=?";
		return DbHelp.query(sql, new BeanHandler<>(Invest.class), investId);
	}

	public void delById(String investId) {
		String sql = "delete from t_invest where id=?";
		DbHelp.update(sql, investId);
	}

	public void update(Invest iv) {
		String sql = "update t_invest set projectId=?,customerId=?,accId=?,rate=?,months=?,investMoney=?,createTime=?,signDate=?,endDate=?,state=? where id=?";
		DbHelp.update(sql, iv.getProjectId(),iv.getCustomerId(),iv.getAccId(),iv.getRate(),iv.getMonths(),iv.getInvestMoney(),iv.getCreateTime(),iv.getSignDate(),iv.getEndDate(),iv.getState(),iv.getId());
	}
}





