package com.mingri.info;

import com.mingri.dbconn.DBResult;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestString
{
  private int curPage;
  private int maxPage;
  private int maxRowCount;
  private int pageSize = 2;
  private DBResult db;
  private String httpFile;
  private String cif;
  private String ccif;
  private String qvalue;
  private String countSql = null;
  private String nowPage = null;
  private String str_parameter;
  private String andor;
  private String sdate;
  private String edate;
  private String orderby;
  private String paixu;
  private String depid;
  private String grpname;
  private String username;
  private String area;
  private String prd;
  private String num;
  private float ATT;
  private float stan;
  
  public QuestString()
  {
    this.pageSize = 2;
    this.countSql = null;
    this.nowPage = null;
    this.db = new DBResult();
  }
  
  public int getCurPage()
  {
    return this.curPage;
  }
  
  public void setCurPage(int i)
  {
    this.curPage = i;
  }
  
  public int getMaxPage()
  {
    return this.maxPage;
  }
  
  public void setMaxPage(int i)
  {
    this.maxPage = i;
  }
  
  public int getMaxRowCount()
  {
    return this.maxRowCount;
  }
  
  public void setMaxRowCount(int i)
  {
    this.maxRowCount = i;
  }
  
  public int getPageSize()
  {
    return this.pageSize;
  }
  
  public void setPageSize(int i)
  {
    this.pageSize = i;
  }
  
  public String getHttpFile()
  {
    return this.httpFile;
  }
  
  public void setHttpFile(String s)
  {
    this.httpFile = s;
  }
  
  public String getCcif()
  {
    return this.ccif;
  }
  
  public void setCcif(String s)
  {
    this.ccif = s;
  }
  
  public String getCif()
  {
    return this.cif;
  }
  
  public void setCif(String s)
  {
    this.cif = s;
  }
  
  public String getQValue()
  {
    return this.qvalue;
  }
  
  public void setQValue(String s)
  {
    this.qvalue = s;
  }
  
  public String getStr_parameter()
  {
    return this.str_parameter;
  }
  
  public void setStr_parameter(String s)
  {
    this.str_parameter = s;
  }
  
  public String getAndor()
  {
    return this.andor;
  }
  
  public void setAndor(String s)
  {
    this.andor = s;
  }
  
  public String getSdate()
  {
    return this.sdate;
  }
  
  public void setSdate(String s)
  {
    this.sdate = s;
  }
  
  public String getEdate()
  {
    return this.edate;
  }
  
  public void setEdate(String s)
  {
    this.edate = s;
  }
  
  public String getOrderby()
  {
    return this.orderby;
  }
  
  public void setOrderby(String s)
  {
    this.orderby = s;
  }
  
  public String getPaixu()
  {
    return this.paixu;
  }
  
  public void setPaixu(String s)
  {
    this.paixu = s;
  }
  
  public String getdepid()
  {
    return this.depid;
  }
  
  public void setdepid(String s)
  {
    this.depid = s;
  }
  
  public String getgrpname()
  {
    return this.grpname;
  }
  
  public void setgrpname(String s)
  {
    this.grpname = s;
  }
  
  public String getusername()
  {
    return this.username;
  }
  
  public void setusername(String s)
  {
    this.username = s;
  }
  
  public String getarea()
  {
    return this.area;
  }
  
  public void setarea(String area)
  {
    this.area = area;
  }
  
  public String getprd()
  {
    return this.prd;
  }
  
  public void setprd(String prd)
  {
    this.prd = prd;
  }
  
  public String getNum()
  {
    return this.num;
  }
  
  public void setNum(String num)
  {
    this.num = num;
  }
  
  public float getATT()
  {
    return this.ATT;
  }
  
  public void setATT(float ATT)
  {
    this.ATT = ATT;
  }
  
  public float getstan()
  {
    return this.stan;
  }
  
  public void setstan(float stan)
  {
    this.stan = stan;
  }
  
  public void setQuerySql(String s, String s1, String s2)
  {
    this.nowPage = s1;
    this.httpFile = s;
    this.countSql = s2;
    try
    {
      querySql(this.countSql);
    }
    catch (Exception exception)
    {
      exception.printStackTrace();
    }
  }
  
  public void querySql(String s)
    throws SQLException
  {
    if ((this.nowPage == null) || ("".equals(this.nowPage)))
    {
      this.curPage = 1;
    }
    else
    {
      this.curPage = Integer.parseInt(this.nowPage);
      if (this.curPage < 1) {
        this.curPage = 1;
      }
    }
    ResultSet resultset = this.db.getResult(s);
    if (resultset.next()) {
      this.maxRowCount = resultset.getInt(1);
    }
    this.maxPage = (this.maxRowCount % this.pageSize != 0 ? this.maxRowCount / this.pageSize + 1 : this.maxRowCount / this.pageSize);
    if (this.curPage > this.maxPage) {
      this.curPage = this.maxPage;
    }
    resultset.close();
  }
  
  public String pageFooter()
  {
    String s = "<form action=" + this.httpFile + " name=formBean methord=post>";
    int i = this.curPage - 1;
    int j = this.curPage + 1;
    s = s + "<font style='font-size: 10pt'>閹槒顓�<font color='red'>" + getMaxRowCount() + "</font>閺壜ゎ唶瑜帮拷," + 
      "閵嗘劕鍙�<font  color='red'>" + getMaxPage() + "</font>妞ょ偣锟斤拷";
    s = s + "閵嗭拷 <font color='red'>" + this.pageSize + "</font>閺夛拷/妞ょ偣锟斤拷 瑜版挸澧犵粭锟�<font color='red'>" + getCurPage() + 
      "</font>妞わ拷 &nbsp; ";
    if (this.curPage > 1) {
      s = s + " <A href=" + this.httpFile + "?pages=1" + this.str_parameter + ">妫ｆ牠銆�</A> ";
    } else {
      s = s + " 妫ｆ牠銆� ";
    }
    if (this.curPage > 1) {
      s = s + " <A href=" + this.httpFile + "?pages=" + i + this.str_parameter + ">娑撳﹣绔存い锟�</A> ";
    } else {
      s = s + " 娑撳﹣绔存い锟� ";
    }
    if (this.curPage < this.maxPage) {
      s = s + " <A href=" + this.httpFile + "?pages=" + j + this.str_parameter + ">娑撳绔存い锟�</A> ";
    } else {
      s = s + " 娑撳绔存い锟� ";
    }
    if ((this.maxPage > 1) && (this.curPage != this.maxPage)) {
      s = s + " <A href=" + this.httpFile + "?pages=" + this.maxPage + this.str_parameter + ">鐏忛箖銆�</A>";
    } else {
      s = s + " 鐏忛箖銆�</font>";
    }
    s = 
    
      s + "鏉烆剙鍩�<input type ='text' name='pages' size='2'>妞わ拷" + "<input type='hidden' name='ccif' value='" + this.ccif + "'><input type ='hidden' name='cif' value='" + this.cif + "'><input type ='hidden' name='qvalue' value='" + this.qvalue + "'><input type ='hidden' name='andor' value='" + this.andor + "'><input type ='hidden' name='sdate' value='" + this.sdate + "'><input type ='hidden' name='edate' value='" + this.edate + "'><input type ='hidden' name='orderby' value='" + this.orderby + "'><input type ='hidden' name='paixu' value='" + this.paixu + "'><input type='submit' name='sumbmit' value='go'></form>";
    return s;
  }
  
  public String getString(String s)
  {
    if (this.ccif.equals("="))
    {
      String s1 = "select top " + this.pageSize * this.curPage + " * from " + s + " where" + " " + this.cif + "=" + "'" + this.qvalue + 
        "'";
      return s1;
    }
    if (this.ccif.equals("LIKE"))
    {
      String s2 = "select top " + this.pageSize * this.curPage + " * from " + s + " where" + " " + this.cif + " " + "like" + " " + 
        "'%" + this.qvalue + "%'";
      return s2;
    }
    if (this.ccif.equals("ALL"))
    {
      String s3 = "select top " + this.pageSize * this.curPage + " * from " + s;
      return s3;
    }
    if (this.ccif.equals("<"))
    {
      String s4 = "select top " + this.pageSize * this.curPage + " * from " + s + " where " + this.cif + " < '" + this.qvalue + "'";
      return s4;
    }
    return null;
  }
  
  public String getXSString(String s)
  {
    if (this.ccif.equals("="))
    {
      String s1 = "select top " + this.pageSize * this.curPage + " * from " + s + " where" + " " + this.cif + "=" + "'" + this.qvalue + 
        "' and whether='閸氾拷'";
      return s1;
    }
    if (this.ccif.equals("LIKE"))
    {
      String s2 = "select top " + this.pageSize * this.curPage + " * from " + s + " where" + " " + this.cif + " " + "like" + " " + 
        "'%" + this.qvalue + "%'and whether='閸氾拷'";
      return s2;
    }
    return null;
  }
  
  public String getCount(String s)
  {
    if (this.ccif.equals("="))
    {
      String s1 = "select count(*) from " + s + " where" + " " + this.cif + "=" + "'" + this.qvalue + "'";
      return s1;
    }
    if (this.ccif.equals("LIKE"))
    {
      String s2 = "select count(*) from " + s + " where" + " " + this.cif + " " + "like" + " " + "'%" + this.qvalue + "%'";
      return s2;
    }
    if (this.ccif.equals("ALL"))
    {
      String s3 = "select count(*) from " + s;
      return s3;
    }
    if (this.ccif.equals("<"))
    {
      String s4 = "select count(*) from " + s + " where " + this.cif + " < '" + this.qvalue + "'";
      return s4;
    }
    return null;
  }
  
  public String getXSCount(String s)
  {
    if (this.ccif.equals("="))
    {
      String s1 = "select count(*) from " + s + " where" + " " + this.cif + "=" + "'" + this.qvalue + "' and whether='閸氾拷'";
      return s1;
    }
    if (this.ccif.equals("LIKE"))
    {
      String s2 = "select count(*) from " + s + " where" + " " + this.cif + " " + "like" + " " + "'%" + this.qvalue + 
        "%' and whether='閸氾拷'";
      return s2;
    }
    return null;
  }
  
  public String getDateCount(String s, String s1)
  {
    if (this.ccif.equals("="))
    {
      String s2 = "select count(*) from " + s + " where " + this.cif + " = '" + this.qvalue + "'" + this.andor + " " + s1 + 
        " between '" + this.sdate + "' and '" + this.edate + "'";
      return s2;
    }
    if (this.ccif.equals("LIKE"))
    {
      String s3 = "select count(*) from " + s + " where " + this.cif + " like '%" + this.qvalue + "%'" + this.andor + " " + s1 + 
        " between '" + this.sdate + "' and '" + this.edate + "'";
      return s3;
    }
    if (this.ccif.equals("ALL"))
    {
      String s4 = "select count(*) from " + s;
      return s4;
    }
    return null;
  }
  
  public String getDateString(String s, String s1)
  {
    if (this.ccif.equals("="))
    {
      String s2 = "select top " + this.pageSize * this.curPage + " * from " + s + " where " + this.cif + " = '" + this.qvalue + "'" + 
        this.andor + " " + s1 + " between '" + this.sdate + "' and '" + this.edate + "'";
      return s2;
    }
    if (this.ccif.equals("LIKE"))
    {
      String s3 = "select top " + this.pageSize * this.curPage + " * from " + s + " where " + this.cif + " like '%" + this.qvalue + 
        "%'" + this.andor + " " + s1 + " between '" + this.sdate + "' and '" + this.edate + "'";
      return s3;
    }
    if (this.ccif.equals("ALL"))
    {
      String s4 = "select top " + this.pageSize * this.curPage + " * from " + s;
      return s4;
    }
    return null;
  }
  
  public String getOrderCount(String s, String s1)
  {
    String s2 = "select count(*) from (select spid from " + s + " where " + s1 + " between '" + this.sdate + "' and '" + 
      this.edate + "' group by spid) as aa";
    return s2;
  }
  
  public String getOrderString(String s, String s1)
  {
    String s2 = "select top " + this.pageSize * this.curPage + 
      "* from tb_brand a inner join (select spid,sum(sl) as sl,sum(je) as je" + " from " + s + " where " + 
      s1 + " between '" + this.sdate + "' and '" + this.edate + "' group by spid ) as b" + " on a.id=b.spid order by " + 
      this.orderby + " " + this.paixu;
    return s2;
  }
  
  public String getgrpsumString(String s, String s1, String s2)
  {
    if ((s1 != null) && (!s1.equals("")) && ((s2 == null) || (s2.isEmpty())))
    {
      String s3 = "select grp,dep,sum(AMT) as sumAMT,sum(weixin) as sumweixin,sum(app) as sumapp,sum(AMT)/(AVG(renshu)/0.7),sum(weixin)/(AVG(renshu)/0.7), sum(app)/(AVG(renshu)/0.7) from " + s + " where date between '" + this.sdate + "' and '" + this.edate + 
        "' and dep='" + s1 + "'group by grp,dep order by sumAMT desc";
      return s3;
    }
    if ((s2 != null) && (!s2.equals("")))
    {
      String s3 = "select grp,dep,sum(AMT) as sumAMT,sum(weixin) as sumweixin,sum(app) as sumapp,sum(AMT)/(AVG(renshu)/0.7),sum(weixin)/(AVG(renshu)/0.7), sum(app)/(AVG(renshu)/0.7) from " + s + " where date between '" + this.sdate + "' and '" + this.edate + 
        "' and grp='" + s2 + "'group by grp,dep order by sumAMT desc";
      return s3;
    }
    return null;
  }
  
  public String getgrpeffString(String s, String s1, String s2)
  {
    if ((s1 != null) && (!s1.equals("")) && ((s2 == null) || (s2.isEmpty())))
    {
      String s3 = "select grp,dep,sum(IB)as sumib,round(AVG(ATT),3) as avgatt,round(AVG(AAH),3) as avgaah,round(AVG(LT),3) as avglt ,round(AVG(dr),3) as avgdr ,round(AVG(TW),3) as avgtw from " + s + 
        " where date between '" + this.sdate + "' and '" + this.edate + "' and dep='" + s1 + "' group by grp,dep order by avgatt asc";
      return s3;
    }
    if ((s2 != null) && (!s2.equals("")))
    {
      String s3 = "select grp,dep,sum(IB)as sumib,round(AVG(ATT),3) as avgatt,round(AVG(AAH),3) as avgaah,round(AVG(LT),3) as avglt ,round(AVG(dr),3) as avgdr ,round(AVG(TW),3) as avgtw from " + s + 
        " where date between '" + this.sdate + "' and '" + this.edate + "' and grp='" + s2 + "' group by grp,dep order by avgatt asc";
      return s3;
    }
    return null;
  }
  
  public String getgrpqaString(String s, String s1, String s2)
  {
    if ((s1 != null) && (!s1.equals("")) && ((s2 == null) || (s2.isEmpty())))
    {
      String s3 = "select grp,dep,sum(qn)as sumqn,sum(suresub) as sumsuresub,sum(sureplus) as sumsureplus,sum(plansub) as sumplansub,sum(planplus) as sumplanplus,sum(profit) as sumprofit,round(avg(avgqa),3) as avgqa2,sum(sumqa) as sumqa2,sum(basicqa) as sumbasicqa,round(avg(stan),4) as avgstan from " + s + 
        " where date between '" + this.sdate + "' and '" + this.edate + "' and dep='" + s1 + "' group by grp,dep order by avgqa2 desc";
      return s3;
    }
    if ((s2 != null) && (!s2.equals("")))
    {
      String s3 = "select grp,dep,sum(qn)as sumqn,sum(suresub) as sumsuresub,sum(sureplus) as sumsureplus,sum(plansub) as sumplansub,sum(planplus) as sumplanplus,sum(profit) as sumprofit,round(avg(avgqa),3) as avgqa2,sum(sumqa) as sumqa2,sum(basicqa) as sumbasicqa,round(avg(stan),4) as avgstan from " + s + 
        " where date between '" + this.sdate + "' and '" + this.edate + "' and grp='" + s2 + "' group by grp,dep order by avgqa2 desc";
      return s3;
    }
    return null;
  }
  
  public String getpersumString(String s, String s1, String s2)
  {
    if ((s1 != null) && (!s1.equals("")) && ((s2 == null) || (s2.isEmpty())))
    {
      String s3 = "select username,name,grp,dep,sum(AMT) as sumAMT,sum(weixin) as sumweixin,sum(app) as sumapp from " + s + " where date between '" + this.sdate + "' and '" + this.edate + 
        "'and grp='" + s1 + "'group by username,name,grp,dep order by sumAMT desc";
      return s3;
    }
    if ((s2 != null) && (!s2.equals("")))
    {
      String s3 = "select username,name,grp,dep,sum(AMT) as sumAMT,sum(weixin) as sumweixin,sum(app) as sumapp from " + s + " where date between '" + this.sdate + "' and '" + this.edate + 
        "' and username='" + s2 + "'group by username,name,grp,dep order by sumAMT desc";
      return s3;
    }
    if (((s1 == null) || (s1.isEmpty())) && ((s2 == null) || (s2.isEmpty())))
    {
      String s3 = "select username,name,grp,dep,sum(AMT) as sumAMT,sum(weixin) as sumweixin,sum(app) as sumapp from " + s + " where date between '" + this.sdate + "' and '" + this.edate + 
        "'group by username,name,grp,dep order by sumAMT desc";
      return s3;
    }
    return null;
  }
  
  public String getpereffString(String s, String s1, String s2)
  {
    if ((s1 != null) && (!s1.equals("")) && ((s2 == null) || (s2.isEmpty())))
    {
      String s3 = "select username,name,grp,dep,sum(IB)as sumib,round(AVG(ATT),3) as avgatt,round(AVG(AAH),3) as avgaah,round(AVG(LT),3) as avglt ,round(AVG(dr),3) as avgdr ,round(AVG(TW),3) as avgtw from " + s + 
        " where date between '" + this.sdate + "' and '" + this.edate + "'and grp='" + s1 + "'group by username,name,grp,dep order by avgatt asc";
      return s3;
    }
    if ((s2 != null) && (!s2.equals("")))
    {
      String s3 = "select username,name,grp,dep,sum(IB)as sumib,round(AVG(ATT),3) as avgatt,round(AVG(AAH),3) as avgaah,round(AVG(LT),3) as avglt ,round(AVG(dr),3) as avgdr ,round(AVG(TW),3) as avgtw from " + s + 
        " where date between '" + this.sdate + "' and '" + this.edate + "' and username='" + s2 + "'group by username,name,grp,dep order by avgatt asc";
      return s3;
    }
    if (((s1 == null) || (s1.isEmpty())) && ((s2 == null) || (s2.isEmpty())))
    {
      String s3 = "select username,name,grp,dep,sum(IB)as sumib,round(AVG(ATT),3) as avgatt,round(AVG(AAH),3) as avgaah,round(AVG(LT),3) as avglt ,round(AVG(dr),3) as avgdr ,round(AVG(TW),3) as avgtw from " + s + 
        " where date between '" + this.sdate + "' and '" + this.edate + "'group by username,name,grp,dep order by avgatt asc";
      return s3;
    }
    return null;
  }
  
  public String getperqaString(String s, String s1, String s2)
  {
    if ((s1 != null) && (!s1.equals("")) && ((s2 == null) || (s2.isEmpty())))
    {
      String s3 = "select username,name,grp,dep,sum(qn)as sumqn,sum(suresub) as sumsuresub,sum(sureplus) as sumsureplus,sum(plansub) as sumplansub,sum(planplus) as sumplanplus,sum(profit) as sumprofit,round(avg(avgqa),3) as avgqa2,sum(sumqa) as sumqa2,sum(basicqa) as sumbasicqa,round(avg(stan),4) as avgstan from " + s + 
        " where date between '" + this.sdate + "' and '" + this.edate + "'and grp='" + s1 + "'group by username,name,grp,dep order by avgqa2 desc";
      return s3;
    }
    if ((s2 != null) && (!s2.equals("")))
    {
      String s3 = "select username,name,grp,dep,sum(qn)as sumqn,sum(suresub) as sumsuresub,sum(sureplus) as sumsureplus,sum(plansub) as sumplansub,sum(planplus) as sumplanplus,sum(profit) as sumprofit,round(avg(avgqa),3) as avgqa2,sum(sumqa) as sumqa2,sum(basicqa) as sumbasicqa,round(avg(stan),4) as avgstan from " + s + 
        " where date between '" + this.sdate + "' and '" + this.edate + "' and username='" + s2 + "'group by username,name,grp,dep order by avgqa2 desc";
      return s3;
    }
    if (((s1 == null) || (s1.isEmpty())) && ((s2 == null) || (s2.isEmpty())))
    {
      String s3 = "select username,name,grp,dep,sum(qn)as sumqn,sum(suresub) as sumsuresub,sum(sureplus) as sumsureplus,sum(plansub) as sumplansub,sum(planplus) as sumplanplus,sum(profit) as sumprofit,round(avg(avgqa),3) as avgqa2,sum(sumqa) as sumqa2,sum(basicqa) as sumbasicqa,round(avg(stan),4) as avgstan from " + s + 
        " where date between '" + this.sdate + "' and '" + this.edate + "'group by username,name,grp,dep order by avgqa2 desc";
      return s3;
    }
    return null;
  }
  
  public String getperString(String s, String s1, String s2)
  {
    if ((s1 != null) && (!s1.equals("")) && ((s2 == null) || (s2.isEmpty())))
    {
      String s3 = "select * from " + s + " where date between '" + this.sdate + "' and '" + this.edate + 
        "' and grp='" + s1 + "'order by date desc";
      return s3;
    }
    if ((s2 != null) && (!s2.equals("")))
    {
      String s3 = "select * from " + s + " where date between '" + this.sdate + "' and '" + this.edate + 
        "' and username='" + s2 + "'order by date desc";
      return s3;
    }
    if (((s1 == null) || (s1.isEmpty())) && ((s2 == null) || (s2.isEmpty())))
    {
      String s3 = "select * from " + s + " where date between '" + this.sdate + "' and '" + this.edate + 
        "'order by date desc";
      return s3;
    }
    return null;
  }
  
  public String getper1String(String s, String s1, String s2)
  {
    if ((s1 != null) && (!s1.equals("")) && ((s2 == null) || (s2.isEmpty())))
    {
      String s3 = "select * from " + s + " where back between '" + this.sdate + "' and '" + this.edate + 
        "' and grp='" + s1 + "'order by back desc";
      return s3;
    }
    if ((s2 != null) && (!s2.equals("")))
    {
      String s3 = "select * from " + s + " where back between '" + this.sdate + "' and '" + this.edate + 
        "' and username='" + s2 + "'order by back desc";
      return s3;
    }
    if (((s1 == null) || (s1.isEmpty())) && ((s2 == null) || (s2.isEmpty())))
    {
      String s3 = "select * from " + s + " where back between '" + this.sdate + "' and '" + this.edate + 
        "'order by back desc";
      return s3;
    }
    return null;
  }
  
  public String getper2String(String s, String s1, String s2, String s3)
  {
    if ((s1 != null) && (!s1.equals("")))
    {
      String s4 = "select * from " + s + " where dep= '" + s1 + "' order by username";
      return s4;
    }
    if ((s2 != null) && (!s2.equals("")))
    {
      String s4 = "select * from " + s + " where grp= '" + s2 + "' order by username";
      return s4;
    }
    if ((s3 != null) && (!s3.equals("")))
    {
      String s4 = "select * from " + s + " where username= '" + s3 + "' order by username";
      return s4;
    }
    if (((s1 == null) || (s1.isEmpty())) && ((s2 == null) || (s2.isEmpty())) && ((s3 == null) || (s3.isEmpty())))
    {
      String s4 = "select * from " + s + " order by username ";
      return s4;
    }
    return null;
  }
  
  public String getchart(String s)
  {
    if ((s != null) && (!s.equals("")))
    {
      String s1 = "select * from " + s + " where date between '" + this.sdate + "' and '" + this.edate + 
        " ' ";
      return s1;
    }
    return null;
  }
}
