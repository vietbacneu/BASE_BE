package com.example.qlbhbe.util;


import com.google.common.base.Strings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import javax.persistence.Query;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class CoreUtils {

  private static Logger logger = LogManager.getLogger("CoreUtils");

  /**
   * Read text in exception_info.property
   *
   * @param key
   * @return
   */
  public String textException(String key) {
    ReloadableResourceBundleMessageSource messageResource = setUtf8("exception_info");
    if (key == null) {
      return "-:-";
    }
    try {
      return messageResource.getMessage(key, null, LocaleContextHolder.getLocale());
    } catch (NoSuchMessageException e) {
      logger.info(e.getMessage(), e);
      return key;
    }
  }

  public static String castDateToStringByPattern(Date date, String pattern) {
    SimpleDateFormat formatter = new SimpleDateFormat(pattern);
    String strDate = formatter.format(date);
    return strDate;
  }

  public static Date castStringToDate(String sDate, String format) {
    try {
      Date date = new SimpleDateFormat(format).parse(sDate);
      return date;
    } catch (ParseException e) {
      logger.info(e.getMessage(),e);
      return null;
    }
  }


  public String castDateToStringByPattern(String date, String patternFrom, String patternTo) throws ParseException {
    SimpleDateFormat formatter = new SimpleDateFormat(patternTo);
    Date dateConvert = new SimpleDateFormat(patternFrom).parse(date);
    String strDate = formatter.format(dateConvert);
    return strDate;
  }

  public static Long castStringDecimal(String decimal, String regex) {
    String[] decimals = decimal.split(regex);
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < decimals.length; i++) {
      stringBuilder.append(decimals[i]);
    }
    try {
      return Long.valueOf(stringBuilder.toString());
    } catch (Exception e) {
      logger.info(e.getMessage(),e);
      return null;
    }
  }

  private ReloadableResourceBundleMessageSource setUtf8(String propertyNameFile) {
    ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();
    messageResource.setBasename("classpath:i18n/".concat(propertyNameFile));
    messageResource.setDefaultEncoding("UTF-8");
    return messageResource;
  }

  public static List<String> cutStr(String str, String regex) {
    String strList[] = str.split(regex);

    if (strList.length > 1) {
      return Arrays.asList(strList);
    }
    return null;
  }

  public static boolean isValidDateFormatAndYearFrom1970ToThisYear(String dateFormat, String value) {
    if (value == null) {
      return false;
    }
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
    sdf.setLenient(false);
    try {
      //if not valid, it will throw ParseException
      sdf.parse(value);
      Date date = sdf.parse(value);
      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
      cal.setTime(date);
      int thisYear = Calendar.getInstance().get(Calendar.YEAR);
      int year = cal.get(Calendar.YEAR);
      if (year < 1970 || year > thisYear) {
        return false;
      }
    } catch (ParseException e) {
      logger.info(e.getMessage(),e);
      return false;
    }
    return true;
  }

  public static boolean isValidateDateFormat(String format, String value, boolean isLenient) {
    if (value == null) {
      return false;
    }
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    sdf.setLenient(isLenient);
    try {
      //if not valid, it will throw ParseException
      sdf.parse(value);
      Date date = sdf.parse(value);
      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
      cal.setTime(date);
      int year = cal.get(Calendar.YEAR);
      if (year < 1970 || year > 9999) {
        return false;
      }
    } catch (ParseException e) {
      logger.info(e.getMessage(),e);
      return false;
    }
    return true;
  }

  public static boolean isValidateYearFormat(String format, String value, boolean isLenient) {
    if (value == null) {
      return false;
    }
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    sdf.setLenient(isLenient);
    try {
      //if not valid, it will throw ParseException
      sdf.parse(value);
      Date date = sdf.parse(value);
      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
      cal.setTime(date);
      int year = cal.get(Calendar.YEAR);
      if (year < 1000 || year > 9999) {
        return false;
      }
    } catch (ParseException e) {
      logger.info(e.getMessage(),e);
      return false;
    }
    return true;
  }

  public static List<String> getListMonthYearFrom1970ToNow() {
    int thisYear = Calendar.getInstance().get(Calendar.YEAR);
    List<String> listMonthYear = new ArrayList<>();
    for (int year = 1970; year <= thisYear; year++) {
      String monthYearFormat = "";
      for (int month = 1; month <= 12; month++) {
        if (month <= 9) {
          monthYearFormat = "0" + month + "/" + year;
        } else {
          monthYearFormat = month + "/" + year;
        }
        listMonthYear.add(monthYearFormat);
      }
    }
    return listMonthYear;
  }

  public static List<String> getListYearFrom1970ToNow() {
    int thisYear = Calendar.getInstance().get(Calendar.YEAR);
    List<String> listYear = new ArrayList<>();
    for (int year = thisYear; year >= 1970; year--) {
      String monthYearFormat = String.valueOf(year);
      listYear.add(monthYearFormat);
    }
    return listYear;
  }

  public static List<Long> convertBigIntegerToLong(List<BigInteger> bigIntegers) {
    List<Long> result = new ArrayList<>();
    for (BigInteger integer : bigIntegers) {
      result.add(integer.longValue());
    }
    return result;
  }

  public static CellStyle buildBorder(Workbook workbook) {
    CellStyle style = workbook.createCellStyle();

    style.setBorderBottom(BorderStyle.THIN);
    style.setBottomBorderColor(IndexedColors.BLACK.getIndex());

    style.setBorderRight(BorderStyle.THIN);
    style.setRightBorderColor(IndexedColors.BLACK.getIndex());

    style.setBorderTop(BorderStyle.THIN);
    style.setTopBorderColor(IndexedColors.BLACK.getIndex());

    style.setBorderLeft(BorderStyle.THIN);
    style.setLeftBorderColor(IndexedColors.BLACK.getIndex());

    style.setWrapText(true);
    style.setVerticalAlignment(VerticalAlignment.CENTER);
    style.setAlignment(HorizontalAlignment.CENTER);

    Font font = workbook.createFont();
    font.setFontHeightInPoints((short) 13);
    font.setFontName("Arial");
    style.setFont(font);
    return style;
  }

  public static void buildStyleLeft(CellStyle style) {
    style.setVerticalAlignment(VerticalAlignment.CENTER);
    style.setAlignment(HorizontalAlignment.LEFT);
  }

  public static boolean isNumeric(String str) {
    try {
      Double.parseDouble(str);
      return true;
    } catch (NumberFormatException e) {
      logger.info(e.getMessage(),e);
      return false;
    }
  }

  public static Query buildParamsForQueryNative(Query query, Map<String, Object> params) {
    if (params.size() > 0) {
      params.keySet().stream().forEach((String key) -> {
        query.setParameter(key, params.get(key));
      });
    }
    return query;
  }


  private static boolean emailStrBeforeAtEmail(String email) {
    String textBeforeAtEmail;
    if (email.contains("@")) {
      textBeforeAtEmail = email.substring(0, email.indexOf('@'));
      return textBeforeAtEmail.length() >= 3;
    } else {
      return false;
    }
  }

  public static void buildMessage(StringBuilder message, String messageOther) {
    if (Strings.isNullOrEmpty(message.toString())) {
      message.append("- ").append(messageOther);
      return;
    }
    message.append("\n").append("- ").append(messageOther);
  }

  public static String distinctValue(String value) {
    StringBuilder result = new StringBuilder();
    List<String> allValue = Arrays.asList(value.split(","));
    List<String> listValuesDistinct = allValue.stream().distinct().collect(Collectors.toList());
    for (String v : listValuesDistinct) {
      if (!Strings.isNullOrEmpty(v)) {
        result.append(",").append(v);
      }
    }
    return result.toString().replace(result.toString(), result.toString() + ",");
  }

  public static void buildDataSheet(List<String> years, Workbook workbook, int cellIndexStart) {
    Row row = null;
    Cell cell = null;
    int rowNumberSheetData = 2;
    int index = 0;
    Sheet sheetData = workbook.getSheetAt(8);
    for (String year : years) {
      rowNumberSheetData++;
      row = sheetData.getRow(rowNumberSheetData);
      if (Objects.isNull(row)) {
        row = sheetData.createRow(rowNumberSheetData);
      }
      // STT
      cell = row.createCell(cellIndexStart, CellType.STRING);
      CellStyle style = buildBorder(workbook);
      buildStyleLeft(style);
      cell.setCellValue(++index);
      cell.setCellStyle(style);
      // năm
      cell = row.createCell(cellIndexStart + 1, CellType.STRING);
      cell.setCellValue(year);
      cell.setCellStyle(style);

    }
  }

  public static String genStr9ByLength(int length) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < length; i++) {
      result.append("9");
    }
    return result.toString();
  }
}
