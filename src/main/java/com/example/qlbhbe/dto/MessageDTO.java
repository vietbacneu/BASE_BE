package com.example.qlbhbe.dto;

public class MessageDTO {
    String message;
    Long code;

    public MessageDTO(String message, Long code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }
}
