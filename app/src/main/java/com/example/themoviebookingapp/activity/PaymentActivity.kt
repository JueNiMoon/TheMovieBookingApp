package com.example.themoviebookingapp.activity

import alirezat775.lib.carouselview.Carousel
import alirezat775.lib.carouselview.CarouselListener
import alirezat775.lib.carouselview.CarouselView
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.themoviebookingapp.R
import com.example.themoviebookingapp.adapter.CardAdapter
import com.example.themoviebookingapp.adapter.CarouselViewAdapter
import com.example.themoviebookingapp.data.vos.CardVO
import com.example.themoviebookingapp.data.vos.SnackParcelize
import com.example.themoviebookingapp.models.MovieBookingModel
import com.example.themoviebookingapp.models.MovieBookingModelImpl
import com.example.themoviebookingapp.network.request.CheckOutResquest
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_payment.*


class PaymentActivity : AppCompatActivity() {

    lateinit var  mCarouselViewAdapter: CarouselViewAdapter
    lateinit var mCardAdapter : CardAdapter
    lateinit var mMovieBookingModelImpl : MovieBookingModelImpl
    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl

    private lateinit var mCardList : List<CardVO>
    private lateinit var mSnackList : ArrayList<SnackParcelize>

    private lateinit var mRow : String
    private lateinit var mSeatNumber : String
    private lateinit var mBookingDate : String
    private lateinit var mUserToken :String
    private lateinit var mPosterPath : String
    private lateinit var mBookingNo : String
    private lateinit var mBooingId : String
    private lateinit var mRunTime : String
    private lateinit var mMovieTitle : String
    private lateinit var mCinemaName : String
    private lateinit var mShowTime : String

    private var mMovieId : Int = 0
    private var mCinemaId : Int = 0
    private var mTotalAmount : Double = 0.0
    var selectedCardId: Int = 0
    private var mCinemaTimeSlotId : Int =0

    companion object{
        private const val EXTRA_CINEMA_TIME_SLOT_ID = "EXTRA_CINEMA_TIME_SLOT_ID"
        private const val EXTRA_ROW = "EXTRA_ROW"
        private const val EXTRA_SEAT_NUMBER = "EXTRA_SEAT_NUMBER"
        private const val EXTRA_BOOKING_DATE = "EXTRA_BOOKING_DATE"
        private const val EXTRA_TOTAL_AMOUNT = "EXTRA_EXTRA_TOTAL_AMOUNT"
        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        private const val EXTRA_CINEMA_ID = "EXTRA_CINEMA_ID"
        private const val EXTRA_SNACK_LIST = "EXTRA_SNACK_LIST"
        private const val EXTRA_POSTER_PATH = "EXTRA_POSTER_PATH"
        private const val EXTRA_RUN_TIME = "EXTRA_RUN_TIME"
        private const val EXTRA_MOVIE_TITLE = "EXTRA_MOVIE_TITLE"
        private const val EXTRA_CINEMA_NAME = "EXTRA_CINEMA_NAME"
        private const val EXTRA_SHOW_TIME = "EXTRA_SHOW_TIME"

        fun newIntent(
            context: Context,
            cinema_timeslot_id :Int,
            row:String,
            seat_number :String,
            booking_date : String,
            totalAmount: Double,
            movieId : Int,
            cinema_id: Int,
            snackList: ArrayList<SnackParcelize>,
            posterPath : String,
            runTime : String,
            movieTitle : String,
            cinemaName : String,
            showTime : String): Intent {
            val intent = Intent(context, PaymentActivity::class.java)
            intent.putExtra(EXTRA_CINEMA_TIME_SLOT_ID, cinema_timeslot_id)
            intent.putExtra(EXTRA_ROW, row)
            intent.putExtra(EXTRA_SEAT_NUMBER, seat_number)
            intent.putExtra(EXTRA_BOOKING_DATE, booking_date)
            intent.putExtra(EXTRA_TOTAL_AMOUNT, totalAmount)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            intent.putExtra(EXTRA_CINEMA_ID,cinema_id)
            intent.putExtra(EXTRA_SNACK_LIST,snackList)
            intent.putExtra(EXTRA_POSTER_PATH,posterPath)
            intent.putExtra(EXTRA_RUN_TIME,runTime)
            intent.putExtra(EXTRA_MOVIE_TITLE,movieTitle)
            intent.putExtra(EXTRA_CINEMA_NAME,cinemaName)
            intent.putExtra(EXTRA_SHOW_TIME,showTime)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        mCardList = listOf<CardVO>()

        setUpCarouselView()
        setUpListeners()
        getExtraData()
        getUserToken()
        requestData()
    }

    private fun getExtraData(){
        mCinemaTimeSlotId = intent?.getIntExtra(EXTRA_CINEMA_TIME_SLOT_ID,0) ?: 0
        mRow = intent?.getStringExtra(EXTRA_ROW) ?: ""
        mSeatNumber = intent?.getStringExtra(EXTRA_SEAT_NUMBER) ?: ""
        mBookingDate = intent?.getStringExtra(EXTRA_BOOKING_DATE) ?: ""
        mTotalAmount = intent?.getDoubleExtra(EXTRA_TOTAL_AMOUNT,0.0) ?: 0.0
        mMovieId = intent?.getIntExtra(EXTRA_MOVIE_ID,0) ?: 0
        mCinemaId = intent?.getIntExtra(EXTRA_CINEMA_ID,0) ?: 0
        mSnackList = intent?.getParcelableArrayListExtra<SnackParcelize>(EXTRA_SNACK_LIST) as ArrayList<SnackParcelize>
        mPosterPath = intent?.getStringExtra(EXTRA_POSTER_PATH) ?: ""
        mRunTime = intent?.getStringExtra(EXTRA_RUN_TIME)?:""
        mMovieTitle = intent?.getStringExtra(EXTRA_MOVIE_TITLE )?:""
        mCinemaName  = intent?.getStringExtra(EXTRA_CINEMA_NAME )?:""
        mShowTime  = intent?.getStringExtra(EXTRA_SHOW_TIME )?:""
        tvAmount.text = "\$ $mTotalAmount"
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === 1) {
            if (resultCode === RESULT_OK) {
               requestData()
            }
        }
    }

