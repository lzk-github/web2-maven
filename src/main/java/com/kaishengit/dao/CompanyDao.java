package com.kaishengit.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Company;
import com.kaishengit.util.DbHelp;

public class CompanyDao {

	public List<Company> findAll() {
		String sql = "select * from t_company";
		return DbHelp.query(sql, new BeanListHandler<>(Company.class));
	}

	/**
	 * 根据公司名字找到对应公司
	 * @param value
	 * @return
	 */
	public Company findByCompanyName(String value) {
		String sql = "select * from t_company where companyName = ?";
		return DbHelp.query(sql, new BeanHandler<>(Company.class), value);
	}

	public Company findById(int id) {
		String sql = "select * from t_company where id=?";
		return DbHelp.query(sql, new BeanHandler<>(Company.class), id);
	}

	public void delById(int id) {
		String sql = "delete from t_company where id=?";
		DbHelp.update(sql, id);
	}

	public Company findByTel(String tel) {
		String sql = "select * from t_company where companyTel=?";
		return DbHelp.query(sql, new BeanHandler<>(Company.class), tel);
	}

	public void save(Company com) {
		String sql = "insert into t_company (companyName,companyTel,city,controller,remark) values (?,?,?,?,?)";
		DbHelp.update(sql, com.getCompanyName(),com.getCompanyTel(),com.getCity(),com.getController(),com.getRemark());
	}

	public int findByParams(String key, String value) {
		// TODO 根据前端传入的搜索项查找总共有多少条数据,需要提前判断传入的参数内容
		List<String> list = new ArrayList<>();
		String sql = "select count(*) from t_company";
		if(!StringUtils.isEmpty(value)) {
			sql += " where "+key+"=?";
			list.add(value);
			
		}
		return DbHelp.query(sql, new ScalarHandler<Long>(), list.toArray()).intValue();
	}

	/**
	 * 根据attr=value和limit?,?查询company集合
	 * @param key
	 * @param value
	 * @param pageNo
	 * @return
	 */
	public List<Company> findListByParams(String key, String value, int pageNo) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 根据传入的参数查询Company集合
	 * @param key
	 * @param value
	 * @param start
	 * @param pageSize
	 * @return
	 */
	public List<Company> findListByParams(String key, String value, int start, int pageSize) {
		String sql = "select * from t_company";
		List<Object> list = new ArrayList<>();
		if(StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
			list.add(value);
			sql += " where "+key+"=?";
		}
		sql += " limit ?,?";
		list.add(start);
		list.add(pageSize);
		return DbHelp.query(sql, new BeanListHandler<>(Company.class),list.toArray());
	}

	public void update(Company com) {

		String sql = "update t_company set companyName=?,companyTel=?,city=?,controller=?,remark=? where id=?";
		DbHelp.update(sql, com.getCompanyName(),com.getCompanyTel(),com.getCity(),com.getController(),com.getRemark(),com.getId());
	}
}
