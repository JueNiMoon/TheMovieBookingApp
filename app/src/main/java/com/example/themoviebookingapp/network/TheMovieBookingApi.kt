package com.example.themoviebookingapp.network


import com.example.themoviebookingapp.data.vos.LogoutVO
import com.example.themoviebookingapp.data.vos.MovieVO
import com.example.themoviebookingapp.network.request.CheckOutResquest
import com.example.themoviebookingapp.network.responses.*
import com.example.themoviebookingapp.utils.*
import retrofit2.Call
import retrofit2.http.*

interface TheMovieBookingApi {


    @POST(API_LOGIN_WITH_EMAIL)
    @FormUrlEncoded
    fun loginWithEmail(
        @Field(EMAIL) email: String,
        @Field(PASSWORD) password: String
    ) : Call<UserDataResponse>

    @POST(API_SIGNIN_WITH_EMAIL)
    @FormUrlEncoded
    fun signInWithEmail(
        @Field(NAME) name : String,
        @Field(EMAIL) email: String,
        @Field(PHONE) phone : String,
        @Field(PASSWORD) password: String
    ) : Call<UserDataResponse>

    @GET(API_PROFILE)
    fun getProfile(
        @Header(AUTHORIZATION) Authorization: String,
    ) : Call<UserDataResponse>

    @GET(API_PROFILE)
    fun logout(
        @Header(AUTHORIZATION) Authorization: String,
    ) : Call<LogoutVO>


    @GET(API_GET_NOW_PLAYING)
    fun getNowPlayingMovies(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1,
    ) : Call<MovieListResponse>

    @GET(API_GET_GENRES)
    fun getGenres(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
    ): Call<GetGenresResponse>

    @GET(API_UPCOMING)
    fun getUpComingMovies(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
    ): Call<MovieListResponse>

    @GET("$API_GET_MOVIE_DETAILS/{movie_id}")
    fun getMovieDetails(
        @Path(MOVIE_ID) movieId: String,
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1
    ) : Call<MovieVO>

    @GET("$API_GET_CREDITS_BY_MOVIE/{movie_id}/credits")
    fun getCreditByMovie(
        @Path(MOVIE_ID) movieId: String,
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1
    ) : Call<GetCreditsByMovieResponse>

    @GET(API_GET_CINEMA_DAY_TIMESLOT)
    fun getCinemaDayTimeSlot(
        @Query(MOVIE_ID) movieId: String,
        @Query(DATE) date : String,
        @Header(AUTHORIZATION) authorization: String,
    ) : Call<TimeSlotResponse>

    @GET(API_GET_SEATING_PLAN)
    fun getSeatingPlan(
        @Query(CINEMA_DAY_TIMESLOT_ID) cinema_day_timeslot_id: String,
        @Query(BOOKING_DATE) booking_date : String,
        @Header(AUTHORIZATION) authorization: String,
    ) : Call<MovieSeatsVOResponse>

    @GET(API_GET_SNACK)
    fun getSnack(
        @Header(AUTHORIZATION) Authorization: String,
    ): Call<SnackDataResponse>

    @GET(API_GET_PAYMENT_METHOD)
    fun getPaymentMethod(
        @Header(AUTHORIZATION) Authorization: String,
    ): Call<PaymentMethodVOResponse>

    @POST(API_CREATE_NEW_CARD)
    @FormUrlEncoded
    fun createNewCard(
        @Field(CARD_NUMBER) card_number: String,
        @Field(CARD_HOLDER) card_holder: String,
        @Field(EXPIRATION_DATE) expiration_date : String,
        @Field(CVC) cvc:String,
        @Header(AUTHORIZATION) Authorization: String
    ) : Call<CardResponse>

    @POST(API_CHECK_OUT)
    fun checkOut(
        @Header(AUTHORIZATION) Authorization: String,
        @Body checkoutRequest : CheckOutResquest
    ) : Call<CheckOutResponse>
}