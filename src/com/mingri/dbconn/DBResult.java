package com.mingri.dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBResult
{
  public static Connection conn = null;
  private static String user = "sa";
  private static String password = "1qaz@WSX";
  private static String className = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
  private static String url = "jdbc:sqlserver://localhost:1433;DatabaseName=BDMS";
  
  static
  {
    try
    {
      try
      {
        Class.forName(className);
      }
      catch (ClassNotFoundException e)
      {
        e.printStackTrace();
      }
      try
      {
        conn = DriverManager.getConnection(url, user, password);
      }
      catch (SQLException e)
      {
        conn = null;
        e.printStackTrace();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public ResultSet getResult(String sql)
  {
    try
    {
      Statement stmt = conn.createStatement();
      return stmt.executeQuery(sql);
    }
    catch (Exception localException) {
    	localException.printStackTrace();
    }
    return null;
  }
  
  public void doExecute(String sql)
  {
    try
    {
      Statement stmt = conn.createStatement();
      stmt.executeQuery(sql);
    }
    catch (Exception localException) {}
  }
  
  public PreparedStatement getPreparedStatement(String sql)
  {
    try
    {
      return conn.prepareStatement(sql);
    }
    catch (Exception localException) {}
    return null;
  }
}
