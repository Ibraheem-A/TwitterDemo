package com.example.twitterdemo;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class ParseUtil {

    static boolean usernameAlreadyExists = false;
    static boolean accountAlreadyExists = false;

    public static boolean usernameAlreadyExists (String username){
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("username", username.toLowerCase());

        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if (objects != null && e == null) {
                    Log.i("Username check...", "Already Exists!!");
                    ParseUtil.usernameAlreadyExists = true;
                } else {
                    Log.i("Username check...", "Account does not exist");
                }
            }
        });

        return usernameAlreadyExists;
    }

    public static boolean accountAlreadyExists (String username, String password){
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("username", username.toLowerCase());
        query.whereEqualTo("password", password);

        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if (objects != null && e == null) {
                    Log.i("Credentials check...", "Account already Exists!!");
                    ParseUtil.accountAlreadyExists = true;
                } else {
                    Log.i("Credentials check...", "Account does not exist");
                }
            }
        });

        return accountAlreadyExists;
    }
}
