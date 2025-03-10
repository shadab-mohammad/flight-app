package com.examly.springapp.model;

public class UserToken {
    private int userId;
    private String username;
    private String token;
    private String userRole;
    
    public UserToken() {
    }

    public UserToken(String username,int userId, String token, String userRole) {
        this.username = username;
        this.userId = userId;
        this.token = token;
        this.userRole = userRole;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


}
