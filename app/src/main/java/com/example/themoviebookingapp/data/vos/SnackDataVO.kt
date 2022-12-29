package com.example.themoviebookingapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "snacks")
data class SnackDataVO (

    var isSelected : Boolean=false,
    var count : Int = 0,

    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id : Int?,

    @SerializedName("name")
    @ColumnInfo(name = "name")
    val name : String?,

    @SerializedName("description")
    @ColumnInfo(name = "description")
    val description : String?,

    @SerializedName("price")
    @ColumnInfo(name = "price")
    val price : Double?,

    @SerializedName("image")
    @ColumnInfo(name = "image")
    val image : String?,

    @SerializedName("unit_price")
    @ColumnInfo(name = "unit_price")
    val unitPrice : Double?,

    @SerializedName("quantity")
    @ColumnInfo(name = "quantity")
    val quantity : Int?,

    @SerializedName("total_price")
    @ColumnInfo(name = "total_price")
    val totalPrice : Double?,

)