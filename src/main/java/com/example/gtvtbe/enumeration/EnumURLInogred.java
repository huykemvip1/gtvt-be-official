package com.example.gtvtbe.enumeration;

public enum EnumURLInogred {
    URL_1("/v2/api-docs"),
    URL_2("/configuration/ui"),
    URL_3("/configuration/security"),
    URL_4("/swagger-resources"),
    URL_5("/swagger-ui"),
    URL_6("/webjars"),
    URL_8("/swagger.json"),
    URL_9("/api/v1/authentication"),;
    private final String value;

    EnumURLInogred(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
