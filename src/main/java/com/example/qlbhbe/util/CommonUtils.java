package com.example.qlbhbe.util;

import com.google.common.base.Strings;
import com.viettel.pmp.partner.common.core.RespType;
import com.viettel.pmp.partner.common.exception.CustomExceptionHandler;
import net.logstash.logback.encoder.org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommonUtils {

  private static org.apache.logging.log4j.Logger logger = LogManager.getLogger("CommonUtils");

  public static Pageable validatePageable(com.viettel.pmp.partner.common.util.PageCustom pageCustom, Sort sort) {
    int page = 0;
    int size = 0;
    try {
      if (pageCustom.getPage() == null || "".equals(pageCustom.getPage())) {
        page = 0;
      } else page = Integer.parseInt(pageCustom.getPage().trim());

      if (pageCustom.getSize() == null || "".equals(pageCustom.getSize())) {
        size = 20;
      } else size = Integer.parseInt(pageCustom.getSize().trim());

      if (size <= 0 || page < 0) {
        throw new CustomExceptionHandler("Arg Invalid", RespType.BAD_REQUEST);
      }

      return PageRequest.of(page, size, sort);
    } catch (Exception e) {
      Logger log = LoggerFactory.getLogger(CommonUtils.class);
      log.error(e.getMessage(), e);
      throw new CustomExceptionHandler("Arg Invalid", RespType.BAD_REQUEST);
    }

  }


  public static <T> T trimObject(T obj) {
    Logger log = LoggerFactory.getLogger(CommonUtils.class);
    Class objClass = obj.getClass();

    Field[] fieldsDesc = objClass.getDeclaredFields();

    if (fieldsDesc != null) {
      for (Field field : fieldsDesc) {
        try {
          Object objSrc = objClass.getMethod("get" + StringUtils.capitalize(field.getName())).invoke(obj);
          if (objSrc != null && field.getType() == String.class) {
            objClass.getMethod("set" + StringUtils.capitalize(field.getName()), field.getType()).invoke(obj, objSrc.toString().trim());
          }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
          log.error(e.getMessage(), e);
        }
      }
    }
    return obj;
  }


  public static String getLikeCondition(String str) {
    if (str == null || str.trim().isEmpty()) {
      str = "%";

      return str;
    }
    String newStr =
        str.trim()
            .replace("\\", "\\\\")
            .replace("\\t", "\\\\t")
            .replace("\\n", "\\\\n")
            .replace("\\r", "\\\\r")
            .replace("\\z", "\\\\z")
            .replace("\\b", "\\\\b")
            .replaceAll("_", "\\\\_")
            .replaceAll("%", "\\\\%");
    str = "%".concat(newStr.trim()).concat("%");
//        str = stripAccents(str); // dang khong bi
    return str;
  }

  public static String getStrDate(Long time, String format) {
    return new SimpleDateFormat(format).format(new Date(time));
  }

  /**
   * Check list object is null.
   *
   * @param data
   * @return
   */
  public static boolean isNullOrEmpty(List data) {
    return (data == null || data.isEmpty());
  }

  public static boolean isNullOrEmpty(Object[] objects) {
    if (objects == null || objects.length == 0) {
      return true;
    } else {
      for (Object o : objects) {
        if (o != null && o.toString() != null && !"".equals(o.toString().trim())) {
          return false;
        }
      }
      return true;
    }
  }


  public static InputStream getInputStreamByFileName(String fileName) {
    try {


      if (org.apache.commons.lang3.StringUtils.isNotEmpty(fileName) && fileName.contains(".")) {
        return new ClassPathResource("/excel-template/" + fileName).getInputStream();
      } else {
        return null;
      }
    } catch (IOException ioE) {
      logger.info(ioE.getMessage(), ioE);
      return null;
    }
  }

  public static ResponseEntity getResponseFromByte(String fileName, byte[] exportInputStream) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    headers.set("File", fileName);
    headers.set("Content-Disposition", "attachment; filename=" + fileName);
    headers.set("Access-Control-Expose-Headers", "File");
    return ResponseEntity.ok().headers(headers).body(exportInputStream);
  }

  public static String getStringDateTimeReport(Instant instant, String pattern) {
    Date date = Date.from(instant);
    SimpleDateFormat formatter = new SimpleDateFormat(pattern);
    return formatter.format(date);
  }

  public static void exportFileToPreview(String fileName, byte[] exportInputStream) {
    String rpPath = new ClassPathResource("/report_out/xlsx/").getPath();
    File dir = new File(rpPath);
    if (!dir.exists()) {
      dir.mkdirs();
    }

    File file = new File(rpPath + fileName);
    try {
      FileUtils.writeByteArrayToFile(file, exportInputStream);
    } catch (IOException e) {
      logger.info(e.getMessage(), e);
      new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
  }

  /**
   * Check string is null.
   *
   * @param str
   * @return
   */
  public static boolean isNullOrEmpty(String str) {
    return (str == null || str.trim().isEmpty());
  }


  /**
   * get response of file
   *
   * @param fileName * @param exportInputStream
   * @return
   */
  public static ResponseEntity<InputStreamResource> getResponseFile(String fileName, ByteArrayInputStream exportInputStream) {
    HttpHeaders headers = new HttpHeaders();
    headers.add("File", fileName);
    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    headers.add("Content-Disposition", "attachment; filename=" + fileName);
    headers.add("Access-Control-Expose-Headers", "File");
    return ResponseEntity.ok().headers(headers).body(new InputStreamResource(exportInputStream));
  }

  public static String getFileNameReport(String preName) {
    String pattern = "yyyyMMdd_HHmmss";
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
    LocalDateTime now = LocalDateTime.now();
    return preName + "_" + dtf.format(now) + ".xlsx";
  }

  public static String getFileNameReportUpdate(String preName) {
    String pattern = "yyMMdd_HHmmss";
    DateFormat dtf = new SimpleDateFormat(pattern);
    String date = dtf.format(new Date());
    return preName + "_" + date + ".xlsx";
  }

  public static String replaceNumberZeroBeforeDot(String number) {
    if (number.contains(".")) {
      String[] strings = number.split("\\.");
      String numberBeforeDot = strings[0];
      while ("0".equals(String.valueOf(numberBeforeDot.charAt(0)))) {
        numberBeforeDot = numberBeforeDot.replaceFirst("0", "");
      }
      return numberBeforeDot + "." + strings[1];
    } else {
      return number;
    }
  }

  public static boolean isEqualOne(Integer val) {
    if (val == null) return false;
    return val.equals(1);
  }

  /*IIST - LongPC - Create style for common components of excel function*/
  public static CellStyle createTitleStyle(Sheet sheet) {
    Font font = sheet.getWorkbook().createFont();
    font.setFontName(Constants.CALIBRI_FONT);
    font.setFontHeightInPoints((short) 18);
    font.setBold(true);
    font.setColor(IndexedColors.BLACK.getIndex());

    CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
    cellStyle.setFont(font);
    cellStyle.setAlignment(HorizontalAlignment.CENTER);
    return cellStyle;
  }

  public static CellStyle createHeaderStyle(Sheet sheet) {
    Font font = sheet.getWorkbook().createFont();
    font.setFontName(Constants.ARIAL_FONT);
    font.setFontHeightInPoints((short) 10);
    font.setBold(true);
    font.setColor(IndexedColors.BLACK.getIndex());

    CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
    cellStyle.setFont(font);
    cellStyle.setAlignment(HorizontalAlignment.CENTER);
    cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
    cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    cellStyle.setBorderBottom(BorderStyle.THIN);
    cellStyle.setBorderLeft(BorderStyle.THIN);
    cellStyle.setBorderRight(BorderStyle.THIN);
    cellStyle.setBorderTop(BorderStyle.THIN);
    return cellStyle;
  }

  public static List<CellStyle> createDataStyle(Sheet sheet) {
    Font font = sheet.getWorkbook().createFont();
    font.setFontName(Constants.CALIBRI_FONT);
    font.setFontHeightInPoints((short) 10);
    font.setColor(IndexedColors.BLACK.getIndex());

    return new ArrayList<CellStyle>() {{
      add(createCellStyle(sheet, font, HorizontalAlignment.LEFT));
      add(createCellStyle(sheet, font, HorizontalAlignment.CENTER));
      add(createCellStyle(sheet, font, HorizontalAlignment.RIGHT));
    }};
  }

  public static CellStyle createCellStyle(Sheet sheet, Font font, HorizontalAlignment alignment) {
    CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
    cellStyle.setFont(font);
    cellStyle.setAlignment(alignment);
    cellStyle.setWrapText(true);
    cellStyle.setBorderBottom(BorderStyle.THIN);
    cellStyle.setBorderLeft(BorderStyle.THIN);
    cellStyle.setBorderRight(BorderStyle.THIN);
    cellStyle.setBorderTop(BorderStyle.THIN);
    return cellStyle;
  }

  public static String insertDot(String str) {
    if (str.length() < 4) {
      return str;
    }
    return insertDot(str.substring(0, str.length() - 3)) + "." + str.substring(str.length() - 3, str.length());
  }

  public static String formatDouble(String str) {
    DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
    decimalFormatSymbols.setDecimalSeparator(',');
    DecimalFormat decimalFormat = new DecimalFormat("#.##", decimalFormatSymbols);
    if (isDouble(str)) {
      return decimalFormat.format(Double.parseDouble(str));
    } else {
      return str;
    }

  }

  public static boolean isDouble(String value) {
    if (value == null) {
      return false;
    }
    try {
      Double.parseDouble(value);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  public static String checkDataNumber(String number) {
    DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
    decimalFormatSymbols.setDecimalSeparator(',');
    decimalFormatSymbols.setGroupingSeparator('.');

    DecimalFormat decimalFormat = new DecimalFormat("#,###.##", decimalFormatSymbols);
    return decimalFormat.format(new BigDecimal(number));
  }

  public static String formatCurrency(String number) {
    if (!Strings.isNullOrEmpty(number)) {
      try {
        Double.parseDouble(number);
        String[] numberCheck = number.split("\\.");
        if (numberCheck.length > 1) {
          if (Long.parseLong(numberCheck[1]) > 0) {
            return checkDataNumber(number);
          } else {
            return checkDataNumber(number);
          }
        }
        return checkDataNumber(number);
      } catch (NumberFormatException e) {
        return null;
      }
    }
    return null;
  }

  public static String formatNumber(String number) {
    if (!Strings.isNullOrEmpty(number)) {
      try {
        Double.parseDouble(number);
        String[] numberCheck = number.split("\\.");
        if (numberCheck.length > 1) {
          if (Long.parseLong(numberCheck[1]) > 0) {
            return formatDouble(number);
          } else {
            return formatDouble(number);
          }
        }
        return formatDouble(number);
      } catch (NumberFormatException e) {
        return null;
      }
    }
    return null;
  }

  public static String removeComa(String s) {
    if (s != null && s.length() >= 2) {
      if (',' == s.charAt(0)) {
        s = s.substring(1);
      }
      if (',' == s.charAt(s.length() - 1)) {
        s = s.substring(0, s.length() - 1);
      }
    }
    /* thanhnb update */
    if (!Strings.isNullOrEmpty(s) && ",".equals(s)) {
      s = s.replace(",", "");
    }
    if (s == null)
      return "";
    /* thanhnb update */
    return s;
  }

  public static String removeSemicolon(String s) {
    if (s != null && s.length() >= 2) {
      if (';' == s.charAt(0)) {
        s = s.substring(1);
      }
      if (';' == s.charAt(s.length() - 1)) {
        s = s.substring(0, s.length() - 1);
      }
    }
    /* thanhnb update */
    if (!Strings.isNullOrEmpty(s) && ",".equals(s)) {
      s = s.replace(",", "");
    }
    /* thanhnb update */
    return s;
  }

  public static String removeDuplicateComa(String s) {
    String result = "";
    String arr[] = s.split(",");
    for (String item : arr) {
      if (item.isEmpty()) {
        continue;
      }
      result += item + ";";
    }
    if (result != null && result.length() >= 2) {
      if (';' == result.charAt(0)) {
        result = result.substring(1);
      }
      if (';' == result.charAt(result.length() - 1)) {
        result = result.substring(0, result.length() - 1);
      }
    }
    return result;
  }

  public static String removeComaUpdate(String s) {
    if (s != null && s.length() >= 2) {
      if (',' == s.charAt(0)) {
        s = s.substring(1);
      }
      if (',' == s.charAt(s.length() - 1)) {
        s = s.substring(0, s.length() - 1);
      }
    }
    return s;
  }

  public static String buildFileName(File file, String prefixFileResult) {
    String excelType;
    String result = "";
    String pattern = "yyyyMMdd_HHmmss";
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
    LocalDateTime now = LocalDateTime.now();

    String fileUserImport = file.getName();
    if (!Strings.isNullOrEmpty(fileUserImport)) {
      excelType = fileUserImport.substring(fileUserImport.lastIndexOf("."), fileUserImport.length());
      result = prefixFileResult + "_" + dtf.format(now) + excelType;
    }
    return result;
  }

  public static String getFileName(String preName, String type) {
    String pattern = "yyyyMMdd_HHmmss";
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
    LocalDateTime now = LocalDateTime.now();
    return preName + "_" + dtf.format(now) + "." + type;
  }

//  public static void main(String[] args) {
//    String test = "100.000,00";
//    test = test.replaceAll("\\.", "").replace(",", ".");
//    double parseDouble = Double.parseDouble(test);
//    System.out.println(parseDouble);
//  }

}
