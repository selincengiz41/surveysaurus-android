package com.android.surveysaurus.service

import com.android.surveysaurus.model.LoginModel
import com.android.surveysaurus.model.LoginedModel
import com.android.surveysaurus.model.SignUpModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SurveyAPI {

    //POST , GET ,UPDATE ,DELETE

    @POST("register")
    fun postSignUp(@Body signUpModel: SignUpModel): Call<SignUpModel>

    @POST("login")
    fun postLogin(@Body loginModel: LoginModel): Call<LoginedModel>

}