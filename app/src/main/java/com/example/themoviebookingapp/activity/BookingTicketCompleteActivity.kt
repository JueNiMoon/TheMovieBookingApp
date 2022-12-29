package com.example.themoviebookingapp.activity

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.themoviebookingapp.R
import com.example.themoviebookingapp.utils.IMAGE_BASE_URL
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.Writer
import com.google.zxing.common.BitMatrix
import com.google.zxing.oned.Code128Writer
import com.google.zxing.oned.Code39Writer
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import kotlinx.android.synthetic.main.activity_booking_ticket_complete.*
import java.time.LocalDate
import java.util.*


class BookingTicketCompleteActivity : AppCompatActivity() {

    private lateinit var mPosterPath : String
    private lateinit var mMovieTitel : String
    private lateinit var mRunTime : String
    private lateinit var mBookingNo :String
    private lateinit var mShowTime : String
    private lateinit var mBookingDate : String
    private lateinit var mRow : String
    private lateinit var mSeatNumber : String
    private lateinit var mCinemaName : String
    private lateinit var mBookingId : String

    private var mTotalPrice : Double = 0.0


    companion object{
        private const val EXTRA_POSTER_PATH = "EXTRA_POSTER_PATH"
        private const val EXTRA_MOVIE_TITLE = "EXTRA_MOVIE_TITLE"
        private const val EXTRA_RUN_TIME = "EXTRA_RUN_TIME"
        private const val EXTRA_BOOKING_NO = "EXTRA_BOOKING_NO"
        private const val EXTRA_SHOWTIME = "EXTRA_SHOWTIME"
        private const val EXTRA_BOOKING_DATE = "EXTRA_BOOKING_DATE"
        private const val EXTRA_ROW = "EXTRA_ROW"
        private const val EXTRA_SEAT_NUMBER = "EXTRA_SEAT_NUMBER"
        private const val EXTRA_TOTAL_PRICE = "EXTRA_TOTAL_PRICE"
        private const val EXTRA_CINEMA_NAME = "EXTRA_CINEMA_NAME"
        private const val EXTRA_BOOKING_ID = "EXTRA_PRODUCT_ID"

        fun newIntent(
            context: Context,
            posterPath :String,
            movieTitle :String,
            runTime :String,
            bookingNo : String,
            showTime: String,
            bookingDate : String,
            row: String,
            seatNumber : String,
            totalPrice : Double,
            cinemaName: String,
            bookingId : String): Intent {
            val intent = Intent(context, BookingTicketCompleteActivity::class.java)
            intent.putExtra(EXTRA_POSTER_PATH, posterPath)
            intent.putExtra(EXTRA_MOVIE_TITLE, movieTitle)
            intent.putExtra(EXTRA_RUN_TIME, runTime)
            intent.putExtra(EXTRA_BOOKING_NO, bookingNo)
            intent.putExtra(EXTRA_SHOWTIME, showTime)
            intent.putExtra(EXTRA_BOOKING_DATE, bookingDate)
            intent.putExtra(EXTRA_ROW,row)
            intent.putExtra(EXTRA_SEAT_NUMBER,seatNumber)
            intent.putExtra(EXTRA_TOTAL_PRICE,totalPrice)
            intent.putExtra(EXTRA_CINEMA_NAME,cinemaName)
            intent.putExtra(EXTRA_BOOKING_ID,bookingId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_ticket_complete)

        setUpListeners()
        bindExtraData()
        bindDataToView()
        generateBarCode()
    }

    private fun generateBarCode(){

        val hintMap = Hashtable<EncodeHintType, ErrorCorrectionLevel>()
        hintMap[EncodeHintType.ERROR_CORRECTION] = ErrorCorrectionLevel.L
        val byteMatrix: BitMatrix = Code39Writer().encode(mBookingId, BarcodeFormat.CODE_39, 400, 200, hintMap)
        val width = byteMatrix.width
        val height = byteMatrix.height
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        for (i in 0 until width) {
            for (j in 0 until height) {
                bitmap.setPixel(i, j, if (byteMatrix[i, j]) Color.BLACK else Color.WHITE)
            }
        }
        ivBarCode.setImageBitmap(bitmap)
    }


    private fun bindExtraData(){
        mPosterPath = intent?.getStringExtra(EXTRA_POSTER_PATH)?:""
        mMovieTitel = intent?.getStringExtra(EXTRA_MOVIE_TITLE)?:""
        mRunTime = intent?.getStringExtra(EXTRA_RUN_TIME)?:""
        mBookingNo = intent?.getStringExtra(EXTRA_BOOKING_NO)?:""
        mShowTime = intent?.getStringExtra(EXTRA_SHOWTIME)?:""
        mBookingDate = intent?.getStringExtra(EXTRA_BOOKING_DATE)?:""
        mRow = intent?.getStringExtra(EXTRA_ROW)?:""
        mSeatNumber = intent?.getStringExtra(EXTRA_SEAT_NUMBER)?:""
        mCinemaName  = intent?.getStringExtra(EXTRA_CINEMA_NAME )?:""
        mBookingId = intent?.getStringExtra(EXTRA_BOOKING_ID )?:""
        mTotalPrice = intent?.getDoubleExtra(EXTRA_TOTAL_PRICE,0.0)?:0.0
    }

    private fun bindDataToView(){
        Glide.with(ivPoster.context)
            .load("$IMAGE_BASE_URL$mPosterPath")
            .into(ivPoster)

        tvMovieTitle.text = mMovieTitel
        tvVoucherShowTime.text = mRunTime
        tvBookingNo.text = mBookingNo
        var date : LocalDate = LocalDate.parse(mBookingDate)
        tvShowTimeDate.text = "$mShowTime - ${date.dayOfMonth} ${date.month.toString().subSequence(0,3)}"
        tvTheater.text = mCinemaName
        tvRow.text = mRow
        tvSeats.text = mSeatNumber
        tvPrice.text = "\$$mTotalPrice"

    }

    private fun setUpListeners(){
        btnFinish.setOnClickListener {
            startActivity(HomeActivity.newIntent(this))
        }
    }

    }