package com.example.themoviebookingapp.persistance.typeconverters

import androidx.room.TypeConverter
import com.example.themoviebookingapp.data.vos.TimeSlotDataVO
import com.example.themoviebookingapp.data.vos.TimeSlotVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CinemaTypeConverter {
    @TypeConverter
    fun toString(timeSlotList : List<TimeSlotDataVO>?) : String{
        return Gson().toJson(timeSlotList)
    }

    @TypeConverter
    fun toTimeSlotListType(timeSlotListJsonString : String): List<TimeSlotDataVO>?{
        val timeSlotListType = object : TypeToken<List<TimeSlotDataVO>?>() {}.type
        return Gson().fromJson(timeSlotListJsonString,timeSlotListType)
    }
}