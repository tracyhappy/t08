package com.lgw.dao;

import com.google.gson.Gson;
import com.lgw.bean.Bar;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BarDao2
{
  public String query(String sql)
  {
    ArrayList<Bar> barArr = new ArrayList();
    try
    {
      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      Connection conn = DriverManager.getConnection(
        "jdbc:sqlserver://localhost:1433;DatabaseName=BDMS", "sa", "1qaz@WSX");
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();
      while (rs.next())
      {
        Bar bar = new Bar();
        bar.setcategory(rs.getString(1));
        bar.setscores(rs.getString(2));
        barArr.add(bar);
      }
      conn.close();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    catch (ClassNotFoundException e)
    {
      e.printStackTrace();
    }
    Gson gson = new Gson();
    String str = gson.toJson(barArr);
    
    return str;
  }
}
