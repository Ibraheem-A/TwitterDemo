package com.example.twitterdemo

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

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
        val usernameInput: String = usernameEditText?.text.toString().toLowerCase()
        val passwordInput: String = passwordEditText?.text.toString()

        if (!usernameInput.isEmpty() && !passwordInput.isEmpty()){
            if(ParseUtil.accountAlreadyExists(usernameInput, passwordInput)){
                userLogin(usernameInput, passwordInput)
            } else {
                val signUpResponse = UserSignUp.signUp(usernameInput, passwordInput)
                if (signUpResponse[0].equals("true")){
                    Toast.makeText(this@SignUpLoginActivity, "Signed Up successfully", Toast.LENGTH_LONG).show();
                    userLogin(usernameInput, passwordInput)
                } else{
                    Toast.makeText(this@SignUpLoginActivity, signUpResponse[1], Toast.LENGTH_LONG).show();
                }
            }
        }

    }

    // this is written here because it is used more than once (so to avoid repetition)
    fun userLogin(username: String, passWord: String){
        val loginResponse = UserLogin.login(username, passWord)

        if(loginResponse[0].equals("true")){
            Toast.makeText(this@SignUpLoginActivity, loginResponse[1], Toast.LENGTH_LONG).show()
            val startUsersListActivity = Intent(applicationContext, UserListActivity::class.java)
            startActivity(startUsersListActivity)
        } else {
            Toast.makeText(this@SignUpLoginActivity, loginResponse[1], Toast.LENGTH_LONG).show();
        }
    }

}