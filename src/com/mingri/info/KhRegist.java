package com.mingri.info;

import com.mingri.dbconn.DBResult;
import java.sql.PreparedStatement;

public class KhRegist
{
  private KhinfoBean khinfo;
  DBResult rst = new DBResult();
  
  public void setKhinfo(KhinfoBean khinfo)
  {
    this.khinfo = khinfo;
  }
  
  public void regist()
    throws Exception
  {
    String reg = "insert into tb_customer values(?,?,?,?,?,?,?,?,?,?,?)";
    try
    {
      PreparedStatement pstmt = this.rst.getPreparedStatement(reg);
      
      pstmt.setString(1, this.khinfo.getKhname());
      pstmt.setString(2, this.khinfo.getKhjc());
      pstmt.setString(3, this.khinfo.getAddress());
      pstmt.setString(4, this.khinfo.getPostcode());
      pstmt.setString(5, this.khinfo.getTel());
      pstmt.setString(6, this.khinfo.getFax());
      pstmt.setString(7, this.khinfo.getLxr());
      pstmt.setString(8, this.khinfo.getLxrtel());
      pstmt.setString(9, this.khinfo.getEmail());
      pstmt.setString(10, this.khinfo.getKhyh());
      pstmt.setString(11, this.khinfo.getYhzh());
      
      pstmt.executeUpdate();
    }
    catch (Exception e)
    {
      e.printStackTrace();
      throw e;
    }
  }
}
