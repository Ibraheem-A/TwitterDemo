package com.example.twitterdemo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.parse.ParseAnalytics


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({
            val startSignUpPage = Intent(applicationContext, SignUpLoginActivity::class.java)
            startSignUpPage.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startSignUpPage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(startSignUpPage)
        }, 2000)

        ParseAnalytics.trackAppOpenedInBackground(intent)
    }
}