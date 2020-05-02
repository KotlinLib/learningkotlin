package com.zentedu.loginfirebasesample.view.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.ViewModelProvider
import com.zentedu.loginfirebasesample.R
import com.zentedu.loginfirebasesample.view.viewmodel.LoginActivityViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //init viewmodel here
            viewModel = ViewModelProvider(this).get(LoginActivityViewModel::class.java)
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
