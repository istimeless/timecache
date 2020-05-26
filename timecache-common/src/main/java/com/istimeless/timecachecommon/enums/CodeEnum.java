package com.istimeless.timecachecommon.enums;

public enum CodeEnum {

    SUCCESS(1, "成功"),

    FAILURE(0, "失败")
    ;
    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    CodeEnum (int code, String msg) {

    }
}
