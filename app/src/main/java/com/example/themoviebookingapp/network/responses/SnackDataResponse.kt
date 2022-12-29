package com.example.themoviebookingapp.network.responses

import com.example.themoviebookingapp.data.vos.SnackDataVO
import com.google.gson.annotations.SerializedName

data class SnackDataResponse(
    @SerializedName("code")
    val code : Int?,
    @SerializedName("message")
    val message : String?,
    @SerializedName("data")
    val data : List<SnackDataVO>?,
)