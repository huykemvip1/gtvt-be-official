package com.example.gtvtbe.common;

import com.example.gtvtbe.enumeration.EnumResponseStatus;

public class Result<T> {
    private final T data;
    private final String code;
    private final String message;

    private Result(T data, String code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }


    public T getData() {
        return data;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
    public static <T> Result<T> ok(T t) {
        return new Result<>(t, EnumResponseStatus.CODE_200.getCode(),EnumResponseStatus.CODE_200.getMessage());
    }
    public static <T> Result<T> badRequest(T t){
        return new Result<T>(t, EnumResponseStatus.CODE_400.getCode(),EnumResponseStatus.CODE_400.getMessage());
    }
    public static  <T> Result<T> responseStatus(T t,EnumResponseStatus enumResponseStatus){
        return new Result<T>(t, enumResponseStatus.getCode(),enumResponseStatus.getMessage());
    }
}
