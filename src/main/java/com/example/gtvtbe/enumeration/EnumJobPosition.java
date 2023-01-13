package com.example.gtvtbe.enumeration;

public enum EnumJobPosition {
    GIANGVIEN(1,"Giảng viên"),
    TRUONGBOMON(2, "Trưởng bộ môn"),
    SINHVIEN(3,"Sinh viên"),
    CUUSINHVIEN(4,"Cựu sinh viên");
    private final Integer id;
    private final String name;

    EnumJobPosition(Integer id, String name) {
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
