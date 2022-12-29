package com.example.themoviebookingapp.data.vos

import com.google.gson.annotations.SerializedName

data class LogoutVO(
    @SerializedName("code")
    val code : Int?,
    @SerializedName("message")
    val message : String?,
)