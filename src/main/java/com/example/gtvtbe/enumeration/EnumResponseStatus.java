package com.example.gtvtbe.enumeration;

public enum EnumResponseStatus {
    CODE_200("200", "Successfully !!"),
    CODE_201("201", "Created!!"),
    CODE_202("202", "Accepted!!"),
    CODE_203("203", "Non-Authoritative Information Required!!"),
    CODE_204("204", "No Content!!"),
    CODE_400("400", "Bad Request"),
    CODE_401("401", "Unauthorized"),
    CODE_403("403", "Forbidden"),
    CODE_404("404", "Not Found"),
    CODE_405("405", "Method Not Allowed"),
    CODE_500("500", "Internal Server Error"),
    ;
    private String code;
    private String message;

    EnumResponseStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
