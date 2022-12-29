package com.example.themoviebookingapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.themoviebookingapp.persistance.typeconverters.CinemaTypeConverter

@Entity(tableName = "dateCinemaAndTimeSlots")
@TypeConverters(
    CinemaTypeConverter::class
)
data class DateCinemaAndTimeSlotVO (

    @PrimaryKey
    @ColumnInfo(name = "date")
    var date: String = "",

    @ColumnInfo(name = "cinema")
    var cinemas : List<TimeSlotDataVO>? = null

)