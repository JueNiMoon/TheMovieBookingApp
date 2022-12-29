package com.example.themoviebookingapp.network.responses

import com.example.themoviebookingapp.data.vos.UserDataVO
import com.google.gson.annotations.SerializedName

data class UserDataResponse(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: UserDataVO?,
//    @SerializedName("data")
//    val data: List<UserDataVO>?,
    @SerializedName("token")
    val token: String?
)