package com.android.surveysaurus.model

import java.io.Serializable

data class ListedSurvey(
    val choices: ArrayList<String>,
    val counts: ArrayList<Int>,
    val question: String,
    val title: String


):Serializable
