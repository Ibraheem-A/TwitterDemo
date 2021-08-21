package com.example.twitterdemo;

import android.util.Log;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class UserSignUp {
    String signUpStatus = "false";
    String message = "Sign Up Failed";

    public String[] signUp(String username, String password){
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){
                    Log.i("Signed up...", "Successfully");
                    signUpStatus = "true";
                } else {
                    Log.i("Sign up...", "Failed " + e.getMessage());
                    signUpStatus = "false";
                }
                message = e.getMessage();
            }
        });
        return new String[] {signUpStatus, message};
    }


}
