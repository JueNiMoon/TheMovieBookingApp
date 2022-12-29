package com.example.themoviebookingapp.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.themoviebookingapp.R
import com.example.themoviebookingapp.adapter.MovieSeatAdapter
import com.example.themoviebookingapp.data.vos.MovieSeatVO
import com.example.themoviebookingapp.delegate.BookingSeatDelegate
//import com.example.themoviebookingappoviebookingapp.dummy.DUMMY_SEATS
import com.example.themoviebookingapp.models.MovieBookingModel
import com.example.themoviebookingapp.models.MovieBookingModelImpl
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_booking_seat.*
import java.time.LocalDate

class BookingSeatActivity : AppCompatActivity(),BookingSeatDelegate {

    private lateinit var mAdapter: MovieSeatAdapter
    private lateinit var mMovieBookingModelImpl : MovieBookingModelImpl
    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl
    private lateinit var mSelectedSeat : MutableList<Int>
    private lateinit var mMovieSeatList : List<MovieSeatVO>

    private lateinit var mBookingDate : String
    private lateinit var mCinemaName : String
    private lateinit var mMovieTitle : String
    private lateinit var mShowTime : String
    private lateinit var mRow : String
    private lateinit var mSeatName : String
    private lateinit var mUserToken :String
    private lateinit var  mPosterPath : String
    private lateinit var mRunTime : String

    private var mTotalSeat : Int = 0
    private var mTotalPrice : Double = 0.0
    private var mMovieId : Int = 0
    private var mCinemaId : Int = 0
    private var mCinemaTimeSlotId : Int = 0

    companion object{
        private const val EXTRA_CINEMA_TIMESLOT_ID = "EXTRA_CINEMA_TIMESLOT_ID"
        private const val EXTRA_BOOKING_DATE = "EXTRA_BOOKING_DATE"
        private const val EXTRA_CINEMA_NAME = "EXTRA_CINEMA_NAME"
        private const val EXTRA_MOVIE_TITLE = "EXTRA_MOVIE_TITLE"
        private const val EXTRA_SHOW_TIME = "EXTRA_SHOW_TIME"
        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        private const val EXTRA_CINEMA_ID = "EXTRA_CINEMA_ID"
        private const val EXTRA_POSTER_PATH = "EXTRA_POSTER_PATH"
        private const val EXTRA_RUN_TIME = "EXTRA_RUN_TIME"

        fun newIntent(
            context: Context,
            cinemaTimeSlotId: Int,
            movieId : Int,
            cinemaId : Int,
            cinemaName : String,
            movieTitle : String,
            booking_date: String,
            showTime : String,
            posterPath : String,
            runTime: String): Intent {
            val intent = Intent(context, BookingSeatActivity::class.java)
            intent.putExtra(EXTRA_CINEMA_TIMESLOT_ID, cinemaTimeSlotId)
            intent.putExtra(EXTRA_BOOKING_DATE,booking_date)
            intent.putExtra(EXTRA_CINEMA_NAME,cinemaName)
            intent.putExtra(EXTRA_MOVIE_TITLE,movieTitle)
            intent.putExtra(EXTRA_SHOW_TIME, showTime)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            intent.putExtra(EXTRA_CINEMA_ID, cinemaId)
            intent.putExtra(EXTRA_POSTER_PATH,posterPath)
            intent.putExtra(EXTRA_RUN_TIME,runTime)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_seat)

        mSelectedSeat = mutableListOf()
        setUpSeatingPlan()
        setUpListeners()
        getExtraData()
        bindHeaderData()
        getUserToken()
        requestData()
        clearValues()

        mRow = ""
    }

