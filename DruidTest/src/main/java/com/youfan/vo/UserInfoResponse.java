package com.youfan.vo;

/**
 *
 */
public class UserInfoResponse {

    private int id;
    private String name;
    private String passwordencrypt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswordencrypt() {
        return passwordencrypt;
    }

    public void setPasswordencrypt(String passwordencrypt) {
        this.passwordencrypt = passwordencrypt;
    }
}