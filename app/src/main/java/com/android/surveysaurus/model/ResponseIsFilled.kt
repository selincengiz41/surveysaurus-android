package com.android.surveysaurus.model

import java.io.Serializable

data class ResponseIsFilled(
    val `data`: DataX,
    val message: String
):Serializable