    private fun bindHeaderData(){
        tvMovieName.text = mMovieTitle
        tvCinemaName.text = mCinemaName
        tvTime.text = ""
        mBookingDate?.let {
            val date = LocalDate.parse(mBookingDate)
            tvTime.text = date.dayOfWeek.toString() + ", " + date.dayOfMonth.toString() + " " + date.month.toString().subSequence(0, 3) + ", $mShowTime"
        }

        tvSeatValue.text = "-"
        tvTicketValue.text = mTotalSeat.toString()
        btnBuyTicket.text = "Buy Ticket for \$$mTotalPrice"
    }

    private fun requestData(){
        mMovieBookingModel.getCinemSeatingPlan(
            cinema_day_timeslot_id = mCinemaTimeSlotId.toString(),
            bookingDate = mBookingDate,
            authorization = this.mUserToken,
            onSuccess = {
                mMovieSeatList = it
                mAdapter.setNewData(it)
            },
            onFailure = {
                showErr(it)
            }
        )
    }

    private fun getUserToken(){
        mMovieBookingModelImpl = MovieBookingModelImpl
        this.mUserToken = mMovieBookingModelImpl.getToken()
    }

    private fun getExtraData()
    {
        mCinemaTimeSlotId = intent?.getIntExtra(EXTRA_CINEMA_TIMESLOT_ID,0) ?: 0
        mBookingDate = intent?.getStringExtra(EXTRA_BOOKING_DATE) ?: ""
        mCinemaName = intent?.getStringExtra(EXTRA_CINEMA_NAME) ?: ""
        mMovieTitle = intent?.getStringExtra(EXTRA_MOVIE_TITLE) ?: ""
        mShowTime = intent?.getStringExtra(EXTRA_SHOW_TIME) ?: ""
        mMovieId = intent?.getIntExtra(EXTRA_MOVIE_ID,0) ?: 0
        mCinemaId = intent?.getIntExtra(EXTRA_CINEMA_ID,0) ?: 0
        mPosterPath = intent?.getStringExtra(EXTRA_POSTER_PATH) ?: ""
        mRunTime = intent?.getStringExtra(EXTRA_RUN_TIME)?:""
    }

    private fun setUpListeners(){
        btnBuyTicket.setOnClickListener {
            startActivity(SnackActivity.newIntent(
                this,
                cinema_timeslot_id = mCinemaTimeSlotId,
                movieId = mMovieId,
                row = mRow,
                seat_number = mSeatName,
                bookingDate = mBookingDate,
                totalAmount = mTotalPrice,
                cinemaId = mCinemaId,
                posterPath = mPosterPath,
                runTime = mRunTime,
                movieTitle = mMovieTitle,
                cinemaName = mCinemaName,
                showTime = mShowTime,
            ))
        }
    }

    private fun setUpSeatingPlan(){
        mAdapter  = MovieSeatAdapter(this)
        rvSeatSetting.adapter = mAdapter
        rvSeatSetting.layoutManager = GridLayoutManager(applicationContext,14)
    }

    private fun showErr(message : String){
        Snackbar.make(window.decorView,message, Snackbar.LENGTH_LONG).show()
    }

    private fun clearValues()
    {
        mSeatName = ""
        mTotalSeat = 0
        mTotalPrice = 0.0
    }

    override fun onTabBookingSeat(seatIndex: Int) {
        clearValues()

        if (mMovieSeatList[seatIndex]?.type == "available"){
            var count = 0
            mMovieSeatList.forEach {
                if (count == seatIndex){
                    it.isSelected = !it.isSelected
                }

                if(it.isSelected){
                    mRow = it.symbol ?: ""
                    mTotalSeat ++
                    mSeatName += if(mSeatName == ""){
                                        it.seatName
                                    } else {
                                        ",${it.seatName}"
                                    }
                    mTotalPrice += it.price?:0.0
                }
                count ++
            }
        }
        mAdapter.setNewData(mMovieSeatList)
        bindSeatInformation()
    }

    private fun bindSeatInformation()
    {
        tvSeatValue.text = mSeatName
        tvTicketValue.text = mTotalSeat.toString()
        btnBuyTicket.text = "Buy Ticket for \$$mTotalPrice"
    }

}