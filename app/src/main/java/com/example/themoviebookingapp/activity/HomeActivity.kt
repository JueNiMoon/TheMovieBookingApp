package com.example.themoviebookingapp.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.bumptech.glide.Glide
import com.example.themoviebookingapp.R
import com.example.themoviebookingapp.data.vos.GenreVO
import com.example.themoviebookingapp.data.vos.UserDataVO
import com.example.themoviebookingapp.delegate.MovieListDelegate
import com.example.themoviebookingapp.delegate.NavigationDelegate
import com.example.themoviebookingapp.models.MovieBookingModel
import com.example.themoviebookingapp.models.MovieBookingModelImpl
import com.example.themoviebookingapp.utils.IMAGE_BASE_URL
import com.example.themoviebookingapp.viewpod.MovieListViewPod
import com.example.themoviebookingapp.viewpod.NavigationViewPod
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.view_pod_navigation.view.*

class HomeActivity : AppCompatActivity(), MovieListDelegate, NavigationDelegate {

    private lateinit var mNowShowingViewPod: MovieListViewPod
    private lateinit var mComingSoonViewPod: MovieListViewPod
    private lateinit var mNavigationViewPod: NavigationViewPod
    private var actionBarDrawerToggle: ActionBarDrawerToggle? = null
    private lateinit var mMovieBookingModelImpl : MovieBookingModelImpl
    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl

    //var mGenreList: List<GenreVO> = listOf()
    private lateinit var mUserToken:String

    companion object{
        fun newIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupToolBar()
        setUpNavigation()
        setUpNowShowingViewPod()
        setUpCommingSoonViewPod()
        setUpNavigationViewPod()
        getUserToken()


        requestData()
    }

    private fun getUserToken(){
        mMovieBookingModelImpl = MovieBookingModelImpl
        this.mUserToken = mMovieBookingModelImpl.getToken()
    }

    private fun requestData(){

        //profile
        mMovieBookingModel.getProfile(
            authorization = mUserToken,
            onSuccess = {
                        bindProfileData(it)
            }, onFailure = {
                showErr(it)
            }
        )

        //Get Genre List
        mMovieBookingModel.getGenre(
            onSuccess = {

            },
            onFailure = {
                showErr(it)
            }
        )


        //Get Now Playing Movies
        mMovieBookingModel.getNowPlayingMovie(
            onSuccess = {
                mNowShowingViewPod.setData(it)
            },
            onFailure = {
                showErr(it)
            }
        )

        //Get ComingSoon Movies
        mMovieBookingModel.getUpCommingMovie(
            onSuccess = {
                mComingSoonViewPod.setData(it)
            },
            onFailure = {
                showErr(it)
            }
        )
    }

    private fun bindProfileData(userVO : UserDataVO){
        tvUsreName.text = userVO.name.toString() ?: ""
        Glide.with(ivProfile.context)
            .load("$IMAGE_BASE_URL${userVO.profile_image}")
            .placeholder(R.mipmap.default_profile)
            .into(ivProfile)

        //bind navigation data
        mNavigationViewPod.tvName.text = userVO.name.toString() ?: ""
        mNavigationViewPod.tvEmail.text = userVO.email.toString() ?: ""

        Glide.with(mNavigationViewPod.ivProfile.context)
            .load("$IMAGE_BASE_URL${userVO.profile_image}")
            .placeholder(R.mipmap.default_profile)
            .into(mNavigationViewPod.ivProfile)
    }


    private fun setUpNavigation(){

        setSupportActionBar(toolBar)
        actionBarDrawerToggle = ActionBarDrawerToggle(this,drawerLayout,toolBar, R.string.drawer_open,R.string.drawer_close)
        actionBarDrawerToggle?.let {
            drawerLayout.addDrawerListener(it)
            it.syncState()
        }
        toolBar.elevation = 0.0f
        supportActionBar?.elevation = 0.0f
        supportActionBar?.title =""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onBackPressed() {

        when{
            drawerLayout.isDrawerOpen(GravityCompat.START) -> {
                drawerLayout.closeDrawer(GravityCompat.START)
            }
            else -> {
                super.onBackPressed()
            }
        }
    }

    private fun setUpNavigationViewPod(){
       mNavigationViewPod = navView as NavigationViewPod
       mNavigationViewPod.setUpNavigationViewPod(this)
    }
    
    private fun setUpNowShowingViewPod(){
        mNowShowingViewPod = vpNowShowing as MovieListViewPod
        mNowShowingViewPod.setUpMovieListViewPod(this,getString(R.string.lbl_now_showing))
    }

    private fun setUpCommingSoonViewPod(){
        mComingSoonViewPod = vpCommingSoon as MovieListViewPod
        mComingSoonViewPod.setUpMovieListViewPod(this,getString(R.string.lbl_comming_soon))
    }

    private fun setupToolBar() {
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
    }


    override fun onTabNavigationItemLogout() {
        mMovieBookingModel.logout(
            authorization = this.mUserToken,
            onSuccess = {
                startActivity(LoginActivity.newIntent(this))
            }, onFailure = {
                showErr(it)
            }
        )
    }

    private fun showErr(message : String){
        Snackbar.make(window.decorView,message,Snackbar.LENGTH_LONG).show()
    }

    override fun onTabMovie(movieId: Int) {
        startActivity(DetailActivity.newIntent(this,movieId))
    }

}