package com.zentedu.loginfirebasesample.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.zentedu.loginfirebasesample.view.model.LoginModel
import com.zentedu.loginfirebasesample.view.repository.LoginActivityRepository

class LoginActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val repository  = LoginActivityRepository(application)
    val isSuccessful : LiveData<Boolean>

    init {
        isSuccessful = repository.isSuccessful
    }

    //request login to filebase
    fun requestLogin(mail: String, password: String) {
        repository.requestLogin(mail, password)
    }

    //lay thong tin dang nhap
    fun getLoginInfo() : LoginModel {
        return repository.getLoginInfo()
    }

}