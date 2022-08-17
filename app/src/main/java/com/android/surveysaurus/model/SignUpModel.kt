package com.android.surveysaurus.model

import com.google.gson.annotations.SerializedName

data class SignUpModel(
    @SerializedName("userName")
    val name :String,

    @SerializedName("email")
    val email :String,

    @SerializedName("password")
    val password :String,

    @SerializedName("gender")
    val gender :String,

    @SerializedName("country")
    val country :String,

    @SerializedName("city")
    val city :String


)



