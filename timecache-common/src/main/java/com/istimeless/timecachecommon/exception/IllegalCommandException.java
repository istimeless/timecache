package com.istimeless.timecachecommon.exception;

public class IllegalCommandException extends RuntimeException{

    public IllegalCommandException() {
        super("illegal command");
    }

    public IllegalCommandException(String msg) {
        super(msg);
    }
}
