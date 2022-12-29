package com.example.themoviebookingapp.network.dataagents

import com.example.themoviebookingapp.data.vos.*
import com.example.themoviebookingapp.network.request.CheckOutResquest
import com.example.themoviebookingapp.network.responses.UserDataResponse
import com.example.themoviebookingapp.utils.AUTHORIZATION
import retrofit2.http.Header
import java.util.*

interface MovieBookingDataAgent {



    fun logInWithEmail(
        email: String,
        password: String,
        onSuccess: (Pair<UserDataVO, String>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun singInWithEmail(
        name:String,
        email: String,
        phone: String,
        password: String,
        onSuccess: (Pair<UserDataVO, String>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getProfile(
        authorization: String,
        onSuccess: (UserDataVO) -> Unit,
        onFailure: (String) -> Unit
    )

    fun logout(
        authorization: String,
        onSuccess: (LogoutVO) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getNowPlayingMovie(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getGenres(
        onSuccess: (List<GenreVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getUpComming(
        onSuccess: (List<MovieVO>) -> kotlin.Unit,
        onFailure: (String) -> Unit
    )

    fun getMovieDetails(
        movieId:String,
        onSuccess : (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getCreditsByMovie(
        movieId: String,
        onSuccess: (List<ActorVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getCinemaDayTimeSlot(
        movieId: String,
        date : String,
        authorization: String,
        onSuccess: (List<TimeSlotDataVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getCinemSeatingPlan(
        cinema_day_timeslot_id : String,
        bookingDate: String,
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

    fun createNewCard(
        cardNo : String,
        cardHolder : String,
        expirationDate : String,
        cvc : String,
        authorization: String,
        onSuccess: (List<CardVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun checkOut(
        checkoutRequest : CheckOutResquest,
        authorization: String,
        onSuccess: (CheckOutDataVO) -> Unit,
        onFailure: (String) -> Unit
    )
}