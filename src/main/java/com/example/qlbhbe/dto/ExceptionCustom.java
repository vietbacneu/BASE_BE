package com.example.qlbhbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.http.HttpStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExceptionCustom extends RuntimeException {
    private Long code;
    private String mess;
    private HttpStatus httpStatus;

    public ExceptionCustom(Long code, String mess, HttpStatus httpStatus) {
        this.code = code;
        this.mess = mess;
        this.httpStatus = httpStatus;
    }

    public ExceptionCustom(String message, Long code, String mess, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.mess = mess;
        this.httpStatus = httpStatus;
    }

    public ExceptionCustom(String message, Throwable cause, Long code, String mess, HttpStatus httpStatus) {
        super(message, cause);
        this.code = code;
        this.mess = mess;
        this.httpStatus = httpStatus;
    }

    public ExceptionCustom(Throwable cause, Long code, String mess, HttpStatus httpStatus) {
        super(cause);
        this.code = code;
        this.mess = mess;
        this.httpStatus = httpStatus;
    }

    public ExceptionCustom(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Long code, String mess, HttpStatus httpStatus) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.mess = mess;
        this.httpStatus = httpStatus;
    }

    public ExceptionCustom() {
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
