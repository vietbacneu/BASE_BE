package com.example.qlbhbe.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class UString {

  private UString() {
  }

  /**
   * Kiểm tra chuỗi ký tự có phải null hoặc toàn dấu whitespace, tab, enter...
   *
   * @param str
   * @return true nếu str là null hoặc toàn dấu whitespace, tab, enter...
   */
  public static boolean isNullOrWhitespace(String str) {
    return str == null || "".equals(str.trim());
  }

  /**
   * Loai bo dau trang o 2 dau cua 1 String, neu null hoac '' chuyển thành
   * null
   *
   * @param arg
   * @return String chuoi sau khi duoc loai bo dau trang o 2 dau
   * @throws Exception
   */
  public static String trim(String arg) {
    String result = null;
    if (arg != null && !"".equals(arg.trim())) {
      result = arg.trim();
    }
    return result;
  }

  /**
   * Tách chuỗi ký tự thành các phần tử phân cách bởi regex
   *
   * @param str   chuỗi ký tự cần phân tách
   * @param regex chuỗi ký tự phân cách
   * @return
   * @throws Exception
   */
  public static List<String> split(String str, String regex) {
    List<String> result = new ArrayList<>();
    if (!isNullOrWhitespace(str)) {
      String[] arr = str.split(regex);
      for (String ele : arr) {
        if (!isNullOrWhitespace(ele)) {
          result.add(ele.trim());
        }
      }
    }
    return result;
  }

  /**
   * Ghep list thanh chuoi ky tu.
   *
   * @param list      danh sách cần ghép
   * @param separator chuỗi phân cách
   * @return
   * @throws Exception
   */
  public static String join(List<String> list, String separator) {
    StringBuilder str = new StringBuilder();
    if (list != null && !list.isEmpty()) {
      str.append(list.get(0));
      for (int i = 1; i < list.size(); i++) {
        str.append(separator);
        str.append(list.get(i));
      }
    }
    return str.toString();
  }

  public static boolean isDouble(String str) {
    try {
      Double.parseDouble(str);
      return true;
    }
    catch(NumberFormatException e) {
      return false;
    }

  }
  public static String roundOffTo2DecPlaces( String tr){
    DecimalFormat f = new DecimalFormat("##.00");
    return f.format(Double.parseDouble(tr));
  }

  public static boolean checkCellEmpty(Cell cell){
    if(cell == null ){
      return true;
    }else{
      if("".equals(getValueFromExcel(cell))){
        return true;
      }
    }
    return false;
  }
  private static String getValueFromExcel(Cell cell) {
    String value = "";
    try {
      if (cell.getCellTypeEnum() == CellType.NUMERIC) {
        value = String.valueOf(cell.getNumericCellValue()).trim();
      }
      if (cell.getCellTypeEnum() == CellType.STRING) {
        value = cell.getStringCellValue().trim();
      }
    } catch (Exception e) {
       throw e;
    }
    return value;
  }
}
