package com.example.jetpackcomposeinstagram.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {


    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email
    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password
    private val _isLogingEnable = MutableLiveData<Boolean>()
    val isLogingEnable: LiveData<Boolean> = _isLogingEnable

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _isLogingEnable.value = enableLogin(email, password)
    }

    fun enableLogin(email: String, password: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6


}