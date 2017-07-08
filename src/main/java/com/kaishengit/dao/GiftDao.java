package com.kaishengit.dao;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.kaishengit.entity.Gift;
import com.kaishengit.util.DbHelp;

public class GiftDao {

	public int findCount() {
		String sql = "select count(*) from t_gift";
		return DbHelp.query(sql, new ScalarHandler<Long>()).intValue();
	}

	public List<Gift> findList() {
		String sql = "select * from t_gift";
		return DbHelp.query(sql, new BeanListHandler<>(Gift.class));
	}

}
