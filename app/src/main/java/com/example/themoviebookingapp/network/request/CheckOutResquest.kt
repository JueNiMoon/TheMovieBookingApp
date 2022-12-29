package com.example.themoviebookingapp.network.request

import com.example.themoviebookingapp.data.vos.OrderSnackVO
import com.example.themoviebookingapp.data.vos.SnackParcelize
import com.google.gson.annotations.SerializedName

data class CheckOutResquest (

    @SerializedName("cinema_day_timeslot_id")
    var cinemaDayTimeslotId : Int?,
    @SerializedName("row")
    var row : String?,
    @SerializedName("seat_number")
    var seatNumber : String?,
    @SerializedName("booking_date")
    var bookingDate : String?,
    @SerializedName("total_price")
    var totalPrice : Double?,
    @SerializedName("movie_id")
    var movieId : Int?,
    @SerializedName("card_id")
    var cardId : Int?,
    @SerializedName("cinema_id")
    var cinemaId : Int?,
    @SerializedName("snacks")
    var snacks : ArrayList<SnackParcelize>?,

    )