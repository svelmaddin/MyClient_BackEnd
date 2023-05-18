package com.example.MyClientApp.util;

public class ErrorMessage {
    public static String USERID_NOT_FOUND = "User not found with given id: ";
    public static String USERNAME_NOT_FOUND = "User not found with given username: ";
    public static String INVALID_PASSWORD = "Invalid password! Please Enter a Valid Password , Password must be min 6 , max 10 character!";
    public static String PASSWORD_NOT_NULL = "User password can not be empty! Please Enter a Valid Password";
    public static String INVALID_EMAIL = "Invalid Email! Please Enter valid Email";
    public static String EMAIL_NOT_NULL = "User Email can not be empty! Please Enter your Email";
    public static String TAKEN_EMAIL = "Email already taken! Try another Email";
    public static String WRONG_USER_DETAIL = "Username or Password is wrong! , please enter a valid username and password";
    public static String PASSWORD_REGEX = "^[a-zA-Z0-9]{6,10}$";
    public static String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    public static String TAKEN_USERNAME = "Username already taken! Try another Username";

}
