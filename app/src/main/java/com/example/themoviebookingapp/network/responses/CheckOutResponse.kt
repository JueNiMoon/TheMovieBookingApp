package com.example.themoviebookingapp.network.responses

import com.example.themoviebookingapp.data.vos.CheckOutDataVO
import com.google.gson.annotations.SerializedName

data class CheckOutResponse(
    @SerializedName("code")
    val code : Int?,
    @SerializedName("message")
    val message : String?,
    @SerializedName("data")
    val data : CheckOutDataVO?,
)