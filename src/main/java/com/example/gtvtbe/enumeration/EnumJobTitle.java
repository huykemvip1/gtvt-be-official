package com.example.gtvtbe.enumeration;

public enum EnumJobTitle {
    GIAOSU(1,"Giáo Sư"),
    PHOGIAOSU(2,"Phó Giáo Sư"),
    TIENSI(3,"Tiến Sĩ"),
    THACSI(4,"Thạc Sĩ"),
    DAIHOC(5,"Đại Học");
    private final Integer id;
    private final String name;

    EnumJobTitle(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
