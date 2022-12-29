package com.example.themoviebookingapp.persistance.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themoviebookingapp.data.vos.SnackDataVO

@Dao
interface SnackDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSnackList(snackList : List<SnackDataVO>)

    @Query("SELECT * FROM snacks")
    fun getSnackByToken() : List<SnackDataVO>


}