package com.example.themoviebookingapp.persistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreIdsTypeConverter {
    @TypeConverter
    fun toString(genreIdsList : List<Int>?) : String{
        return Gson().toJson(genreIdsList)
    }

    @TypeConverter
    fun toGenreIdsListType(genreIdsListJsonString : String): List<Int>?{
        val genreIdsListType = object : TypeToken<List<Int>?>() {}.type
        return Gson().fromJson(genreIdsListJsonString,genreIdsListType)
    }
}