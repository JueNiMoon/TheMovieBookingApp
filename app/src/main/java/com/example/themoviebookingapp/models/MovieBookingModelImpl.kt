package com.example.themoviebookingapp.models

import android.content.Context
import com.example.themoviebookingapp.data.vos.*
import com.example.themoviebookingapp.network.dataagents.MovieBookingDataAgent
import com.example.themoviebookingapp.network.dataagents.RetrofitDataAgentImpl
import com.example.themoviebookingapp.network.request.CheckOutResquest
import com.example.themoviebookingapp.network.responses.UserDataResponse
import com.example.themoviebookingapp.persistance.MovieBookingDatabase

object MovieBookingModelImpl : MovieBookingModel {
    var genreList : List<GenreVO>? = null
    val mMovieBookingDataAgent: MovieBookingDataAgent = RetrofitDataAgentImpl

    //Database
    private var mMovieBookingDatabase: MovieBookingDatabase? = null

    fun initDatabase(context: Context){
        mMovieBookingDatabase = MovieBookingDatabase.getDBInstance(context)
    }

    override fun loginWithEmail(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
            mMovieBookingDataAgent.logInWithEmail(
                email = email,
                password = password,
                onSuccess = {
                    it.first.token = it.second
                    mMovieBookingDatabase?.userDao()?.insertUserData(it.first)
                    onSuccess()
                },
                onFailure = onFailure
            )
    }

    override fun signInWtihEmail(
        name: String,
        email: String,
        phone: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieBookingDataAgent.singInWithEmail(
            name = name,
            email = email,
            phone = phone,
            password = password,
            onSuccess = {
                it.first.token = it.second
                mMovieBookingDatabase?.userDao()?.insertUserData(it.first)
                onSuccess()
            },
            onFailure = onFailure
        )
    }

    public fun getToken( ) : String {
        return "Bearer "+ mMovieBookingDatabase?.userDao()?.getUserInfo()?.token ?: ""
    }

