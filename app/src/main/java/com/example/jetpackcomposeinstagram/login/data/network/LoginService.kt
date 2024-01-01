package com.example.jetpackcomposeinstagram.login.data.network

import com.example.jetpackcomposeinstagram.core.network.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginService  @Inject constructor(){
    private val retrofit = RetrofitHelper.getRefrofit()

    suspend fun doLogin(user: String, password: String): Boolean {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(LoginClient::class.java).doLogin()
            response.body()?.success ?: false

        }
    }


}