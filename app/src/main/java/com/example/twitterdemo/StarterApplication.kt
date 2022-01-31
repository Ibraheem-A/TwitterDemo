package com.example.twitterdemo

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.parse.Parse
import com.parse.ParseACL
import com.parse.ParseException
import com.parse.ParseObject
import com.parse.SaveCallback

class StarterApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this)

        // Add your initialization code here
        Parse.initialize(Parse.Configuration.Builder(applicationContext)
                .applicationId("myappID")
                .clientKey("hCxsL9CG4krg")
                .server("http://3.70.112.251//parse/")
                .build()
        )


//        val `object` = ParseObject("ExampleObject")
//        `object`.put("myNumber", "123")
//        `object`.put("myString", "Single")
//        `object`.saveInBackground(SaveCallback() {
//            fun done(e: ParseException?) {
//                if (e == null) {
//                    Log.i("Parse Result", "Successful!")
//                } else {
//                    Log.i("Parse Result", "Failed $e")
//                }
//            }
//        })


        //ParseUser.enableAutomaticUser()
        val defaultACL = ParseACL()
        defaultACL.publicReadAccess = true
        defaultACL.publicWriteAccess = true
        ParseACL.setDefaultACL(defaultACL, true)

    }
}