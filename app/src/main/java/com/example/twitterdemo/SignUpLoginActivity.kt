package com.example.twitterdemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.parse.ParseUser
import java.util.*

class SignUpLoginActivity : AppCompatActivity(), View.OnKeyListener {
    var usernameEditText: EditText? = null
    var passwordEditText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_login)

        usernameEditText = findViewById(R.id.userNameTextView)
        passwordEditText = findViewById(R.id.passwordTextView)
    }

    override fun onKey(view: View, keyCode: Int, event: KeyEvent): Boolean{
        if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN){
            onSignUpLoginClick(view)
        }
        return false
    }

    fun onBackgroundClickHideKeyboard(view: View){
        val inputMethodManager: InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    fun onSignUpLoginClick(view: View){
        Log.i("Sign Up/Login", "Button Clicked!!!")
        val usernameInput: String = usernameEditText?.text.toString().toLowerCase(Locale.ROOT)
        val passwordInput: String = passwordEditText?.text.toString()

        if (usernameInput.isNotEmpty() && passwordInput.isNotEmpty()){
            Log.i("Sign Up/Login", "Initiating Parse check if account exists...")

                ParseUtil.get().accountAlreadyExists(
                    usernameInput, passwordInput
                ) { objects, e ->
                    if (objects != null && e == null) {
                        Log.i(
                            "Credentials check...",
                            "Account with username @$usernameInput already Exists!!"
                        )
                        onAccountCheckResponse(usernameInput, passwordInput, true)
                    } else {
                        Log.i(
                            "Credentials check...",
                            "Account with username @$usernameInput does not Exist!!"
                        )
                        onAccountCheckResponse(usernameInput, passwordInput, false)
                    }
                }
            }



    }

    private fun onAccountCheckResponse(username: String, password: String, accountAlreadyExists: Boolean) {
        if (accountAlreadyExists) {
            Log.i("Sign Up/Login", "Account exists already. Attempting Login...")
            userLogin(username, password)
        } else {
            userSignUp(username, password)
        }
    }


    // this is written here because it is used more than once (so to avoid repetition)
    private fun userLogin(username: String, password: String){

        val user = ParseUser()
        user.username = username
        user.setPassword(password)

        ParseUser.logInInBackground(username, password) { _, e ->
            if (e == null) {
                Log.i("Logged in...", "Successfully")
                Toast.makeText(this@SignUpLoginActivity, "Logged in... Successfully", Toast.LENGTH_LONG).show()
                val startUsersListActivity = Intent(applicationContext, UserListActivity::class.java)
                startActivity(startUsersListActivity)
            } else {
                Log.i("Sign Up...", "Failed " + e.message)
                Toast.makeText(this@SignUpLoginActivity, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun userSignUp(username: String, password: String){

        val user = ParseUser()
        user.username = username
        user.setPassword(password)

        user.signUpInBackground{ e ->
            if (e == null) {
                Log.i("Signed up...", "Successfully")
                Toast.makeText(this@SignUpLoginActivity, "Signed Up Successfully", Toast.LENGTH_LONG).show()
                userLogin(username, password)
            } else {
                Log.i("Log in...", "Failed " + e.message)
                Toast.makeText(this@SignUpLoginActivity, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}