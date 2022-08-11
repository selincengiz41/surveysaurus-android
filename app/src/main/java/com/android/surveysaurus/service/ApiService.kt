package com.android.surveysaurus.service

import com.android.surveysaurus.model.LoginModel
import com.android.surveysaurus.model.LoginedModel
import com.android.surveysaurus.model.SignUpModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiService {
    fun postLogin(loginModel: LoginModel, onResult: (LoginedModel?) -> Unit){
        val retrofit = ServiceBuilder.buildService(SurveyAPI::class.java)
        retrofit.postLogin(loginModel).enqueue(
            object : Callback<LoginedModel> {
                override fun onFailure(call: Call<LoginedModel>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<LoginedModel>, response: Response<LoginedModel>) {
                    val loginedUser = response.body()
                    onResult(loginedUser)
                }
            }
        )
    }

    fun postSignUp(signUpModel: SignUpModel, onResult: (SignUpModel?) -> Unit){
        val retrofit = ServiceBuilder.buildService(SurveyAPI::class.java)
        retrofit.postSignUp(signUpModel).enqueue(
            object : Callback<SignUpModel> {
                override fun onFailure(call: Call<SignUpModel>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<SignUpModel>, response: Response<SignUpModel>) {
                    val signUpedUser = response.body()
                    onResult(signUpedUser)
                }
            }
        )
    }


}