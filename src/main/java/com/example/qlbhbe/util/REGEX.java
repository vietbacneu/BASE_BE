package com.example.qlbhbe.util;

public enum REGEX {

    NOT_SPECIAL_CHARACTER("[A-Za-z0-9_]+"),

    ONLY_NUMBER("^[0-9]*$"),

    EMAIL( "^[\\w!#$%&'*+\\/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+\\/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"),

    PROJECT_CODE_REG("[a-zA-Z0-9-_]*");

    private String value;

    public String getValue() {
        return value;
    }

    REGEX(String value) {
        this.value = value;
    }
}
