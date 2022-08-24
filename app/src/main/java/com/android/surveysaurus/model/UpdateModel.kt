package com.android.surveysaurus.model

import com.google.gson.annotations.SerializedName

data class UpdateModel(
    @SerializedName("userName")
    val name :String,

    @SerializedName("email")
    val email :String,

    @SerializedName("country")
    val country :String,

    @SerializedName("city")
    val city :String


)

