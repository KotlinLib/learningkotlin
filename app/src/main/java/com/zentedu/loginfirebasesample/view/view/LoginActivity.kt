package com.zentedu.loginfirebasesample.view.view

//import androidx.lifecycle.ViewModelProvider

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.zentedu.loginfirebasesample.R
import com.zentedu.loginfirebasesample.view.viewmodel.LoginActivityViewModel
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*


class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel: LoginActivityViewModel
    private val EMAIL = "email"
    lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //init viewmodel here
        viewModel = ViewModelProvider(this).get(LoginActivityViewModel::class.java)
        //neu nhu user da dang nhap thanh cong thi lay lai cai thong tin dang nhap lan truoc de fill vao textview
        edtEmail.setText(viewModel.getLoginInfo().mail)
        edtPassword.setText(viewModel.getLoginInfo().password)

        btnLoginFacebook.setOnClickListener {
            LoginManager.getInstance().logInWithReadPermissions(this, listOf("public_profile"));
        }

        btnLogin.setOnClickListener {
            Log.d("login", "Login here")
            if (isValidData()) {
                //REQUEST login firebase here
               viewModel.requestLogin(edtEmail.text.toString(), edtPassword.text.toString())
            } else {
                Toast.makeText(application, "Vui lòng nhập đúng!", Toast.LENGTH_LONG).show()
            }
        }

        //khi dang nhap thanh cong hoac that bai
        viewModel.isSuccessful.observe(this, Observer {
            //handle
            var message = ""
            if (it == true) {
                message = "Đăng nhập thành công!"
            } else {
                message = "Đăng nhập thất bại!"
            }

            Toast.makeText(application, message, Toast.LENGTH_LONG).show()
        })

//        btnLoginFacebook.setPermissions(Arrays.asList(EMAIL))

        callbackManager = CallbackManager.Factory.create()

        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) {
                    // App code
                    Toast.makeText(application, "Đăng nhập facebook thành công", Toast.LENGTH_LONG).show()

                }

                override fun onCancel() {
                    // App code
                    Toast.makeText(application, "Đăng nhập facebook cancel", Toast.LENGTH_LONG).show()
                }

                override fun onError(exception: FacebookException) {
                    // App code
                    Toast.makeText(application, "Đăng nhập facebook thất bại", Toast.LENGTH_LONG).show()
                }
            })


//        val accessToken = AccessToken.getCurrentAccessToken()
//        val isLoggedIn = accessToken != null && !accessToken.isExpired


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    //check valid data
    //true valid - failed invalid
    private fun isValidData(): Boolean {
        if (edtEmail.text.isNullOrEmpty() || edtPassword.text.isNullOrEmpty()) {
            return false
        }

        return true
    }
}
