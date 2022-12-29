package com.example.themoviebookingapp.network.responses

import com.example.themoviebookingapp.data.vos.CardVO
import com.google.gson.annotations.SerializedName

data class CardResponse (
    @SerializedName("code")
    val code : Int?,
    @SerializedName("message")
    val message : String?,
    @SerializedName("data")
    val data : List<CardVO>?,
)