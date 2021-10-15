package com.example.qlbhbe.controller;

import com.example.qlbhbe.controller.response.BadRequestResponse;
import com.example.qlbhbe.dto.ExceptionCustom;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@RestControllerAdvice
public class ApiControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ApiControllerAdvice.class);

    private MessageSource messageSource;
    private ObjectMapper objectMapper;


    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public BadRequestResponse handleDataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request, HttpServletResponse response) {
        //couldNotDelete
        response.setStatus(400);
        Locale locale = LocaleContextHolder.getLocale();
        if ("DELETE".equals(request.getMethod())) {
            return new BadRequestResponse(messageSource.getMessage("couldNotDelete", null, locale));
        } else {
            return new BadRequestResponse(messageSource.getMessage("dataIntegrityViolated", null, locale));
        }
    }

    @ExceptionHandler(value = ResponseStatusException.class)
    public ObjectNode handleResponseStatusException(ResponseStatusException e, HttpServletResponse httpServletResponse) {
        httpServletResponse.setStatus(e.getStatus().value());
        ObjectNode node = objectMapper.createObjectNode();
        if (e.getReason() != null) {
            node.put("message", e.getReason());
        }
        return node;
    }

    @ExceptionHandler(value = EmptyResultDataAccessException.class)
    public ObjectNode handleEmptyResultDataAccessException(EmptyResultDataAccessException e, HttpServletResponse response) {
        response.setStatus(404);
        ObjectNode node = objectMapper.createObjectNode();
        node.put("message",
                messageSource.getMessage("resourceNotFound", null, LocaleContextHolder.getLocale()));
        return node;
    }


    @ExceptionHandler(value = Exception.class)
    public ObjectNode handleIOException(Exception e, HttpServletResponse response) {
        logger.error(e.getMessage(), e);
        response.setStatus(500);
        ObjectNode node = objectMapper.createObjectNode();
        node.put("message",
                messageSource.getMessage("internalServerError", null, LocaleContextHolder.getLocale()));
        return node;
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ObjectNode handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletResponse response) {
        logger.error(e.getMessage(), e);
        response.setStatus(400);
        ObjectNode node = objectMapper.createObjectNode();
        node.put("message", "Sai phương thức POST/GET/PUT/DELETE");
        return node;
    }

//    @ExceptionHandler(AccessDeniedException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    public ObjectNode handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request) {
//        ObjectNode node = objectMapper.createObjectNode();
//        String msg;
//        if ("GET".equalsIgnoreCase(request.getMethod())) {
//            msg = messageSource.getMessage("accessDeniedGet", null, LocaleContextHolder.getLocale());
//        } else {
//            msg = messageSource.getMessage("accessDeniedAction", null, LocaleContextHolder.getLocale());
//        }
//        node.put("message", msg);
//        return node;
//    }

    @ExceptionHandler(value = ExceptionCustom.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity customEx(ExceptionCustom exceptionCustom) {
        return new ResponseEntity(exceptionCustom.getMess(), exceptionCustom.getHttpStatus());
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}
