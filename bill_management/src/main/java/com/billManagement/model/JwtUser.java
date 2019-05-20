package com.billManagement.model;



public class JwtUser {
	
    private static String userName;

    public static void setUserName(String username) {
        JwtUser.userName = username;
    }
  
    public static String getUserName() {
        return userName;
    }

  
}
