package com.example.themoviebookingapp.models

import com.example.themoviebookingapp.data.vos.*
import com.example.themoviebookingapp.network.request.CheckOutResquest
import com.example.themoviebookingapp.network.responses.UserDataResponse

interface MovieBookingModel {




    fun loginWithEmail(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

    fun signInWtihEmail(
        name:String,
        email: String,
        phone: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

    fun getProfile(
        authorization : String,
        onSuccess: (UserDataVO) -> Unit,
        onFailure: (String) -> Unit
    )

    fun logout(
        authorization : String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

    fun getNowPlayingMovie(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getGenre(
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit

    )

    fun getUpCommingMovie(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getMovieDetails(
        movieId : String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getCreditsByMovie(
        movieId: String,
        onSuccess: (List<ActorVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getCinemaDayTimeSlot(
        movieId: String,
        authorization: String,
        date : String,
        onSuccess: (List<TimeSlotDataVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getCinemSeatingPlan(
        cinema_day_timeslot_id : String,
        bookingDate : String,
        authorization: String,
        onSuccess: (List<MovieSeatVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getSnack(
        authorization: String,
        onSuccess: (List<SnackDataVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getPaymentMethod(
        authorization: String,
        onSuccess: (List<PaymentMethodVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getCards(
        authorization : String,
        onSuccess: (List<CardVO>) -> Unit,
        onFailure: (String) -> Unit
    )



    fun createNewCard(
        card_number : String,
        card_holder : String,
        expiration_date : String,
        cvc : String,
        authorization: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

    fun checkOut(
        cinemaTimeSlotId : Int,
        row : String,
        seatNumber:String,
        bookingDate:String,
        totalAmount : Double,
        movieId : Int,
        selectedCardId : Int,
        cinemaId : Int,
        snackList : ArrayList<SnackParcelize>,
        authorization: String,
        //checkOutResquest: CheckOutResquest,
        onSuccess: (CheckOutDataVO) -> Unit,
        onFailure: (String) -> Unit
    )
}