package com.android.surveysaurus.service

import com.android.surveysaurus.model.*
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
        retrofit.postCreateSurvey(surveyModel, LoginSingleton.token).enqueue(
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

    fun getMySurvey(onResult: (ArrayList<ListedSurvey>?) -> Unit) {
        val retrofit = ServiceBuilder.buildService(SurveyAPI::class.java)
        retrofit.getMySurvey(LoginSingleton.token).enqueue(
            object : Callback<com.android.surveysaurus.model.Response> {
                override fun onFailure(
                    call: Call<com.android.surveysaurus.model.Response>,
                    t: Throwable
                ) {
                    println(t.message)
                    onResult(null)
                }

                override fun onResponse(
                    call: Call<com.android.surveysaurus.model.Response>,
                    response: Response<com.android.surveysaurus.model.Response>
                ) {
                    var responseList = response.body()!!.data!!.surveys!!
                    var listedSurveys: ArrayList<ListedSurvey> = ArrayList()
                    for (item in 0 until responseList.size) {
                        var listedSurvey: ListedSurvey = ListedSurvey(
                            responseList.get(item).choices,
                            responseList.get(item).counts,
                            responseList.get(item).question,
                            responseList.get(item).title

                        )
                        listedSurveys.add(listedSurvey)
                    }

                    println(response.message())

                    onResult(listedSurveys)
                }
            }
        )
    }


    fun getSurveys(onResult: (ArrayList<ListedSurvey>?) -> Unit) {
        val retrofit = ServiceBuilder.buildService(SurveyAPI::class.java)
        retrofit.getSurveys(LoginSingleton.token).enqueue(
            object : Callback<com.android.surveysaurus.model.Response> {
                override fun onFailure(
                    call: Call<com.android.surveysaurus.model.Response>,
                    t: Throwable
                ) {
                    println(t.message)
                    onResult(null)
                }

                override fun onResponse(
                    call: Call<com.android.surveysaurus.model.Response>,
                    response: Response<com.android.surveysaurus.model.Response>
                ) {
                    var responseList = response.body()!!.data!!.surveys!!
                    var listedSurveys: ArrayList<ListedSurvey> = ArrayList()
                    for (item in 0 until responseList.size) {
                        var listedSurvey: ListedSurvey = ListedSurvey(
                            responseList.get(item).choices,
                            responseList.get(item).counts,
                            responseList.get(item).question,
                            responseList.get(item).title

                        )
                        listedSurveys.add(listedSurvey)
                    }

                    println(response.message())

                    onResult(listedSurveys)
                }
            }
        )
    }

    fun postFillSurvey(fillModel: FillModel, onResult: (ResponseBody?) -> Unit) {
        val retrofit = ServiceBuilder.buildService(SurveyAPI::class.java)
        retrofit.postFillSurvey(fillModel, LoginSingleton.token).enqueue(
            object : Callback<ResponseBody> {
                override fun onFailure(
                    call: Call<ResponseBody>,
                    t: Throwable
                ) {
                    println(t.message)
                    onResult(null)
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    var filled = response.body()

                    println(response.message())

                    onResult(filled)
                }
            }
        )
    }

}