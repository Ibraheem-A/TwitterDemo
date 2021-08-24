package com.example.twitterdemo;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;

class StarterApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this)

        // Add your initialization code here
        Parse.initialize(Parse.Configuration.Builder(applicationContext)
                .applicationId("myappID")
                .clientKey("OOXtW6xkAxNg")
                .server("http://18.119.11.109/parse/")
                .build()
        )

        /*
        ParseObject object = new ParseObject("ExampleObject");
        object.put("myNumber", "123");
        object.put("myString", "Single");
        object.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){
                    Log.i("Parse Result", "Successful!");
                } else {
                    Log.i("Parse Result", "Failed" + e.toString());
                }
            }
        });
        */


        //ParseUser.enableAutomaticUser()
        val defaultACL = ParseACL()
        defaultACL.publicReadAccess = true
        defaultACL.publicWriteAccess = true
        ParseACL.setDefaultACL(defaultACL, true)

    }
}
