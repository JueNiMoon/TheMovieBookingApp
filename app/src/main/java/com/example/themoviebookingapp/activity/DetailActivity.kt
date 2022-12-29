package com.example.themoviebookingapp.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.themoviebookingapp.R
import com.example.themoviebookingapp.adapter.MovieTypeAdapter
import com.example.themoviebookingapp.data.vos.MovieVO
import com.example.themoviebookingapp.models.MovieBookingModel
import com.example.themoviebookingapp.models.MovieBookingModelImpl
import com.example.themoviebookingapp.utils.IMAGE_BASE_URL
import com.example.themoviebookingapp.viewpod.CastViewPod
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var mMovieTypeAdapter: MovieTypeAdapter
    private val mMovieBookingModel = MovieBookingModelImpl
    private lateinit var mCastViewPod : CastViewPod

    private var mMovieId : Int = 0
    private lateinit var mMovieTitle : String
    private lateinit var mRunTime : String
    private lateinit var mPosterPath : String

    companion object{
        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"

        fun newIntent(context: Context,movieId: Int): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        mCastViewPod = view_pod_cast as CastViewPod

        setUpRecyclerView()
        setUpListeners()
        setUpFlags()


        mMovieId = intent?.getIntExtra(EXTRA_MOVIE_ID,0) ?: 0

        mMovieId?.let {
            requestData(it)
        }

    }

    private fun setUpFlags(){
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    private fun requestData(movieId: Int){

        /// Get Movie Details
        mMovieBookingModel.getMovieDetails(
            movieId = movieId.toString(),
            onSuccess = {
                bindData(it)
            }, onFailure = {
                showErr(it)
            }
        )

        /// Get casts
        mMovieBookingModel.getCreditsByMovie(
            movieId = movieId.toString(),
            onSuccess = {
                mCastViewPod.setData(it)
            },
            onFailure = {
                showErr(it)
            }
        )
    }

    private fun bindData(mMovieVO : MovieVO){
        mMovieTitle = mMovieVO.title.toString()
        mRunTime = "${mMovieVO.runtime}m-IMAX"
        mPosterPath = mMovieVO.posterPath.toString()
        Glide.with(ivMoviePoster.context)
            .load("$IMAGE_BASE_URL${mMovieVO.posterPath}")
            .into(ivMoviePoster)
        tvMovieName.text = mMovieVO.title
        tvShowTime.text = mMovieVO.getMovieTime()
        rbMovieRating.rating = mMovieVO.getRatingBasedOnFiveStars()
        tvRating.text = "IMD ${mMovieVO.voteAverage}"
        mMovieVO.genres?.let { mMovieTypeAdapter.setNewData(it) }
        tvPlotSummaryDetial.text = mMovieVO.overView

    }

    private fun setUpListeners(){

        btnBackToHome.setOnClickListener {
            onBackPressed()
        }

        btnGetYourTicket.setOnClickListener {
            startActivity(BookingTicketActivity.newIntent(this,mMovieId,mMovieTitle, mPosterPath, mRunTime))
        }

    }

    private fun setUpRecyclerView(){
        mMovieTypeAdapter = MovieTypeAdapter()
        rvMovieType.adapter = mMovieTypeAdapter
        rvMovieType.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
    }

    private fun showErr(message : String){
        Snackbar.make(window.decorView,message, Snackbar.LENGTH_LONG).show()
    }
}