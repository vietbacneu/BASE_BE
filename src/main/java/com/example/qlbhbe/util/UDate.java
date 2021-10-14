package com.example.qlbhbe.util;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UDate {

  private UDate() {
  }

  static Logger logger = Logger.getLogger(UDate.class);

  public static String format(Date date, String patern) {
    if (date == null) {
      return "";
    }
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patern);

    return simpleDateFormat.format(date);
  }

  public static Date parse(String date, String patern) {
    if (date == null) {
      return null;
    }
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patern);

    try {
      return simpleDateFormat.parse(date);
    } catch (ParseException e) {
      e.printStackTrace();
    }

    return null;
  }

  public static String toLogDateFormat(Date date) {
    if (date == null) {
      return null;
    }
    SimpleDateFormat logDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS");
    return logDateFormat.format(date);
  }

}
