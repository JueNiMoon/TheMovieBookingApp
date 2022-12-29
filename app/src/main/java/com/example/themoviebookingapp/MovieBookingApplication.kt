package com.example.themoviebookingapp

import android.app.Application
import com.example.themoviebookingapp.models.MovieBookingModelImpl

class MovieBookingApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        MovieBookingModelImpl.initDatabase(applicationContext)
    }
}