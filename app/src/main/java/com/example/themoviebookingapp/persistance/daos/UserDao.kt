package com.example.themoviebookingapp.persistance.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themoviebookingapp.data.vos.UserDataVO

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserData(userData : UserDataVO?)

    @Query("DELETE FROM user_data")
    fun deleteUser()

    @Query("SELECT * FROM user_data WHERE email = :email")
    fun getUserInfoByEmail(email: String) : UserDataVO?

    @Query("SELECT * FROM user_data LIMIT 1")
    fun getUserInfo() : UserDataVO?
}