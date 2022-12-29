package com.example.themoviebookingapp.persistance.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themoviebookingapp.data.vos.DateCinemaAndTimeSlotVO
import com.example.themoviebookingapp.data.vos.TimeSlotDataVO

@Dao
interface DateCinemaTimeSlotDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTimeSlotList(timeSlotList : DateCinemaAndTimeSlotVO?)

    @Query("SELECT * FROM dateCinemaAndTimeSlots WHERE date = :date")
    fun getTimeSlotByDate(date : String) : DateCinemaAndTimeSlotVO?

//    @Query("SELECT * FROM dateCinemaAndTimeSlots")
//    fun getTimeSlotList() : List<DateCinemaAndTimeSlotVO>
}