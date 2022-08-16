package com.android.surveysaurus.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SurveyModel (


    @SerializedName("question")
    val question :String,

    @SerializedName("description")
    val description :String,

    @SerializedName("options")
    val options :ArrayList<String>,


        ):Serializable