package com.android.surveysaurus.service

import com.android.surveysaurus.model.LoginModel
import com.android.surveysaurus.model.SignUpModel
import com.android.surveysaurus.model.SurveyModel
import com.android.surveysaurus.singleton.LoginSingleton
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiService {
    fun postLogin(loginModel: LoginModel, onResult: (ResponseBody?) -> Unit) {
        val retrofit = ServiceBuilder.buildService(SurveyAPI::class.java)
        retrofit.postLogin(loginModel).enqueue(
            object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    println(t.message)
                    onResult(null)

                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    val loginedUser = response.body()


                    println(response.message())
                    onResult(loginedUser)
                }
            }
        )
    }

    fun postSignUp(signUpModel: SignUpModel, onResult: (SignUpModel?) -> Unit) {
        val retrofit = ServiceBuilder.buildService(SurveyAPI::class.java)
        retrofit.postSignUp(signUpModel).enqueue(
            object : Callback<SignUpModel> {
                override fun onFailure(call: Call<SignUpModel>, t: Throwable) {
                    println(t.message)
                    onResult(null)
                }

                override fun onResponse(call: Call<SignUpModel>, response: Response<SignUpModel>) {
                    val signUpedUser = response.body()

                    println(response.message())

                    onResult(signUpedUser)
                }
            }
        )
    }


    fun postCreateSurvey(surveyModel: SurveyModel, onResult: (ResponseBody?) -> Unit) {
        val retrofit = ServiceBuilder.buildService(SurveyAPI::class.java)
        retrofit.postCreateSurvey(LoginSingleton.token,surveyModel).enqueue(
            object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    println(t.message)
                    onResult(null)
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    val createdSurvey = response.body()

                    println(response.message())

                    onResult(createdSurvey)
                }
            }
        )
    }


}