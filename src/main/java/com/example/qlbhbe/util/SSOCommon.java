package com.example.qlbhbe.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class SSOCommon {

  private SSOCommon() {
  }

  private static final Logger log = LogManager.getLogger(SSOCommon.class);
  private static final String VSA_USER_TOKEN = "vsaUserToken";

  public static final String USERNAME_START = "<cas:USERNAME>";
  public static final String USERNAME_END = "</cas:USERNAME>";
  public static final String STAFFCODE_START = "<cas:STAFFCODE>";
  public static final String STAFFCODE_END = "</cas:STAFFCODE>";
  public static final String EMAIL_START = "<cas:EMAIL>";
  public static final String EMAIL_END = "</cas:EMAIL>";
  public static final String FULLNAME_START = "<cas:FULLNAME>";
  public static final String FULLNAME_END = "</cas:FULLNAME>";

  static String xmlTest = "<cas:serviceResponse xmlns:cas='http://www.yale.edu/tp/cas'>\n" +
      "    <cas:authenticationSuccess>\n" +
      "        <cas:user>241173</cas:user>\n" +
      "        <cas:attributes>\n" +
      "            <cas:isFromNewLogin>true</cas:isFromNewLogin>\n" +
      "            <cas:PHONENUMBER>9402351143</cas:PHONENUMBER>\n" +
      "            <cas:authenticationDate>2020-07-21T16:46:37.059+07:00[Asia/Ho_Chi_Minh]</cas:authenticationDate>\n" +
      "            <cas:successfulAuthenticationHandlers>USER_NAME</cas:successfulAuthenticationHandlers>\n" +
      "            <cas:GAUTH>false</cas:GAUTH>\n" +
      "            <cas:EMAIL>hunglm25@viettel.com.vn</cas:EMAIL>\n" +
      "            <cas:credentialType>UsernamePasswordCredential</cas:credentialType>\n" +
      "            <cas:STATUS>1</cas:STATUS>\n" +
      "            <cas:LOCALE>vi</cas:LOCALE>\n" +
      "            <cas:ISSENDSMS>false</cas:ISSENDSMS>\n" +
      "            <cas:authenticationMethod>USER_NAME</cas:authenticationMethod>\n" +
      "            <cas:USERID>123456</cas:USERID>\n" +
      "            <cas:STAFFCODE>241173</cas:STAFFCODE>\n" +
      "            <cas:USERNAME>241173</cas:USERNAME>\n" +
      "            <cas:longTermAuthenticationRequestTokenUsed>false</cas:longTermAuthenticationRequestTokenUsed>\n" +
      "            <cas:DEPTID>241450</cas:DEPTID>\n" +
      "            <cas:FULLNAME>Le Manh Hung</cas:FULLNAME>\n" +
      "            <cas:POSITIONID>8442</cas:POSITIONID>\n" +
      "            </cas:attributes>\n" +
      "    </cas:authenticationSuccess>\n" +
      "</cas:serviceResponse>";

  public static String getPropertyCASRes(String prefix, String end, String response) {
    String result = null;

    try {
      String[] val = response.split(prefix);
      String[] val2 = val[1].split(end);

      result = val2[0];
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }

    return result;
  }

  public static String getCurrentUserToken(HttpServletRequest request) {

    return request.getHeader(VSA_USER_TOKEN);
  }
}
