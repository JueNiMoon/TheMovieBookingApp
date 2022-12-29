package com.example.themoviebookingapp.persistance.typeconverters

import androidx.room.TypeConverter
import com.example.themoviebookingapp.data.vos.MovieVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MovieTypeConverter {
    @TypeConverter
    fun toString(movieList : List<MovieVO>?) : String{
        return Gson().toJson(movieList)
    }

    @TypeConverter
    fun toProductionCompaniesListType(movieListJsonString : String): List<MovieVO>?{
        val movieListType = object : TypeToken<List<MovieVO>?>() {}.type
        return Gson().fromJson(movieListJsonString,movieListType)
    }
}