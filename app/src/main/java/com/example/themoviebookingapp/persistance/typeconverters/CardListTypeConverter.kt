package com.example.themoviebookingapp.persistance.typeconverters

import androidx.room.TypeConverter
import com.example.themoviebookingapp.data.vos.CardVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CardListTypeConverter {
    @TypeConverter
    fun toString(cardList : List<CardVO>?) : String{
        return Gson().toJson(cardList)
    }

    @TypeConverter
    fun toCardList(cardListJsonString : String): List<CardVO>?{
        val cardListType = object : TypeToken<List<CardVO>?>() {}.type
        return Gson().fromJson(cardListJsonString,cardListType)
    }
}