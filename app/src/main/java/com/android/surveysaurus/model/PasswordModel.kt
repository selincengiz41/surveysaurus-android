package com.android.surveysaurus.model

import com.google.gson.annotations.SerializedName

data class PasswordModel (
        @SerializedName("oldPassword")
        val oldPassword :String,
        @SerializedName("newPassword")
        val newPassword :String,

        )