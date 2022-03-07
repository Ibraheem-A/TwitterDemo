package com.example.twitterdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import com.parse.ParseObject
import com.parse.ParseQuery
import com.parse.ParseUser

class UserFeedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_feed)

        title = "User feed"

        val userFeedActivity: ListView = findViewById(R.id.userFeedView)


        val query:ParseQuery<ParseObject> = ParseQuery.getQuery("Tweet")
        query.findInBackground{ objects, e ->
            if (objects!!.isNotEmpty() && e== null){
                Log.i("Users followed", "Tweet found... Loading to view")
                for (tweet in objects){
                    Log.i("tweet", tweet.get("tweet").toString())
                }
            } else {
                Log.i("Tweet", "Not found")

            }
        }
    }
}