package com.kaishengit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.handlers.AbstractListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Acc;
import com.kaishengit.entity.Company;
import com.kaishengit.util.DbHelp;

public class AccDao {

	public int save(Acc acc) {
		String sql = "insert into t_acc (name,password,idNo,tel,company_id,dept,lastLoginTime,lastLoginIp) values (?,?,?,?,?,?,?,?)";
		return DbHelp.update(sql, acc.getName(),acc.getPassword(),acc.getIdNo(),acc.getTel(),acc.getCompanyId(),acc.getDept(),acc.getLastLoginTime(),acc.getLastLoginIp());
		
	}
	public Acc findById(int id) {
		String sql = "select * from t_acc where id=?";
		return DbHelp.query(sql, new BeanHandler<>(Acc.class,new BasicRowProcessor(new GenerousBeanProcessor())), id);
	}
	
	public Acc findByTel(String tel) {
		String sql = "select * from t_acc where tel=?";
		return DbHelp.query(sql, new BeanHandler<>(Acc.class,new BasicRowProcessor(new GenerousBeanProcessor())), tel);
	}
	
	public Acc findByIdNo(String idNo) {
		String sql = "select * from t_acc where idNo=?";
		return DbHelp.query(sql, new BeanHandler<>(Acc.class,new BasicRowProcessor(new GenerousBeanProcessor())), idNo);
	}
	
	public void update(Acc acc) {
		String sql = "update t_acc set name=?,password=?,idNo=?,tel=?,company_id=?,dept=?,lastLoginTime=?,lastLoginIp=? where id=?";
		DbHelp.update(sql, acc.getName(),acc.getPassword(),acc.getIdNo(),acc.getTel(),acc.getCompanyId(),acc.getDept(),acc.getLastLoginTime(),acc.getLastLoginIp(),acc.getId());
	}
	
	public List<Acc> findAll() {
		String sql = "select * from t_acc";
		return DbHelp.query(sql, new BeanListHandler<>(Acc.class,new BasicRowProcessor(new GenerousBeanProcessor())));
	}
	public int findTotalCount() {
		String sql = "select count(*) from t_acc";
		return DbHelp.query(sql, new ScalarHandler<Long>()).intValue();
	}
	public List<Acc> findByPage(int start, int pageSize) {
		String sql = "select * from t_acc limit ?,?";
		return DbHelp.query(sql, new BeanListHandler<>(Acc.class,new BasicRowProcessor(new GenerousBeanProcessor())), start,pageSize);

	}
	public int findCountByParams(String attr, String value) {
		if(StringUtils.isEmpty(attr) || StringUtils.isEmpty(value)) {
			
			String sql = "select count(*) from t_acc";
			return DbHelp.query(sql, new ScalarHandler<Long>()).intValue();
		} else {
			
			String sql = "select count(*) from t_acc where "+attr+"= ?";
			return DbHelp.query(sql, new ScalarHandler<Long>(), value).intValue();
		}
		
	}
	public List<Acc> findByParams(String attr, String value, int start, int pageSize) {
		String sql = "select * from t_acc where "+attr+"=? limit ?,?";
		return DbHelp.query(sql, new BeanListHandler<>(Acc.class,new BasicRowProcessor(new GenerousBeanProcessor())), value,start,pageSize);
	}
	
	public void delById(int id) {
		String sql = "delete from t_acc where id=?";
		DbHelp.update(sql, id);
	}
	public List<Acc> findByMultiParams(String attr, String value, int start, int pageSize) {
		String sql = "select a.*,c.companyName from t_acc a left join t_company c on a.company_id=c.id";
		String where = "";
		List<Object> list = new ArrayList<>();
		attr = (attr == null) ? "" : attr;
		value = (value == null) ? "" : value;
		
		if(StringUtils.isNotEmpty(attr) && StringUtils.isNotEmpty(value)) {
			where += " where " + attr + "=?";
			list.add(value);
		}
		sql += where;
		sql += " limit ?,?";
		list.add(start);
		list.add(pageSize);
	
		List<Acc> temp = DbHelp.query(sql, new AbstractListHandler<Acc>(){
			@Override
			protected Acc handleRow(ResultSet rs) throws SQLException {
				Acc acc = new GenerousBeanProcessor().toBean(rs, Acc.class);
				Company com = new Company();
				com.setCompanyName(rs.getString("companyName"));
				acc.setCompany(com);
				return acc;
			}
			
		}, list.toArray());
		return temp;
	}
	public Acc findByName(String name) {
		String sql = "select * from t_acc where name=?";
				
		return DbHelp.query(sql, new BeanHandler<>(Acc.class,new BasicRowProcessor(new GenerousBeanProcessor())), name);
	}
	public int findCountByCompanyId(int idInAcc) {
		String sql = "select count(*) from t_acc where company_id=?";
		return DbHelp.query(sql, new ScalarHandler<Long>(), idInAcc).intValue();
	}
}
