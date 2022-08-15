package com.android.surveysaurus.model

import com.google.gson.annotations.SerializedName

class LoginedModel (
    @SerializedName("name")
    val name :String,

    @SerializedName("email")
    val email :String,

    @SerializedName("gender")
    val gender :String,

    @SerializedName("country")
    val country :String,

    @SerializedName("city")
    val city :String
)