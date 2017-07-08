package com.kaishengit.test;

import org.junit.Test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;



public class MySQLAdvancedTest {

	@Test
	public void testQuery() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql:///db_23","root","123456");
			String sql = "{call fnin(?)}";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "tom");
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				System.out.println(rs.getObject(1));
				System.out.println(rs.getObject(2));
				System.out.println(rs.getObject(3));
				System.out.println(rs.getObject(4));
				System.out.println(rs.getObject(5));
			}
			rs.close();
			ps.close();
			conn.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testQueryOut() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql:///db_23","root","123456");
			String sql = "{call fnout(?,?)}";
			CallableStatement cs = conn.prepareCall(sql);

			cs.registerOutParameter(1, Types.LONGNVARCHAR);
			cs.registerOutParameter(2, Types.LONGNVARCHAR);
			
			cs.executeUpdate();
			System.out.println(cs.getString(1));
			System.out.println(cs.getString(2));
			
			cs.close();
			conn.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void transactionJDBC() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql:///db_23","root","123456");
			
			conn.setAutoCommit(false);
			String sql1 = "delete from t_b where id=4";
			String sql2 = "delete from t_b where id=6";
			PreparedStatement ps1 = conn.prepareStatement(sql1);
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			
			ps1.executeUpdate();
			ps2.executeUpdate();
			
			conn.commit();
			
			ps1.close();
			ps2.close();
			conn.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
