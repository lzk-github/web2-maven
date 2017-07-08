package com.kaishengit.dao;

import org.apache.commons.dbutils.handlers.BeanHandler;

import com.kaishengit.entity.Settings;
import com.kaishengit.util.DbHelp;

public class SettingsDao {

	public Settings findByName(String string) {
		String sql = "select * from t_settings where `key`=?";
		return DbHelp.query(sql, new BeanHandler<>(Settings.class), string);
	}

	public void update(String key, String value) {
		String sql = "update t_settings set value=? where `key`=?";
		DbHelp.update(sql,value,key);
	}

}
