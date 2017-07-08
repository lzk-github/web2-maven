package com.kaishengit.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Salary;
import com.kaishengit.entity.SalarySum;
import com.kaishengit.util.DbHelp;

public class SalaryDao {

	public void save(Salary salary) {
		String sql = "insert into t_salary (accid,investid,commission) values (?,?,?)";
		DbHelp.update(sql, salary.getAccid(), salary.getInvestid(), salary.getCommission());
	}

	public void delByInvestId(int investId) {
		String sql = "delete from t_salary where investid=?";
		DbHelp.update(sql, investId);
	}

	public int findSalarySumCountByParams(String key, String value) {
		String sql = "select count(*) from (select sum(commission),accid,DATE_FORMAT(createtime,'%y-%m') from t_salary GROUP BY accid,DATE_FORMAT(createtime,'%y-%m')";
				
		List<Object> list = new ArrayList<>();
		if(StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
			value = "%" + value + "%";
			sql += " having accid in(select id from t_acc where " +key+" like ?)"; 
			list.add(value);
		}
		sql += ") as t_salsum";
		
		return DbHelp.query(sql, new ScalarHandler<Long>(), list.toArray()).intValue();
	}

	public List<SalarySum> findListSalarySumByParams(String key, String value, int start, int pageSize) {
		String sql = "select a.name as accName,a.tel as accTel,m.coss as sumSalary,m.dd as date from (select sum(commission) as coss,accid,DATE_FORMAT(createtime,'%y-%m') as dd from t_salary GROUP BY accid,DATE_FORMAT(createtime,'%y-%m')) as m,t_acc as a where m.accid=a.id ";
		List<Object> list = new ArrayList<>();
		if(StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
			value = "%" + value + "%";
			sql += " and a.id in (select id from t_acc where "+key+" like ?)";
			list.add(value);
		}
		
		sql += " limit ?,?";
		list.add(start);
		list.add(pageSize);
		return DbHelp.query(sql, new BeanListHandler<>(SalarySum.class), list.toArray());
	}

	
}
