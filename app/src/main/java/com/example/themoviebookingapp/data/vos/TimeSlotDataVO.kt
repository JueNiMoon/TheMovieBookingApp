package com.example.themoviebookingapp.data.vos

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName



data class TimeSlotDataVO(
    @SerializedName("cinema_id")
    val cinema_id: Int?,

    @SerializedName("cinema")
    val cinema: String?,

    @SerializedName("timeslots")
    val timeslots: List<TimeSlotVO>,

    @ColumnInfo(name = "date")
    var date : String?,

    )