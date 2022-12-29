package com.example.themoviebookingapp.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themoviebookingapp.R
import com.example.themoviebookingapp.adapter.SnackAdapter
import com.example.themoviebookingapp.adapter.PaymentMethodAdapter
import com.example.themoviebookingapp.data.vos.PaymentMethodVO
import com.example.themoviebookingapp.data.vos.SnackDataVO
import com.example.themoviebookingapp.data.vos.SnackParcelize
import com.example.themoviebookingapp.delegate.PaymentMethodDelegate
import com.example.themoviebookingapp.delegate.SnackDelegate
import com.example.themoviebookingapp.models.MovieBookingModel
import com.example.themoviebookingapp.models.MovieBookingModelImpl
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_snack.*


class SnackActivity : AppCompatActivity(),SnackDelegate,PaymentMethodDelegate {

    lateinit var mSnackAdapter: SnackAdapter
    lateinit var mPaymentMethodAdapter: PaymentMethodAdapter
    lateinit var mMovieBookingModelImpl : MovieBookingModelImpl
    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl

    private lateinit var mSnackList : List<SnackDataVO>
    private lateinit var mPaymentMethodList : List<PaymentMethodVO>
    lateinit var mOrderSnackList : ArrayList<SnackParcelize>

    var mTotalamount : Double = 0.0
    var mCinemaTimeSlotId : Int = 0
    var mMovieId : Int = 0
    var mCinemaId : Int = 0
    lateinit var mRow : String
    lateinit var mSeatNumber : String
    lateinit var mBookingDate : String
    lateinit var mUserToken :String
    lateinit var mPosterPath : String
    lateinit var mRunTime : String
    lateinit var mMovieTitle : String
    lateinit var mCinemaName : String
    lateinit var mShowTime : String


    companion object{
        private const val EXTRA_TOTAL_AMOUNT = "EXTRA_TOTAL_AMOUNT"
        private const val EXTRA_CINEMA_TIME_SLOT_ID = "EXTRA_CINEMA_TIME_SLOT_ID"
        private const val EXTRA_ROW = "EXTRA_ROW"
        private const val EXTRA_SEAT_NUMBER = "EXTRA_SEAT_NUMBER"
        private const val EXTRA_BOOKING_DATE = "EXTRA_BOOKING_DATE"
        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        private const val EXTRA_CINEMA_ID = "EXTRA_CINEMA_ID"
        private const val EXTRA_POSTER_PATH = "EXTRA_POSTER_PATH"
        private const val EXTRA_RUN_TIME = "EXTRA_RUN_TIME"
        private const val EXTRA_MOVIE_TITLE = "EXTRA_MOVIE_TITLE"
        private const val EXTRA_CINEMA_NAME = "EXTRA_CINEMA_NAME"
        private const val EXTRA_SHOW_TIME = "EXTRA_SHOW_TIME"

        fun newIntent(
            context: Context,
            cinema_timeslot_id:Int,
            row : String,
            seat_number : String,
            bookingDate : String,
            totalAmount: Double,
            movieId : Int,
            cinemaId : Int,
            posterPath : String,
            runTime : String,
            movieTitle: String,
            cinemaName : String,
            showTime : String): Intent {
            val intent = Intent(context, SnackActivity::class.java)
            intent.putExtra(EXTRA_CINEMA_TIME_SLOT_ID,cinema_timeslot_id)
            intent.putExtra(EXTRA_ROW, row)
            intent.putExtra(EXTRA_SEAT_NUMBER, seat_number)
            intent.putExtra(EXTRA_BOOKING_DATE, bookingDate)
            intent.putExtra(EXTRA_TOTAL_AMOUNT, totalAmount)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            intent.putExtra(EXTRA_CINEMA_ID, cinemaId)
            intent.putExtra(EXTRA_POSTER_PATH, posterPath)
            intent.putExtra(EXTRA_RUN_TIME,runTime)
            intent.putExtra(EXTRA_MOVIE_TITLE,movieTitle)
            intent.putExtra(EXTRA_CINEMA_NAME,cinemaName)
            intent.putExtra(EXTRA_SHOW_TIME,showTime)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snack)
        mSnackList = listOf<SnackDataVO>()
        mPaymentMethodList = listOf<PaymentMethodVO>()
        mOrderSnackList = arrayListOf()
        setUpRecyclerView()
        setUpListeners()
        getExtraData()
        getUserToken()
        bindTotalAmount(mTotalamount)

        requestData()
    }

