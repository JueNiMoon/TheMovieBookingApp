package com.example.themoviebookingapp.persistance.typeconverters

import androidx.room.TypeConverter
import com.example.themoviebookingapp.data.vos.ProductionCountriesVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductionCountryTypeConverter {
    @TypeConverter
    fun toString(productionCountriesList : List<ProductionCountriesVO>?) : String{
        return Gson().toJson(productionCountriesList)
    }

    @TypeConverter
    fun toProductionCountriesListType(productionCountriesListJsonString : String): List<ProductionCountriesVO>?{
        val productionCountriesListType = object : TypeToken<List<ProductionCountriesVO>?>() {}.type
        return Gson().fromJson(productionCountriesListJsonString,productionCountriesListType)
    }
}