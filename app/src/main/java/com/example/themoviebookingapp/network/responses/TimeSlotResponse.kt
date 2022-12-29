package com.example.themoviebookingapp.network.responses

import com.example.themoviebookingapp.data.vos.TimeSlotDataVO
import com.google.gson.annotations.SerializedName

data class TimeSlotResponse(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: MutableList<TimeSlotDataVO>?,
)