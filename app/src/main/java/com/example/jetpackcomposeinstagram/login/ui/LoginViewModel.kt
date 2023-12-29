package com.example.jetpackcomposeinstagram.login.ui

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposeinstagram.login.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUsecase: LoginUseCase) : ViewModel() {

   // val loginUsecase=LoginUseCase()


    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email
    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password
    private val _isLogingEnable = MutableLiveData<Boolean>()
    val isLogingEnable: LiveData<Boolean> = _isLogingEnable

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> =_isLoading

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _isLogingEnable.value = enableLogin(email, password)
        //loginUsecase("","")
    }

    fun enableLogin(email: String, password: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6

    fun onLoginSelected(){
        viewModelScope.launch{
            _isLoading.value=true
            val result=loginUsecase(email.value!!,password.value!!)
            if(result){
                //Navega a la siguiente pantalla
                Log.i("JoseLuis","resultado ok")
            }
            _isLoading.value=false
        }
    }


}