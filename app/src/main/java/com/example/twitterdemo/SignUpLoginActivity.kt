package com.example.twitterdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.parse.ParseUser

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
        val usernameInput: String = usernameEditText?.text.toString().toLowerCase()
        val passwordInput: String = passwordEditText?.text.toString()

        if (!usernameInput.isEmpty() && !passwordInput.isEmpty()){
            if(ParseUtil.accountAlreadyExists(usernameInput, passwordInput)){
                // Login
            } else {
                val signUpResponse = UserSignUp.signUp(usernameInput, passwordInput)
                if (signUpResponse[0].equals("true")){
                    Toast.makeText(this, "Signed Up successfully", Toast.LENGTH_LONG).show();
                    // Then Log User in
                } else{
                    Toast.makeText(this, signUpResponse[1], Toast.LENGTH_LONG).show();
                }
            }
        }

    }


}