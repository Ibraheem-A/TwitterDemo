package com.example.twitterdemo;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

final public class ParseUtil {

    private static ParseUtil instance;

    private boolean usernameAlreadyExists = false;
    private boolean accountAlreadyExists = false;

    public boolean isUsernameAlreadyExists() {
        return usernameAlreadyExists;
    }

    public boolean isAccountAlreadyExists() {
        return accountAlreadyExists;
    }

    public void setUsernameAlreadyExists(boolean usernameAlreadyExists) {
        this.usernameAlreadyExists = usernameAlreadyExists;
    }

    public void setAccountAlreadyExists(boolean accountAlreadyExists) {
        this.accountAlreadyExists = accountAlreadyExists;
    }

    public boolean usernameAlreadyExists(String username){
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("username", username.toLowerCase());

        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if (objects != null && e == null) {
                    Log.i("Username check...", "@" + username + " already Exists!!");
                    ParseUtil.get().setUsernameAlreadyExists(true);
                } else {
                    Log.i("Username check...", "@" + username + " does not exist");
                    ParseUtil.get().setUsernameAlreadyExists(false);
                }
            }
        });

        return usernameAlreadyExists;
    }

    public void accountAlreadyExists (String username, String password, FindCallback<ParseUser> callBack) {
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("username", username.toLowerCase());
        query.whereEqualTo("password", password);

        query.findInBackground(callBack);
    }

    public static void create () {
        instance = new ParseUtil();
    }
    public static ParseUtil get (){
        return instance;
    }
}
