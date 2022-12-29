package com.example.themoviebookingapp.data.vos

import com.google.gson.annotations.SerializedName

data class TimeSlotVO (

    var isSelected : Boolean = false,
    @SerializedName("cinema_day_timeslot_id")
    val cinemaDayTimeslotId : Int?,
    @SerializedName("start_time")
    val startTime : String?,
)