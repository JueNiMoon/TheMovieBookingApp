package com.example.themoviebookingapp.persistance.typeconverters

import androidx.room.TypeConverter
import com.example.themoviebookingapp.data.vos.GenreVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenresTypeConverter {
    @TypeConverter
    fun toString(genresList : List<GenreVO>?) : String{
        return Gson().toJson(genresList)
    }

    @TypeConverter
    fun toGenresListType(genresListJsonString : String): List<GenreVO>?{
        val genresListType = object : TypeToken<List<GenreVO>?>() {}.type
        return Gson().fromJson(genresListJsonString,genresListType)
    }
}