package com.zentedu.loginfirebasesample.view.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.zentedu.loginfirebasesample.view.DBManager.DBManager
import com.zentedu.loginfirebasesample.view.model.LoginModel


class LoginActivityRepository(val application: Application) {
    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    val isSuccessful = MutableLiveData<Boolean>()


    fun requestLogin(mail: String, password: String) {
    //call fireBase service
        firebaseAuth.signInWithEmailAndPassword(mail, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d("login", "login success")
                    isSuccessful.value = it.isSuccessful
                    //luu lai mail va password vao app de lan sau su dung
                    val loginInfo = LoginModel(mail, password)
                    val dbManager = DBManager(application)
                    dbManager.saveLoginInfo(loginInfo)
                } else {
                    Log.d("login", "login failed")
                    isSuccessful.value = false
                    //xoa thong tin dang nhap di
                    val loginInfo = LoginModel(mail, "")
                    val dbManager = DBManager(application)
                    dbManager.saveLoginInfo(loginInfo)
                }
            }

    }

    //ham lay du lieu
    fun getLoginInfo(): LoginModel {
        val dbManager = DBManager(application)
        return dbManager.getLoginInfo()
    }

}