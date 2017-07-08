package com.kaishengit.dao;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.kaishengit.entity.Customer;
import com.kaishengit.util.DbHelp;

public class CustomerDao {

	/**
	 * 查询所有用户列表
	 * @return 用户列表
	 */
	public List<Customer> findAll() {
		String sql = "select * from t_customer";
		return DbHelp.query(sql, new BeanListHandler<>(Customer.class));
	}

	public Customer findById(String custId) {
		String sql = "select * from t_customer where id=?";
		return DbHelp.query(sql, new BeanHandler<>(Customer.class), custId);
	}

	public void update(Customer cust) {
		String sql = "update t_customer set name=?,tel=?,idNo=?,bankName=?,bankNo=?,birthday=?,address=?,point=?,remark=? where id=?";
		DbHelp.update(sql, cust.getName(),cust.getTel(),cust.getIdNo(),cust.getBankName(),cust.getBankNo(),cust.getBirthday(),cust.getAddress(),cust.getPoint(),cust.getRemark(),cust.getId());
	}

	public int findCountBydays(String days) {
		String sql = "select count(*) from t_customer where (DAYOFYEAR(birthday)-DAYOFYEAR(now()) BETWEEN 0 and ?) "
				+" or (DAYOFYEAR(birthday)+DAYOFYEAR(CONCAT(YEAR(now()),'-12-31'))-DAYOFYEAR(now()) BETWEEN 0 AND ?)";
		return DbHelp.query(sql, new ScalarHandler<Long>(), days,days).intValue();
	}

	public List<Customer> findByParams(String days, int start, int pageSize) {
		String sql = "select * from t_customer where (DAYOFYEAR(birthday)-DAYOFYEAR(now()) BETWEEN 0 and ?) "
				+" or (DAYOFYEAR(birthday)+DAYOFYEAR(CONCAT(YEAR(now()),'-12-31'))-DAYOFYEAR(now()) BETWEEN 0 AND ?) limit ?,?";
		return DbHelp.query(sql, new BeanListHandler<>(Customer.class), days,days,start,pageSize);
	}

	public List<Customer> findBypage(int start, int pageSize) {
		String sql = "select * from t_customer limit ?,?";
		return DbHelp.query(sql, new BeanListHandler<>(Customer.class), start,pageSize);
	}

	public int findCountByAttr(String attr, String value) {
		String sql = "select * from t_customer where " + attr + "=?";
		
		return DbHelp.query(sql, new ScalarHandler<Long>(), value).intValue();
	}

	public List<Customer> findByPageAndAttr(String attr, String value, int start, int pageSize) {
		String sql = "select * from t_customer where "+attr+"=? limit ?,?";
		return DbHelp.query(sql, new BeanListHandler<>(Customer.class), value,start,pageSize);
	}

	public int findallCount() {
		String sql = "select count(*) from t_customer";
		return DbHelp.query(sql, new ScalarHandler<Long>()).intValue();
	}

	public Customer findByIdNo(String idNo) {
		String sql = "select * from t_customer where idNo=?";
		return DbHelp.query(sql, new BeanHandler<>(Customer.class), idNo);
	}

	public void save(Customer cus) {
		String sql = "insert into t_customer (name,tel,idNo,bankName,bankNo,birthday,address,point,remark) values (?,?,?,?,?,?,?,?,?)";
		DbHelp.update(sql,cus.getName(),cus.getTel(),cus.getIdNo(),cus.getBankName(),cus.getBankNo(),cus.getBirthday(),cus.getAddress(),cus.getPoint(),cus.getRemark());
	}

}