    override fun getProfile(
        authorization: String,
        onSuccess: (UserDataVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        var userInfo = mMovieBookingDatabase?.userDao()?.getUserInfo()
        userInfo?.let {
            onSuccess(it)
        }
    }

    override fun logout(
        authorization: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieBookingDataAgent.logout(
            authorization = authorization,
            onSuccess = {
                mMovieBookingDatabase?.userDao()?.deleteUser()
                onSuccess()
            },
            onFailure = onFailure
        )
    }


    override fun getNowPlayingMovie(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        //database
        onSuccess(mMovieBookingDatabase?.movieDao()?.getMovieListByType(NOW_PLAYING) ?: listOf())

        //Network
        mMovieBookingDataAgent.getNowPlayingMovie(
            onSuccess = {
                it?.let {
                    it.forEach { movie->
                        movie.type = NOW_PLAYING
                    }
                    mMovieBookingDatabase?.movieDao()?.insertMovieList(it)
                    onSuccess(it)
                }
            },
            onFailure = onFailure
        )
    }

    override fun getGenre(onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        mMovieBookingDataAgent.getGenres(
            onSuccess = {
                genreList = it
                onSuccess
            } ,
            onFailure = onFailure
        )
    }

    override fun getUpCommingMovie(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        //database
        onSuccess(mMovieBookingDatabase?.movieDao()?.getMovieListByType(UP_COMING) ?: listOf())

        //Network
        mMovieBookingDataAgent.getUpComming(
            onSuccess = {
                it?.let {
                    it.forEach { movie->
                        movie.type = UP_COMING
                    }
                    mMovieBookingDatabase?.movieDao()?.insertMovieList(it)
                    onSuccess(it)
                }
            },
            onFailure = onFailure
        )
    }

    override fun getMovieDetails(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        //Database
        mMovieBookingDatabase?.movieDao()?.getMovieById(movieId = movieId.toInt())
            ?.let { onSuccess(it) }

        //Network
        mMovieBookingDataAgent.getMovieDetails(
            movieId = movieId,
            onSuccess = {
                val movieFromDatabase = mMovieBookingDatabase?.movieDao()?.getMovieById(movieId = movieId.toInt())
                it.type = movieFromDatabase?.type
                mMovieBookingDatabase?.movieDao()?.insertSingleMovie(it)

                onSuccess(it)
            },
            onFailure = onFailure
        )
    }

    override fun getCreditsByMovie(
        movieId: String,
        onSuccess: (List<ActorVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieBookingDataAgent.getCreditsByMovie(
            movieId = movieId,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }

    override fun getCinemaDayTimeSlot(
        movieId: String,
        authorization: String,
        date: String,
        onSuccess: (List<TimeSlotDataVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {

        onSuccess(mMovieBookingDatabase?.dateCinemaTimeSlotDao()?.getTimeSlotByDate(date = date)?.cinemas?: listOf())

        mMovieBookingDataAgent.getCinemaDayTimeSlot(
            movieId = movieId,
            authorization = authorization,
            date = date,
            onSuccess = {

                val cinemaData = DateCinemaAndTimeSlotVO(
                    date = date,
                    cinemas = it
                )
                mMovieBookingDatabase?.dateCinemaTimeSlotDao()?.insertTimeSlotList(cinemaData)
                onSuccess(it)
            },
            onFailure = onFailure
        )
    }

    override fun getCinemSeatingPlan(
        cinema_day_timeslot_id: String,
        bookingDate: String,
        authorization: String,
        onSuccess: (List<MovieSeatVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieBookingDataAgent.getCinemSeatingPlan(
            cinema_day_timeslot_id = cinema_day_timeslot_id,
            bookingDate = bookingDate,
            authorization = authorization,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }

    override fun getSnack(
        authorization: String,
        onSuccess: (List<SnackDataVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        onSuccess(mMovieBookingDatabase?.snackDao()?.getSnackByToken()?: listOf())

        mMovieBookingDataAgent.getSnack(
            authorization = authorization,
            onSuccess = {

                mMovieBookingDatabase?.snackDao()?.insertSnackList(it)
                onSuccess(it)
            },
            onFailure = onFailure
        )
    }

    override fun getPaymentMethod(
        authorization: String,
        onSuccess: (List<PaymentMethodVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        onSuccess(mMovieBookingDatabase?.paymentDao()?.getPaymentMethodListByToken()?: listOf())

        mMovieBookingDataAgent.getPaymentMethod(
            authorization = authorization,
            onSuccess = {
                mMovieBookingDatabase?.paymentDao()?.insertPaymentMethodList(it)
                onSuccess(it)
            },
            onFailure = onFailure
        )
    }

    override fun getCards(
        authorization: String,
        onSuccess: (List<CardVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {

        //database
        onSuccess(mMovieBookingDatabase?.userDao()?.getUserInfo()?.cards ?: listOf())

        //Network
        mMovieBookingDataAgent.getProfile(
            authorization = authorization,
            onSuccess = {
                it.token = mMovieBookingDatabase?.userDao()?.getUserInfo()?.token ?: ""
                mMovieBookingDatabase?.userDao()?.insertUserData(it)

                it.cards?.let { cards -> onSuccess(cards) }
            },
            onFailure = onFailure
        )


    }


    override fun createNewCard(
        card_number: String,
        card_holder: String,
        expiration_date: String,
        cvc: String,
        authorization: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieBookingDataAgent.createNewCard(
            cardNo = card_number,
            cardHolder = card_holder,
            expirationDate = expiration_date,
            cvc = cvc,
            authorization = authorization,
            onSuccess = {

                        onSuccess()
            },
            onFailure = onFailure

        )
    }

    override fun checkOut(
        cinemaTimeSlotId: Int,
        row: String,
        seatNumber: String,
        bookingDate: String,
        totalAmount: Double,
        movieId: Int,
        selectedCardId: Int,
        cinemaId: Int,
        snackList: ArrayList<SnackParcelize>,
        authorization: String,
        onSuccess: (CheckOutDataVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        var checkoutRequest = CheckOutResquest( cinemaTimeSlotId,row,seatNumber,bookingDate,totalAmount,movieId,selectedCardId,cinemaId, snackList)
        mMovieBookingDataAgent.checkOut(
            authorization = authorization,
            checkoutRequest = checkoutRequest,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }


}