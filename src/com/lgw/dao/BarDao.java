package com.lgw.dao;

import com.google.gson.Gson;
import com.lgw.bean.Bar;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BarDao {
	
  public String query(String table, String sdate, String edate) {
	    ArrayList<Bar> barArr = new ArrayList<Bar>();
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    try{
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=BDMS", "sa", "1qaz@WSX");
	        stmt = conn.prepareStatement("select * from " + table + " where date between '" + sdate + "' and '" + edate + " ' ");
	        rs = stmt.executeQuery();
	        while (rs.next()){
		        Bar bar = new Bar();
		        bar.setdata(rs.getString("date"));
		        if("effsch".equalsIgnoreCase(table)) {
		        	 bar.setATT(rs.getString("att"));
		             bar.setATT2(rs.getString("att2"));
		        }
		        if("qasch".equalsIgnoreCase(table)) {
		        	bar.setstan(rs.getString("stan"));
		            bar.setStan2(rs.getString("stan2"));
		        }
		        barArr.add(bar);
		    }
	    } catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	    Gson gson = new Gson();
	    String str = gson.toJson(barArr);
	    return str;
  }
}
