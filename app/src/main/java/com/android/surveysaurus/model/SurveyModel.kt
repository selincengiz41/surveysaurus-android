package com.android.surveysaurus.model

import android.widget.Button
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SurveyModel (

    @SerializedName("question")
    val question :String,

    @SerializedName("title")
    val title :String,

    @SerializedName("choice")
    val choice :ArrayList<String>,

        ):Serializable