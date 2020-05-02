package com.zentedu.loginfirebasesample.view.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth

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
                } else {
                    Log.d("login", "login failed")
                    isSuccessful.value = false
                }
            }

    }

}