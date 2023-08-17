package com.example.MyClientApp.util;


public class ErrorMessage {
    public static String USERNAME_NOT_FOUND = "User not found with given username: ";
    public static String INVALID_PASSWORD = "Invalid password! Please Enter a Valid Password , Password must be min 6 , max 10 character!";
    public static String PASSWORD_NOT_NULL = "User password can not be empty! Please Enter a Valid Password";
    public static String INVALID_EMAIL = "Invalid Email! Please Enter valid Email";
    public static String EMAIL_NOT_NULL = "User Email can not be empty! Please Enter your Email";
    public static String TAKEN_EMAIL = "Email already taken! Try another Email";
    public static String WRONG_USER_DETAIL = "Username or Password is wrong! , please enter a valid username and password";
    public static String PASSWORD_REGEX = "^[a-zA-Z0-9]{6,10}$";
    public static String PASSWORDS_MATCH = "Password And Confirm Password Did not matcher! Please be sure its equals.";
    public static String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    public static String TAKEN_USERNAME = "Username already taken! Try another Username";
    public static String NAME_NOT_NLL = "Name Can not be null! Please enter your name.";
    public static String SURNAME_NOT_NLL = "Surname Can not be null! Please enter your Surname.";
    public static String PHOTO_NULL = "Profile photo is empty!";
    public static String PHOTO_UPLOAD_SUCCESS = "Profile photo uploaded was successfully.";
    public static String USER_NOT_FOUND = "User not found with given id: ";

}
