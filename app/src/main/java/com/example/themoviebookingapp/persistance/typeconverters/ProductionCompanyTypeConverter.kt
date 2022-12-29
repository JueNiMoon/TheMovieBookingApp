package com.example.themoviebookingapp.persistance.typeconverters

import androidx.room.TypeConverter
import com.example.themoviebookingapp.data.vos.ProductionCompaniesVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductionCompanyTypeConverter {
    @TypeConverter
    fun toString(productionCompaniesList : List<ProductionCompaniesVO>?) : String{
        return Gson().toJson(productionCompaniesList)
    }

    @TypeConverter
    fun toProductionCompaniesListType(productionCompaniesListJsonString : String): List<ProductionCompaniesVO>?{
        val productionCompaniesListType = object : TypeToken<List<ProductionCompaniesVO>?>() {}.type
        return Gson().fromJson(productionCompaniesListJsonString,productionCompaniesListType)
    }
}