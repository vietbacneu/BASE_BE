package com.example.qlbhbe.controller.response;

import com.example.qlbhbe.util.Constants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SuccessResponse {
    private String code;
    private String message;

    public SuccessResponse() {
    }

    public SuccessResponse(String message) {
        this.code = Constants.SUCCESS;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
