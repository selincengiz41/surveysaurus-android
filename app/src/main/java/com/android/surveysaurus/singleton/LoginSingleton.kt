package com.android.surveysaurus.singleton

import androidx.navigation.Navigation
import com.android.surveysaurus.fragment.SignUpFragmentDirections
import com.android.surveysaurus.model.LoginModel
import com.android.surveysaurus.model.SignUpModel
import com.android.surveysaurus.service.SurveyAPI
import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object LoginSingleton {
    var isLogin: Boolean = true
    var email: String = ""
    var name: String = ""
    var gender: String = ""
    var city: String = ""
    var country: String = ""
    var token: String = ""


}