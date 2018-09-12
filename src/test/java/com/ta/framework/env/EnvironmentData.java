package com.ta.framework.env;

public class EnvironmentData {

    private String baseUrl;
    private User user;

    public EnvironmentData(String baseUrl, String userName, String password) {
        this.baseUrl = baseUrl;
        this.user = new User(userName, password);
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getUserName() {
        return this.user.getUserName();
    }

    public String getPassword() {
        return this.user.getPassword();
    }
}

class User {

    private String userName;
    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}