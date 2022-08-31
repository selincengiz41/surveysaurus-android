package com.android.surveysaurus.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FillModel
 (
    @SerializedName("title")
    val title :String,

    @SerializedName("answer")
    val answer :Number,

    ):Serializable