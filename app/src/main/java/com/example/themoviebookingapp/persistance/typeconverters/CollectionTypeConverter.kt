package com.example.themoviebookingapp.persistance.typeconverters

import androidx.room.TypeConverter
import com.example.themoviebookingapp.data.vos.CollectionVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CollectionTypeConverter {
    @TypeConverter
    fun toString(collection : CollectionVO?) : String{
        return Gson().toJson(collection)
    }

    @TypeConverter
    fun toCollectionType(collectionJsonString : String): CollectionVO?{
        val collectionType = object : TypeToken<CollectionVO?>() {}.type
        return Gson().fromJson(collectionJsonString,collectionType)
    }
}