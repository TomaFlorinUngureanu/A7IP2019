package com.packages.model;



public class JwtUser {
    private static String userName;

    public static void setUserName(String username) {
        userName = username;
    }

    public static String getUserName() {
        return userName;
    }

}
