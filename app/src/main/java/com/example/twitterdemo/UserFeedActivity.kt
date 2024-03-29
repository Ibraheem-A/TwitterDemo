package com.example.twitterdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import com.parse.ParseObject
import com.parse.ParseQuery
import com.parse.ParseUser

class UserFeedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_feed)

        title = "User feed"

        val userFeedListView: ListView = findViewById(R.id.userFeedView)

        val tweetData = ArrayList<Map<String, String>>()

        val query:ParseQuery<ParseObject> = ParseQuery.getQuery("Tweet")
        query.whereContainedIn("username", ParseUser.getCurrentUser().getList("isFollowing"))
        query.orderByDescending("createdAt")
        query.findInBackground{ objects, e ->
            if (objects!!.isNotEmpty() && e== null){
                Log.i("Users followed", "Tweet found... Loading to view")
                for (tweet in objects){
                    Log.i("tweet", tweet.get("tweet").toString())

                    val tweetInfo = HashMap<String, String>()
                    tweetInfo["content"] = tweet.get("tweet").toString()
                    tweetInfo["username"] = tweet.get("username").toString()
                    tweetData.add(tweetInfo)
                }

                userFeedListView.adapter = createSimpleAdapter(tweetData)

            } else {
                Log.i("Tweet", "Not found")

            }
        }
    }

    private fun createSimpleAdapter(data:ArrayList<Map<String, String>>): SimpleAdapter{
        return SimpleAdapter(this, data, android.R.layout.simple_list_item_2, arrayOf("content", "username"), intArrayOf(android.R.id.text1, android.R.id.text2))
    }
}