    private fun requestData(){
        //get snack data
        mMovieBookingModel.getSnack(
            authorization = this.mUserToken,
            onSuccess = {
                mSnackList = it
                mSnackAdapter.setNewData(mSnackList)
            }, onFailure = {
                showErr(it)
            }
        )
        //get payment method data
        mMovieBookingModel.getPaymentMethod(
            authorization = this.mUserToken,
            onSuccess = {
                mPaymentMethodList = it
                mPaymentMethodAdapter.setNewData(it)
            }, onFailure = {
                showErr(it)
            }
        )
    }

    private fun getUserToken(){
        mMovieBookingModelImpl = MovieBookingModelImpl
        this.mUserToken = mMovieBookingModelImpl.getToken()
    }


    private fun bindTotalAmount(amount : Double){
        btnPayment.text = "Pay \$$amount"
        tvSubTotalAmount.text = "$amount\$"
    }

    private fun getExtraData(){
        mTotalamount = intent?.getDoubleExtra(EXTRA_TOTAL_AMOUNT,0.0) ?: 0.0
        mCinemaTimeSlotId = intent?.getIntExtra(EXTRA_CINEMA_TIME_SLOT_ID,0) ?: 0
        mRow = intent?.getStringExtra(EXTRA_ROW) ?: ""
        mSeatNumber = intent?.getStringExtra(EXTRA_SEAT_NUMBER) ?: ""
        mBookingDate = intent?.getStringExtra(EXTRA_BOOKING_DATE) ?: ""
        mMovieId = intent?.getIntExtra(EXTRA_MOVIE_ID,0) ?: 0
        mCinemaId = intent?.getIntExtra(EXTRA_CINEMA_ID,0) ?: 0
        mPosterPath = intent?.getStringExtra(EXTRA_POSTER_PATH) ?: ""
        mRunTime = intent?.getStringExtra(EXTRA_RUN_TIME)?:""
        mMovieTitle = intent?.getStringExtra(EXTRA_MOVIE_TITLE )?:""
        mCinemaName  = intent?.getStringExtra(EXTRA_CINEMA_NAME )?:""
        mShowTime  = intent?.getStringExtra(EXTRA_SHOW_TIME )?:""
    }

    private fun setUpListeners(){
        btnBackToBookingTicket.setOnClickListener {
            onBackPressed()
        }

        btnPayment.setOnClickListener {

            mSnackList?.let {
                it.forEach { snack->
                    if(snack.count > 0){
                        var orderSnack : SnackParcelize = SnackParcelize (snack.id,snack.count)
                        mOrderSnackList.add(0,orderSnack)
                    }

                }
            }
            startActivity(PaymentActivity.newIntent(this,
                cinema_timeslot_id = mCinemaTimeSlotId,
                row = mRow,
                seat_number = mSeatNumber,
                booking_date = mBookingDate,
                totalAmount = mTotalamount,
                movieId = mMovieId,
                cinema_id = mCinemaId,
                snackList = mOrderSnackList,
                posterPath = mPosterPath,
                runTime = mRunTime,
                movieTitle = mMovieTitle,
                cinemaName = mCinemaName,
                showTime = mShowTime
            ))
        }

    }

    private fun setUpRecyclerView(){
        mSnackAdapter = SnackAdapter(this)
        rvComboSet.adapter = mSnackAdapter
        rvComboSet.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        mPaymentMethodAdapter = PaymentMethodAdapter(this)
        rvPaymentMethod.adapter = mPaymentMethodAdapter
        rvPaymentMethod.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
    }

    override fun onTabPlusBtn(index: Int) {
        mSnackList[index]?.let {
            it.count ++
            mSnackAdapter.setNewData(mSnackList)

            mTotalamount += it.price?:0.0
            bindTotalAmount(mTotalamount)
        }

    }

    override fun onTabMinusBtn(index: Int) {
        mSnackList[index]?.let {
            if(it.count>0){
                it.count --
                mSnackAdapter.setNewData(mSnackList)

                mTotalamount -= it.price?:0.0
                bindTotalAmount(mTotalamount)
            }

        }
    }

    override fun onTabPaymentMethod(index: Int) {
        mPaymentMethodList?.let {
            for (i in 0 until it.count()){
                it?.getOrNull(i)?.isSelected = i == index
            }

            mPaymentMethodAdapter.setNewData(it)
        }
    }

    private fun showErr(message : String){
        Snackbar.make(window.decorView,message, Snackbar.LENGTH_LONG).show()
    }
}