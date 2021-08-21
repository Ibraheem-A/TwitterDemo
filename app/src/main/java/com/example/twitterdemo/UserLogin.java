package com.example.twitterdemo;

import android.util.Log;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class UserLogin {
    private static String loginStatus = "false";
    private static String message = "Login Failed";

    public static String[] login(String username, String password){
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);

        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e == null) {
                    Log.i("Logged in...", "Successfully");
                    loginStatus = "true";
                } else {
                    Log.i("Log in...", "Failed" + e.getMessage());
                    e.printStackTrace();
                    loginStatus = "false";
                }
                message = e.getMessage();
            }
        });
        return new String[] {loginStatus, message};
    }
}
