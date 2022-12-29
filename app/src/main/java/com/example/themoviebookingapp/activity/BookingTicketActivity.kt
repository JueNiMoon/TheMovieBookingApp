package com.example.themoviebookingapp.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themoviebookingapp.R
import com.example.themoviebookingapp.adapter.BookingTimeAdapter
import com.example.themoviebookingapp.adapter.DayAdapter
import com.example.themoviebookingapp.data.vos.MovieBookingDaysVO
import com.example.themoviebookingapp.data.vos.TimeSlotDataVO
import com.example.themoviebookingapp.delegate.DayDelegate
import com.example.themoviebookingapp.delegate.TimeSlotDelegate
import com.example.themoviebookingapp.models.MovieBookingModel
import com.example.themoviebookingapp.models.MovieBookingModelImpl
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_booking_ticket.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class BookingTicketActivity() : AppCompatActivity(),DayDelegate,TimeSlotDelegate {

    private lateinit var mDayAdapter: DayAdapter
    private lateinit var mBookingTimeAdapter: BookingTimeAdapter
    private lateinit var mMovieBookingModelImpl : MovieBookingModelImpl
    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl

    private lateinit var mTimeSlotList : List<TimeSlotDataVO>
    private lateinit var mBookingDayList : MutableList<MovieBookingDaysVO>

    private lateinit var mSelectedDay : String
    private lateinit var mPosterPath : String
    private lateinit var mCinemaName : String
    private lateinit var mMovieTitle : String
    private lateinit var mShowTime : String
    private lateinit var mRunTime : String
    private lateinit var mUserToken:String
    private var mCinemaId : Int =0
    private var mMovieId : Int = 0
    private var mSelectedCinemaTimeSlotId : Int = -1

    companion object{
        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        private const val EXTRA_MOVIE_TITLE = "EXTRA_MOVIE_TITLE"
        private const val EXTRA_POSTER_PATH = "EXTRA_POSTER_PATH"
        private const val EXTRA_RUN_TIME = "EXTRA_RUN_TIME"

        fun newIntent(context: Context, movieId: Int, movieName : String,posterPath : String,runTime : String): Intent {
            val intent = Intent(context, BookingTicketActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            intent.putExtra(EXTRA_MOVIE_TITLE,movieName)
            intent.putExtra(EXTRA_POSTER_PATH,posterPath)
            intent.putExtra(EXTRA_RUN_TIME,runTime)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mTimeSlotList = listOf<TimeSlotDataVO>()
        mBookingDayList = mutableListOf()
        setContentView(R.layout.activity_booking_ticket)
        setUpDayRecyclerView()
        setUpListeners()
        getDayList()
        getUserToken()
        getExtraData()
        requestData()
    }

    private fun getExtraData()
    {
        mMovieId = intent?.getIntExtra(EXTRA_MOVIE_ID,0)?:0
        mMovieTitle = intent?.getStringExtra(EXTRA_MOVIE_TITLE)?:""
        mPosterPath = intent?.getStringExtra(EXTRA_POSTER_PATH)?:""
        mRunTime = intent?.getStringExtra(EXTRA_RUN_TIME)?:""
    }

    private fun requestData(){
        mMovieBookingModel.getCinemaDayTimeSlot(
            movieId = mMovieId.toString(),
            date = mSelectedDay,
            authorization = mUserToken,
            onSuccess = {
                mTimeSlotList = it
                mBookingTimeAdapter.setNewData(it)
            }, onFailure = {
                showErr(it)
            }
        )
    }

    private fun showErr(message : String){
        Snackbar.make(window.decorView,message, Snackbar.LENGTH_LONG).show()
    }

    private fun getUserToken(){
        mMovieBookingModelImpl = MovieBookingModelImpl
        this.mUserToken = mMovieBookingModelImpl.getToken()
    }

    private fun getDayList(){

        var todayDate = LocalDate.now()
        setInitialSelectedDay(todayDate)

        var mMovieBookingDaysVO : MovieBookingDaysVO = MovieBookingDaysVO(true,todayDate)
        mBookingDayList.add(0,mMovieBookingDaysVO)

        for (day in 1..13){
            todayDate = todayDate.plusDays(1)
            mMovieBookingDaysVO = MovieBookingDaysVO(false,todayDate)
            mBookingDayList.add(day,mMovieBookingDaysVO)
        }

        mDayAdapter.setNewData(mBookingDayList)

    }

    private fun setInitialSelectedDay(date : LocalDate){
        val df = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        mSelectedDay = LocalDate.parse(date.toString(), df).toString()
    }

    private fun setUpListeners(){

        btnBackToDetail.setOnClickListener {
            onBackPressed()
        }

        btnNext.setOnClickListener {

            if (mSelectedCinemaTimeSlotId >= 0){

                startActivity(BookingSeatActivity.newIntent(
                    this,
                    cinemaTimeSlotId =  mSelectedCinemaTimeSlotId,
                    movieId = mMovieId,
                    cinemaId = mCinemaId,
                    movieTitle = mMovieTitle,
                    cinemaName = mCinemaName,
                    booking_date =  mSelectedDay,
                    showTime = mShowTime,
                    posterPath = mPosterPath,
                    runTime = mRunTime))
            }else {
                showErr("Please select cinema time!")
            }

        }
    }

    private fun setUpDayRecyclerView(){

        mDayAdapter = DayAdapter(this)
        rvDay.adapter = mDayAdapter
        rvDay.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        mBookingTimeAdapter = BookingTimeAdapter(this)
        rvBook.adapter = mBookingTimeAdapter
        rvBook.layoutManager =LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

    }

    override fun daySelect(date: String, index: Int) {
        for (i in 0 until mBookingDayList.count()){
            mBookingDayList[i].isSelected = i == index
        }
        mDayAdapter.setNewData(mBookingDayList)
        mSelectedDay = date

        requestData()
    }

    override fun onTapTimeSlot(cinemaTimeSlotID : Int) {

        mSelectedCinemaTimeSlotId = cinemaTimeSlotID;
        mTimeSlotList.forEach {
            it.timeslots?.forEach {timeSlot ->
                timeSlot.isSelected = timeSlot.cinemaDayTimeslotId == cinemaTimeSlotID
                if(timeSlot.cinemaDayTimeslotId == cinemaTimeSlotID){
                    mCinemaName = it.cinema.toString()
                    mCinemaId = it.cinema_id?:0
                    mShowTime = timeSlot.startTime.toString()
                }
            }
        }
        mBookingTimeAdapter.setNewData(mTimeSlotList)

    }
}