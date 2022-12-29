package com.example.themoviebookingapp.network.dataagents

import com.example.themoviebookingapp.data.vos.*
import com.example.themoviebookingapp.network.TheMovieBookingApi
import com.example.themoviebookingapp.network.request.CheckOutResquest
import com.example.themoviebookingapp.network.responses.*
import com.example.themoviebookingapp.utils.AUTHORIZATION
import com.example.themoviebookingapp.utils.BASE_URL
import com.example.themoviebookingapp.utils.MOVIE_API_KEY
import com.example.themoviebookingapp.utils.MOVIE_BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Header
import java.util.concurrent.TimeUnit

object RetrofitDataAgentImpl : MovieBookingDataAgent {

    private var mTheMovieBookingApi: TheMovieBookingApi? = null
    private var mTheMovieApi: TheMovieBookingApi? = null

    init {
        val mOkHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        //for Movie booking API
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        mTheMovieBookingApi = retrofit.create(TheMovieBookingApi::class.java)

        /// for Movie API (now playing and comming soon)
        val retrofit_movie = Retrofit.Builder()
            .baseUrl(MOVIE_BASE_URL)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        mTheMovieApi = retrofit_movie.create(TheMovieBookingApi::class.java)
    }




    override fun logInWithEmail(
        email: String,
        password: String,
        onSuccess: (Pair<UserDataVO, String>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.loginWithEmail(email = email , password = password)
            ?.enqueue(object : Callback<UserDataResponse>{
                override fun onResponse(
                    call: Call<UserDataResponse>,
                    response: Response<UserDataResponse>
                ) {
                    if(response.isSuccessful){
                        response.body()?.let {
                            onSuccess(Pair(it.data,it.token) as Pair<UserDataVO, String>)
                        }
                    }else{
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<UserDataResponse>, t: Throwable) {
                    onFailure(t.message?:"")
                }

            })
    }

    override fun singInWithEmail(
        name: String,
        email: String,
        phone: String,
        password: String,
        onSuccess: (Pair<UserDataVO, String>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.signInWithEmail(name = name, email = email, phone = phone, password= password)
            ?.enqueue(object : Callback<UserDataResponse>{
                override fun onResponse(
                    call: Call<UserDataResponse>,
                    response: Response<UserDataResponse>
                ) {
                    if(response.isSuccessful){
                        response.body()?.let {
                            onSuccess(Pair(it.data,it.token) as Pair<UserDataVO, String>)
                        }
                    }else{
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<UserDataResponse>, t: Throwable) {
                    onFailure(t.message?:"")
                }

            })
    }

    override fun getProfile(
        authorization: String,
        onSuccess: (UserDataVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.getProfile(Authorization = authorization)
            ?.enqueue(object : Callback<UserDataResponse>{
                override fun onResponse(call: Call<UserDataResponse>, response: Response<UserDataResponse>) {
                    if (response.isSuccessful){
                        response.body()?.let {
                            it.data?.let { it1 -> onSuccess(it1) }
                        }
                    }else{
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<UserDataResponse>, t: Throwable) {
                    onFailure(t.message?:"")
                }

            })
    }

    override fun logout(
        authorization: String,
        onSuccess: (LogoutVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.logout(Authorization = authorization)
            ?.enqueue(object : Callback<LogoutVO>{
                override fun onResponse(call: Call<LogoutVO>, response: Response<LogoutVO>) {
                    if (response.isSuccessful){
                        response.body()?.let {
                            onSuccess(it)
                        }
                    }else{
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<LogoutVO>, t: Throwable) {
                    onFailure(t.message?:"")
                }

            })
    }

    override fun getNowPlayingMovie(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieApi?.getNowPlayingMovies()
            ?.enqueue(object : Callback<MovieListResponse>{
                override fun onResponse(
                    call: Call<MovieListResponse>,
                    response: Response<MovieListResponse>
                ) {
                    if(response.isSuccessful){
                        response.body()?.let {
                            it.results?.let { movieList->
                                onSuccess(movieList)
                            }
                        }
                    }else{
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                    onFailure(t.message?:"")
                }

            })
    }

    override fun getGenres(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {
        mTheMovieApi?.getGenres(apiKey = MOVIE_API_KEY)
            ?.enqueue(object : Callback<GetGenresResponse>{
                override fun onResponse(
                    call: Call<GetGenresResponse>,
                    response: Response<GetGenresResponse>
                ) {
                    if (response.isSuccessful){
                        onSuccess(response.body()?.genres ?: listOf())
                    }
                    else{
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<GetGenresResponse>, t: Throwable) {
                    onFailure(t.message?:"")
                }

            })
    }

    override fun getUpComming(onSuccess: (List<MovieVO>) -> Unit, onFailure: (String) -> Unit) {
        mTheMovieApi?.getUpComingMovies()
            ?.enqueue(object : Callback<MovieListResponse>{
                override fun onResponse(
                    call: Call<MovieListResponse>,
                    response: Response<MovieListResponse>
                ) {
                    if(response.isSuccessful){
                        response.body()?.let {
                            it.results?.let { movieList->
                                onSuccess(movieList)
                            }
                        }
                    }else{
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                    onFailure(t.message?:"")
                }

            })
    }

    override fun getMovieDetails(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieApi?.getMovieDetails(movieId = movieId)
            ?.enqueue(object : Callback<MovieVO>{
                override fun onResponse(call: Call<MovieVO>, response: Response<MovieVO>) {
                    if(response.isSuccessful){
                        response.body()?.let {
                            onSuccess(it)
                        }
                    }else{
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<MovieVO>, t: Throwable) {
                    onFailure(t.message?:"")
                }

            })
    }

    override fun getCreditsByMovie(
        movieId: String,
        onSuccess: (List<ActorVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieApi?.getCreditByMovie(movieId = movieId)
            ?.enqueue(object : Callback<GetCreditsByMovieResponse>{
                override fun onResponse(
                    call: Call<GetCreditsByMovieResponse>,
                    response: Response<GetCreditsByMovieResponse>
                ) {
                    if(response.isSuccessful){
                        response.body()?.let {
                            it.cast?.let { mCast -> onSuccess(mCast) }
                        }
                    }else{
                        onFailure(response.message())
                    }
                }
                override fun onFailure(call: Call<GetCreditsByMovieResponse>, t: Throwable) {
                    onFailure(t.message?:"")
                }
            })
    }

    override fun getCinemaDayTimeSlot(
        movieId: String,
        date: String,
        authorization: String,
        onSuccess: (List<TimeSlotDataVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.getCinemaDayTimeSlot(movieId = movieId, date= date, authorization = authorization)
            ?.enqueue(object :Callback<TimeSlotResponse>{
                override fun onResponse(
                    call: Call<TimeSlotResponse>,
                    response: Response<TimeSlotResponse>
                ) {
                    if(response.isSuccessful){
                        response.body()?.let {
                            it.data?.let { timeSlotList ->
                                onSuccess(timeSlotList)
                            }
                        }
                    }else{
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<TimeSlotResponse>, t: Throwable) {
                    onFailure(t.message?:"")
                }

            })
    }

    override fun getCinemSeatingPlan(
        cinema_day_timeslot_id: String,
        bookingDate: String,
        authorization: String,
        onSuccess: (List<MovieSeatVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.getSeatingPlan(cinema_day_timeslot_id = cinema_day_timeslot_id, booking_date = bookingDate, authorization = authorization)
            ?.enqueue(object : Callback<MovieSeatsVOResponse>{
                override fun onResponse(
                    call: Call<MovieSeatsVOResponse>,
                    response: Response<MovieSeatsVOResponse>
                ) {
                    if(response.isSuccessful){
                        response.body()?.let {
                            onSuccess(it.getMovieSeatList())
                        }
                    }else{
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<MovieSeatsVOResponse>, t: Throwable) {
                    onFailure(t.message?:"")
                }

            })
    }

    override fun getSnack(
        authorization: String,
        onSuccess: (List<SnackDataVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.getSnack(Authorization = authorization)
            ?.enqueue(object : Callback<SnackDataResponse>{
                override fun onResponse(
                    call: Call<SnackDataResponse>,
                    response: Response<SnackDataResponse>
                ) {
                    if(response.isSuccessful){
                        response.body()?.let {
                            it.data?.let {snackList->
                                onSuccess(snackList)
                            }

                        }
                    }else{
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<SnackDataResponse>, t: Throwable) {
                    onFailure(t.message?:"")
                }

            })
    }

    override fun getPaymentMethod(
        authorization: String,
        onSuccess: (List<PaymentMethodVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.getPaymentMethod(Authorization = authorization)
            ?.enqueue(object : Callback<PaymentMethodVOResponse>{
                override fun onResponse(
                    call: Call<PaymentMethodVOResponse>,
                    response: Response<PaymentMethodVOResponse>
                ) {
                    if(response.isSuccessful){
                        response.body()?.let {
                            it.data?.let {paymentList->
                                onSuccess(paymentList)
                            }

                        }
                    }else{
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<PaymentMethodVOResponse>, t: Throwable) {
                    onFailure(t.message?:"")
                }

            })
    }

    override fun createNewCard(
        cardNo: String,
        cardHolder: String,
        expirationDate: String,
        cvc: String,
        authorization: String,
        onSuccess: (List<CardVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.createNewCard(card_number = cardNo, card_holder = cardHolder, expiration_date = expirationDate, cvc = cvc, Authorization = authorization)
            ?.enqueue(object : Callback<CardResponse>{
                override fun onResponse(
                    call: Call<CardResponse>,
                    response: Response<CardResponse>
                ) {
                    if(response.isSuccessful){
                        response.body()?.let {
                            it.data?.let {cardlist->
                                onSuccess(cardlist)
                            }
                        }
                    }else{
                        onFailure(response.message())
                    }
                }
                override fun onFailure(call: Call<CardResponse>, t: Throwable) {
                    onFailure(t.message?:"")
                }

            })
    }

    override fun checkOut(
        checkoutRequest: CheckOutResquest,
        authorization: String,
        onSuccess: (CheckOutDataVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.checkOut(Authorization = authorization,checkoutRequest = checkoutRequest)
            ?.enqueue(object : Callback<CheckOutResponse>{
                override fun onResponse(
                    call: Call<CheckOutResponse>,
                    response: Response<CheckOutResponse>
                ) {
                    if(response.isSuccessful){
                        response.body()?.let {
                           it.data?.let { checkOutData ->
                               onSuccess(checkOutData)
                           }
                        }
                    }else{
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<CheckOutResponse>, t: Throwable) {
                    onFailure(t.message?:"")
                }

            })
    }


}