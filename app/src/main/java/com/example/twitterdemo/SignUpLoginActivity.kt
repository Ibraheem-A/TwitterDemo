package com.example.twitterdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class SignUpLoginActivity : AppCompatActivity() {
    var usernameEditText: EditText? = null
    var passwordEditText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_login)

        usernameEditText = findViewById(R.id.userNameTextView)
        passwordEditText = findViewById(R.id.passwordTextView)
    }

    fun onSignUpLoginClick(view: View){
        var usernameInput: String = usernameEditText?.text.toString().toLowerCase()
        var passwordInput: String = passwordEditText?.text.toString()


    }
}