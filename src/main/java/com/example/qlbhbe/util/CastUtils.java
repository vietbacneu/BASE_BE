package com.example.qlbhbe.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.*;
import java.util.stream.Collectors;

public class CastUtils {

  private static Logger logger = LogManager.getLogger("CastUtils");
  private static ModelMapper modelMapper = new ModelMapper();

  private CastUtils() {
  }

  /**
   * Cast to Pageable
   *
   * @param page
   * @param limit
   * @return
   */
  public static Pageable castToPageable(String page, String limit) {
    int pageInt = 0;
    int limitInt = 10;
    Pageable pageable = null;
    try {
      pageInt = Integer.parseInt(page);
      limitInt = Integer.parseInt(limit);
      pageable = PageRequest.of(pageInt, limitInt);
    } catch (Exception e) {
      logger.info(e.getMessage(),e);
      pageable = PageRequest.of(pageInt, limitInt);
    }
    return pageable;
  }


  /**
   * Return error info for validation exception
   *
   * @param bindingResult
   * @return
   */
  public static Map<String, List<String>> castErrorMessageToMap(BindingResult bindingResult) {
    Map<String, List<String>> errorMessageMap = new HashMap<>();
    List<String> messageList = new ArrayList<>();
    List<ObjectError> objectErrorList = bindingResult.getAllErrors();
    for (ObjectError e : objectErrorList) {
      if (e instanceof FieldError) {
        String message = e.getDefaultMessage();
        String key = (((FieldError) e).getField());
        putToErrorMessage(messageList, errorMessageMap, key, message);
      } else {
        String message = e.getDefaultMessage();
        String key = (String) ReflectionUtils.getPropertyValue(Objects.requireNonNull(e.getArguments())[1], "resolvableString");
        putToErrorMessage(messageList, errorMessageMap, key, message);
      }
    }
    return errorMessageMap;
  }

  private static void putToErrorMessage(List<String> messageList, Map<String, List<String>> errorMessageMap, String key, String message) {
    if (!errorMessageMap.containsKey(key)) {
      messageList = new ArrayList<>();
      messageList.add(message);
      errorMessageMap.put(key, messageList);
    } else {
      messageList = errorMessageMap.get(key);
      messageList.add(message);
      errorMessageMap.put(key, messageList);
    }
  }

  public static <D, T> D map(final T entity, Class<D> outClass) {
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    return modelMapper.map(entity, outClass);
  }

  public static <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outCLass) {
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    return entityList.stream()
            .map(entity -> map(entity, outCLass))
            .collect(Collectors.toList());
  }
}
