package com.kaishengit.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.kaishengit.entity.Project;
import com.kaishengit.util.DbHelp;
import com.kaishengit.util.StringUtils;

public class ProjectDao {

	/**
	 * 根据属性和值查询数据行数(条目数)
	 * @param key
	 * @param value
	 * @return
	 */
	public int countByParams(String key, String value) {
		String sql = "select count(*) from t_project ";
		List<String> list = new ArrayList<>();
		if(StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
			list.add(value);
			sql += "where "+key+"=?";
		}
		return DbHelp.query(sql, new ScalarHandler<Long>(), list.toArray()).intValue();
	}

	/**
	 * 根据条件获取Project对象组成的集合
	 * @param key
	 * @param value
	 * @param start
	 * @param pageSize
	 * @return
	 */
	public List<Project> findListByParams(String key, String value, int start, int pageSize) {
		String sql = "select * from t_project";
		List<Object> list = new ArrayList<>();
		if(StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
			list.add(value);
			sql += " where "+key+"=? ";
		}
		sql += " limit ?,?";
		list.add(start);
		list.add(pageSize);
		
		return DbHelp.query(sql, new BeanListHandler<>(Project.class), list.toArray());
	}

	public Project findByName(String name) {
		String sql = "select * from t_project where projectName=?";
		return DbHelp.query(sql, new BeanHandler<>(Project.class), name);
	}

	public void save(Project project) {
		String sql = "insert into t_project (projectName,repayRate,sumMoney,remainMoney,months,startDate,endDate,state,remark) values (?,?,?,?,?,?,?,?,?)";
		DbHelp.update(sql, project.getProjectName(),project.getRepayRate(),project.getSumMoney(),project.getRemainMoney(),project.getMonths(),project.getStartDate(),project.getEndDate(),project.getState(),project.getRemark());
	}

	public List<Project> findNormarl() {
		String sql = "select * from t_project where state=1";
		return DbHelp.query(sql, new BeanListHandler<>(Project.class));
	}

	public Project findById(String projectId) {
		String sql = "select * from t_project where id=?";
		return DbHelp.query(sql, new BeanHandler<>(Project.class), projectId);
	}

	public void update(Project pro) {
		String sql = "update t_project set projectName=?,repayRate=?,sumMoney=?,remainMoney=?,months=?,startDate=?,endDate=?,state=?,remark=? where id=?";
		DbHelp.update(sql, pro.getProjectName(),pro.getRepayRate(),pro.getSumMoney(),pro.getRemainMoney(),pro.getMonths(),pro.getStartDate(),pro.getEndDate(),pro.getState(),pro.getRemark(),pro.getId());
	}


}
