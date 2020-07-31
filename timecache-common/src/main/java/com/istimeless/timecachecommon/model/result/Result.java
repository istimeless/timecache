package com.istimeless.timecachecommon.model.result;

public class Result<T> {

    private int code;

    private String id;

    private String msg;

    private T data;

    public Result (int code) {
        this.code = code;
    }

    public Result (int code, String id) {
        this.code = code;
        this.id = id;
    }

    public Result (int code, String id, String msg) {
        this.code = code;
        this.id = id;
        this.msg = msg;
    }

    public Result (int code, String id, T data) {
        this.code = code;
        this.id = id;
        this.data = data;
    }

    public Result (int code, String id, String msg, T data) {
        this.code = code;
        this.id = id;
        this.msg = msg;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
