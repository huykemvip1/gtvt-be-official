package com.example.gtvtbe.enumeration;

public enum EnumRoles {
    ROLE_ADMIN("ROLE1","ROLE_ADMIN"),
    ROLE_LECTURER("ROLE2","ROLE_LECTURER"),
    ROLE_USER("ROLE3","ROLE_USER");
    private final String id;
    private final String name;

    EnumRoles(String id, String name) {
      this.id = id;
      this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
