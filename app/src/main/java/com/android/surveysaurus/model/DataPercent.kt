package com.android.surveysaurus.model

data class DataPercent (
    val choices: ArrayList<String>,
    val counts: ArrayList<Int>,
    val question: String,
    val percent: ArrayList<Float>
        )