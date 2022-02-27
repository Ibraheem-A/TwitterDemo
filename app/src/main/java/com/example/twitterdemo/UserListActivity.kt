package com.example.twitterdemo

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.parse.ParseObject
import com.parse.ParseUser
import com.parse.SaveCallback


class UserListActivity : AppCompatActivity() {
    var usersArrayList = ArrayList<String>()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var menuInflater: MenuInflater = getMenuInflater()
        menuInflater.inflate(R.menu.tweet_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.tweet){
            var builder = AlertDialog.Builder(this)
            builder.setTitle("Send a Tweet")
            var tweetEditText = EditText(this)
            builder.setView(tweetEditText)
            builder.setPositiveButton("Send", DialogInterface.OnClickListener { dialog, which -> Log.i("Info", tweetEditText.text.toString())
                var tweetObject = ParseObject("Tweet")
                tweetObject.put("tweet", tweetEditText.text.toString())
                tweetObject.put("username", ParseUser.getCurrentUser().username)
                tweetObject.saveInBackground(SaveCallback { e ->
                    if(e == null){
                        Toast.makeText(applicationContext, "Tweet sent!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(applicationContext, "Tweet failed :(", Toast.LENGTH_SHORT).show()
                    }
                })
            })
            builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> Log.i("Info", "User aborts tweet!"); dialog.cancel()})
            builder.show()

        } else if (item.itemId == R.id.yourfeed){
            var startUserFeedActivity = Intent(applicationContext, UserFeedActivity::class.java)
            startActivity(startUserFeedActivity)
            Log.i("Info", "Displaying feed for ${ParseUser.getCurrentUser().username}")


        } else if (item.itemId == R.id.logout){
            ParseUser.logOut()
            Log.i("Info", "${ParseUser.getCurrentUser().username} Logging out")
            val startSignUpLoginActivity = Intent(applicationContext, SignUpLoginActivity::class.java)
            startActivity(startSignUpLoginActivity)
            Toast.makeText(applicationContext, "Logged out successfully", Toast.LENGTH_SHORT).show()
        }

        return super.onOptionsItemSelected(item)
    }

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
                populateArrayAdapterAndListView(userListView)
            } else {
                Toast.makeText(this@UserListActivity, e?.message, Toast.LENGTH_LONG).show()
                Log.i(
                        "Querying users... ",
                        """Failed!! Result: ${objects.size} users ${e?.message}"""
                )
            }
        }

        userListView.setOnItemClickListener{ parent, view, position, id ->
            // check to follow and unfollow
        }


    }

    private fun populateArrayAdapterAndListView(userListView: ListView) {
        Log.i("UserListActivity", "Populating Array Adapter...")
        var arrayAdapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_checked, usersArrayList)
        Log.i("UserListActivity", "Update the View using the array adapter")
        userListView.adapter = arrayAdapter
    }
}