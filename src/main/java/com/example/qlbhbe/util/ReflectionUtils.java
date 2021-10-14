package com.example.qlbhbe.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;

public class ReflectionUtils {

  private static final Logger log = LogManager.getLogger(ReflectionUtils.class);

  private ReflectionUtils() {
  }

  public static <T> Object getPropertyValue(T obj, String fieldName) {
    Object result = null;
    try {
      Field field = obj.getClass().getDeclaredField(fieldName);
      field.setAccessible(true);
      result = field.get(obj);
    } catch (NoSuchFieldException | IllegalAccessException ignored) {
      log.info(ignored.getMessage(), ignored);
    }
    return result;
  }
}
