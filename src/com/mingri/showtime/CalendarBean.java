package com.mingri.showtime;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class CalendarBean
{
  Calendar calendar;
  
  public CalendarBean()
  {
    this.calendar = null;
    this.calendar = Calendar.getInstance(TimeZone.getTimeZone("PST"));
    Date date = new Date();
    this.calendar.setTime(date);
  }
  
  public int getYear()
  {
    return this.calendar.get(1);
  }
  
  public String getMonth()
  {
    int i = Integer.parseInt(getMonthInt());
    String[] as = {
      "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", 
      "November", "December" };
    if (i > 12) {
      return "Unknown to Month";
    }
    return as[(i - 1)];
  }
  
  public String getMonthInt()
  {
    String s = "";
    int i = this.calendar.get(2) + 1;
    if (i < 10) {
      return s = "0" + String.valueOf(i);
    }
    return String.valueOf(i);
  }
  
  public String getDay()
  {
    int i = getDayOfWeek();
    String[] as = {
      "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
    if (i > 7) {
      return "Unknown to Day";
    }
    return as[(i - 1)];
  }
  
  public int getDayOfWeek()
  {
    return this.calendar.get(7);
  }
  
  public String getDate()
  {
    return getYear() + "-" + getMonthInt() + "-" + getDayOfMonth();
  }
  
  public String getDayOfMonth()
  {
    String s = "";
    int i = this.calendar.get(5);
    if (i < 10)
    {
      String s1 = "0" + String.valueOf(i);
      return s1;
    }
    return String.valueOf(this.calendar.get(5));
  }
  
  public String getTime()
  {
    return getHour() + ":" + getMinute() + ":" + getSecond();
  }
  
  public int getHour()
  {
    return this.calendar.get(11);
  }
  
  public int getMinute()
  {
    return this.calendar.get(12);
  }
  
  public String getSecond()
  {
    String s = "";
    int i = this.calendar.get(13);
    if (i < 10)
    {
      String s1 = "0" + String.valueOf(i);
      return s1;
    }
    return String.valueOf(i);
  }
  
  public String getDateString()
  {
    return getYear() + getMonthInt() + getDayOfMonth();
  }
}
