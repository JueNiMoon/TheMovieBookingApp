package com.example.themoviebookingapp.persistance.typeconverters

import androidx.room.TypeConverter
import com.example.themoviebookingapp.data.vos.SpokenLanguagesVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SpokenLanguagesTypeConverter {

    @TypeConverter
    fun toString(spokenLanguageList : List<SpokenLanguagesVO>?) : String{
        return Gson().toJson(spokenLanguageList)
    }

    @TypeConverter
    fun toSpokenLanguageListType(spokenLanguageListJsonString : String): List<SpokenLanguagesVO>?{
        val spokenLanguageListType = object : TypeToken<List<SpokenLanguagesVO>?>() {}.type
        return Gson().fromJson(spokenLanguageListJsonString,spokenLanguageListType)
    }
}