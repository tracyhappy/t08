package com.mingri.info;

import com.mingri.dbconn.DBResult;
import java.sql.PreparedStatement;

public class GysRegist
{
  private GysinfoBean gysinfo;
  DBResult rst = new DBResult();
  
  public void setGysinfo(GysinfoBean gysinfo)
  {
    this.gysinfo = gysinfo;
  }
  
  public void regist()
    throws Exception
  {
    String sql = "insert into tb_supplier values(?,?,?,?,?,?,?,?,?,?)";
    try
    {
      PreparedStatement pstmt = this.rst.getPreparedStatement(sql);
      pstmt.setString(1, this.gysinfo.getGysname());
      pstmt.setString(2, this.gysinfo.getJc());
      pstmt.setString(3, this.gysinfo.getAddress());
      pstmt.setString(4, this.gysinfo.getPostcode());
      pstmt.setString(5, this.gysinfo.getTel());
      pstmt.setString(6, this.gysinfo.getFax());
      pstmt.setString(7, this.gysinfo.getLxr());
      pstmt.setString(8, this.gysinfo.getLxrtel());
      pstmt.setString(9, this.gysinfo.getKhyh());
      pstmt.setString(10, this.gysinfo.getEamil());
      pstmt.executeUpdate();
    }
    catch (Exception e)
    {
      e.printStackTrace();
      throw e;
    }
  }
}
