package com.android.surveysaurus.service

import com.android.surveysaurus.model.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface SurveyAPI {

    //POST , GET ,UPDATE ,DELETE

    @POST("api/user/register")
    fun postSignUp(@Body signUpModel: SignUpModel): Call<SignUpModel>

    @POST("api/user/login")
    fun postLogin(@Body loginModel: LoginModel): Call<ResponseBody>

    @POST("api/survey/createSurvey")
    fun postCreateSurvey(@Body surveyModel: SurveyModel,@Header("authorization") token: String): Call<ResponseBody>

    @POST("api/survey/fillSurvey")
    fun postFillSurvey(@Body fillModel: FillModel ,@Header("authorization") token: String): Call<ResponseBody>

    @GET("api/user/mysurveys")
    fun getMySurvey(@Header("authorization") token: String): Call<Response>

    @GET("api/survey/sample")
    fun getSurveys(@Header("authorization") token: String): Call<Response>

    @GET("api/user/countries")
    fun getCountries(): Call<ResponseCountry>

    @POST("api/user/cities")
    fun getCity(@Body country: CountryModel): Call<ResponseCountry>

    @POST("api/survey/isfilled")
    fun isFilled(@Body title: IsFilledModel, @Header("authorization") token: String): Call<ResponseIsFilled>

    @POST("api/survey/getSurvey")
    fun getSurvey(@Body title: IsFilledModel): Call<ResponsePercent>

    @POST("api/profile/update")
    fun updateUser(@Body update: UpdateModel,@Header("authorization") token: String): Call<ResponseUpdate>

    @PUT("api/profile/updatepassword")
    fun updatePassword(@Body updatePasswordModel: PasswordModel,@Header("authorization") token: String): Call<ResponseBody>


    @GET("api/profile/getinfo")
    fun getUserInfo(@Header("authorization") token: String): Call<ResponseUser>
}