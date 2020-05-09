package com.zentedu.loginfirebasesample.view.DBManager

import android.app.Application
import android.content.Context
import com.zentedu.loginfirebasesample.view.model.LoginModel

class DBManager(val application: Application) {

    //ham luu thong tin dang nhap
    fun saveLoginInfo(loginModel: LoginModel) {
        val sharedPreference =  application.getSharedPreferences("LearningDB", Context.MODE_PRIVATE)
        var editor = sharedPreference.edit()
        editor.putString("login_mail", loginModel.mail)
        editor.putString("login_password", loginModel.password)
        editor.commit()
    }

    //Ham lay du lieu dang nhap
    fun getLoginInfo(): LoginModel {
        val sharedPreference =  application.getSharedPreferences("LearningDB",Context.MODE_PRIVATE)
        val mail = sharedPreference.getString("login_mail", "")
        val password = sharedPreference.getString("login_password", "")
        return LoginModel(mail!!, password!!)
    }
}