package com.example.twitterdemo;

import android.util.Log;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class UserSignUp {
    private static String signUpStatus = "false";
    private static String message = "Sign Up Failed";

    public static String[] signUp(String username, String password){
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){
                    Log.i("Signed up...", "Successfully");
                    signUpStatus = "true";
                    message = "Signed up... Successfully";
                } else {
                    Log.i("Sign up...", "Failed " + e.getMessage());
                    signUpStatus = "false";
                    e.printStackTrace();
                    message = e.getMessage();
                }
            }
        });
        return new String[] {signUpStatus, message};
    }


}
