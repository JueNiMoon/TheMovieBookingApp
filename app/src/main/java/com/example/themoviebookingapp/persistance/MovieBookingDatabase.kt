package com.example.themoviebookingapp.persistance

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.themoviebookingapp.data.vos.*
import com.example.themoviebookingapp.persistance.daos.*

@Database(entities = [
    UserDataVO::class,
    MovieVO::class,
    ActorVO::class,
    DateCinemaAndTimeSlotVO::class,
    SnackDataVO::class,
    PaymentMethodVO::class], version = 7, exportSchema = false)
abstract class MovieBookingDatabase : RoomDatabase() {

    companion object{
        const val DB_NAME = "THE_MOVIE_BOOKING_DB"
        var dbInstance: MovieBookingDatabase? = null

        fun getDBInstance(context: Context): MovieBookingDatabase?{
            when (dbInstance){
                null -> {
                    dbInstance = Room.databaseBuilder(context,MovieBookingDatabase::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            return dbInstance
        }
    }


    abstract fun userDao() : UserDao
    abstract fun movieDao() : MovieDao
    abstract fun dateCinemaTimeSlotDao() : DateCinemaTimeSlotDao
    abstract fun snackDao() : SnackDao
    abstract fun paymentDao() : PaymentDao
}