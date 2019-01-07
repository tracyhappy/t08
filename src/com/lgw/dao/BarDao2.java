package com.lgw.dao;

import com.google.gson.Gson;
import com.lgw.bean.Bar;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BarDao2{
	
  public String query(String sql){
	    ArrayList<Bar> barArr = new ArrayList<Bar>();
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=BDMS", "sa", "1qaz@WSX");
	        stmt = conn.prepareStatement(sql);
	        rs = stmt.executeQuery();
	        while (rs.next())
	        {
		        Bar bar = new Bar();
		        bar.setcategory(rs.getString("category"));
		        bar.setscores(rs.getString("avgscores"));
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
