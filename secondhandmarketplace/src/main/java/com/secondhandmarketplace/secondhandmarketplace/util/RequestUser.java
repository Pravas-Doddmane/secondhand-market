package com.secondhandmarketplace.secondhandmarketplace.util;


import com.secondhandmarketplace.secondhandmarketplace.entity.User;

public class RequestUser {
    public static final String ATTR = "REQUEST_USER";
    public static User get(Object o) { return (User) o; }
}
