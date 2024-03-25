package com.store.auth.persistence.entity;

public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER"),
    SERVICE("ROLE_SERVICE");

    private final String roleName;

    public String getRoleName() {
        return roleName;
    }

    UserRole(String roleName) {
        this.roleName = roleName;
    }



}