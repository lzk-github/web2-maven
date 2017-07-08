package com.kaishengit.util;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DbHelp {

	private static Logger logger = LoggerFactory.getLogger(DbHelp.class);
	/**获取connection连接
	 * @return
	 */
	/*public static Connection getConnection() {
		return ConnectionManager.getConnection();
	}*/
	
	/**
	 * update() 执行insert update delete
	 * @param sql
	 * @param params
	 * @throws SQLException 
	 */
	public static int update(String sql,Object... params) {
		int res = 0;
		QueryRunner runner = new QueryRunner(ConnectionManager.getDataSource());
		
		try {
			res = runner.update(sql, params);
			logger.info("SQL:{},params:{}",sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public static <T> T query(String sql,ResultSetHandler<T> rsh, Object... params) 
			throws RuntimeException{
		QueryRunner runner = new QueryRunner(ConnectionManager.getDataSource());
		T t = null;
		try { 
			t = runner.query(sql, rsh, params);
			logger.info("SQL:{},params:{}",sql,params);
		} catch (SQLException e) {
			throw new RuntimeException("执行" + sql +"异常",e);
		} 
		return t;
	}
	
	public static <T> T insert(String sql,ResultSetHandler<T> rsh,Object... params) {
		QueryRunner runner = new QueryRunner(ConnectionManager.getDataSource());
		T t = null;
		try { 
			t = runner.insert(sql, rsh, params);
			logger.info("(-insert-)SQL:{},params:{}",sql,params);
		} catch (SQLException e) {
			throw new RuntimeException("执行" + sql +"异常",e);
		} 
		return t;
	}
	
	
}