    private fun requestData(){

        mMovieBookingModel.getCards(
            authorization = this.mUserToken,
            onSuccess = {
                setPaymentCardNewData(it)

            }, onFailure = {
                showErr(it)
            }
        )
    }

    private fun setPaymentCardNewData(cardList : List<CardVO>){
        var reversedList = mutableListOf<CardVO>()
        mCardList =cardList
        if(cardList.isNotEmpty()){
            reversedList =  cardList.reversed() as MutableList<CardVO>
            selectedCardId = reversedList[0].id ?: 0
        }
        mCardAdapter.setNewData(reversedList)
    }

    private fun getUserToken(){
        mMovieBookingModelImpl = MovieBookingModelImpl
        this.mUserToken = mMovieBookingModelImpl.getToken()
    }

    private fun setUpListeners(){
        btnAddPayment.setOnClickListener {
            val intent = Intent (this,AddNewCardActivity::class.java)
            startActivityForResult(intent,1)
        }

        btnConfirm.setOnClickListener {
            checkOut()
        }

        btnBackToBookingTicket.setOnClickListener {
            onBackPressed()
        }
    }

    private fun checkOut(){
        mMovieBookingModelImpl.checkOut(
            cinemaTimeSlotId = mCinemaTimeSlotId,
            row = mRow,
            seatNumber = mSeatNumber,
            bookingDate = mBookingDate,
            totalAmount = mTotalAmount,
            movieId = mMovieId,
            selectedCardId = selectedCardId,
            cinemaId = mCinemaId,
            snackList = mSnackList,
            authorization = this.mUserToken,
            onSuccess = {
                it?.let {
                    mBookingNo = it.bookingNo?: ""
                    mBooingId = it.id.toString()
                }
               startActivity(BookingTicketCompleteActivity.newIntent(
                   this,
                   posterPath = mPosterPath,
                   movieTitle = mMovieTitle,
                   runTime = mRunTime,
                   bookingNo = mBookingNo,
                   showTime = mShowTime,
                   bookingDate = mBookingDate,
                   row = mRow,
                   seatNumber = mSeatNumber,
                   totalPrice = mTotalAmount,
                   cinemaName = mCinemaName,
                   bookingId = mBooingId
               ))
            },
            onFailure = {
                showErr(it)
            }
        )
    }

    private fun showErr(message : String){
        Snackbar.make(window.decorView,message,Snackbar.LENGTH_LONG).show()
    }


    private fun setUpCarouselView(){
        mCarouselViewAdapter = CarouselViewAdapter()
        val carousel = Carousel(this,carouselVisaCard,mCarouselViewAdapter)
        carousel.setOrientation(CarouselView.HORIZONTAL, false)
        carousel.autoScroll(false, 5000, true)
        carousel.scaleView(true)

        mCardAdapter = CardAdapter()
        carouselVisaCard.adapter = mCardAdapter


        carousel.addCarouselListener(object : CarouselListener{
            override fun onPositionChange(position: Int) {
                mCardList?.let {
                    selectedCardId = it.getOrNull(carousel.getCurrentPosition())?.id ?: 0
                    mCardAdapter.setNewData(it)
                }
            }

            override fun onScroll(dx: Int, dy: Int) {

            }
        })
    }

}



