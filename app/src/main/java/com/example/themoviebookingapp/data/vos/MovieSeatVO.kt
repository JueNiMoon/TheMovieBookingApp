package com.example.themoviebookingapp.data.vos

import com.google.gson.annotations.SerializedName


data class MovieSeatVO(

    var isSelected : Boolean = false,

    @SerializedName("id")
    val id : Int?,

    @SerializedName("type")
    val type : String?,

    @SerializedName("seat_name")
    val seatName: String = "",

    @SerializedName("symbol")
    val symbol : String?,

    @SerializedName("price")
    val price : Double?,

//    val title: String = "",
//    private val type: String="",

){
    fun isMovieSeatAvailable(): Boolean? {
        return type === SEAT_TYPE_AVAILABLE
    }
    fun isMovieSeatTaken(): Boolean?{
        return type === SEAT_TYPE_TAKEN
    }

    fun isMovieSeatRowTitle(): Boolean? {
        return type === SEAT_TYPE_TEXT
    }
}

const val SEAT_TYPE_AVAILABLE = "available";
const val SEAT_TYPE_TAKEN = "taken";
const val SEAT_TYPE_TEXT = "text";
const val SEAT_TYPE_EMPTY = "space";