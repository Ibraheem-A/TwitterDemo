package com.example.twitterdemo

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseUser


class UserListActivity : AppCompatActivity() {
    var usersArrayList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        title = "User feed"

        val userListView: ListView = findViewById(R.id.userListView)

        val query = ParseUser.getQuery()
        query.whereNotEqualTo("username", ParseUser.getCurrentUser().username)
        query.orderByAscending("username")
        query.findInBackground { objects, e ->
            if (objects!!.isNotEmpty() && e == null) {
                Log.i("Querying users... ", "Found ${objects.size} user(s)")
                for (user in objects) {
                    usersArrayList.add(user!!.username)
                }
            } else {
                Toast.makeText(this@UserListActivity, e?.message, Toast.LENGTH_LONG).show()
                Log.i(
                        "Querying users... ",
                        """Failed!! Result: ${objects.size} users ${e?.message}"""
                )
            }
        }
        Log.i("UserListActivity","Populating Array Adapter...")
        var arrayAdapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_checked, usersArrayList)
        Log.i("UserListActivity", "Update the View using the array adapter")
        userListView.adapter = arrayAdapter

        userListView.setOnItemClickListener{ parent, view, position, id ->
            // check to follow and unfollow
        }


    }
}