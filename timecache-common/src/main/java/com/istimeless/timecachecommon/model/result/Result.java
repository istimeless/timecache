package com.istimeless.timecachecommon.model.result;

import com.istimeless.timecachecommon.model.value.Value;

public class Result {

    private int code;

    private String msg;

    private Value data;

    public Result (int code) {
        this.code = code;
    }

    public Result (int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result (int code, Value data) {
        this.code = code;
        this.data = data;
    }

    public Result (int code, String msg, Value data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Value getData() {
        return data;
    }

    public void setData(Value data) {
        this.data = data;
    }
}
