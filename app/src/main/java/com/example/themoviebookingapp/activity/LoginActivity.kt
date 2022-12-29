package com.example.themoviebookingapp.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import com.example.themoviebookingapp.R
import com.example.themoviebookingapp.adapter.LoginSigninAdapter
import com.example.themoviebookingapp.delegate.LoginDelegate
import com.example.themoviebookingapp.dummy.dummyLoginList
import com.example.themoviebookingapp.models.MovieBookingModel
import com.example.themoviebookingapp.models.MovieBookingModelImpl
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_sign_in.*
import kotlinx.android.synthetic.main.view_pod_loginsigninbuttons.*

class LoginActivity : AppCompatActivity(), LoginDelegate {

    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl

    companion object{

        fun newIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setUpViewPager()
        setUpLoginTabLayout()
   }

    private fun setUpViewPager(){
        val loginsigninViewPagerAdapter = LoginSigninAdapter(this)
        vpLogin.adapter = loginsigninViewPagerAdapter
    }

    private fun setUpLoginTabLayout(){

        TabLayoutMediator(tablayoutLogin,vpLogin){tab,position ->
            when (position){
                0 -> tab.text = dummyLoginList[position].toString()
                else -> tab.text = dummyLoginList[position].toString()
            }
        }.attach()

    }

    override fun login(email: String, password: String) {

        if (isValidLoginWithEmail(email,password)){
            mMovieBookingModel.loginWithEmail(
                email = email,
                password = password,
                onSuccess = {
                    startActivity(HomeActivity.newIntent(this))
                }, onFailure = {
                    showErr(it)
                }
            )
        }

    }

    override fun signWithEmail(name: String, email: String, phone: String, password: String) {
        if (isValidSignInWithEmail(name,email,phone,password)){
            mMovieBookingModel.signInWtihEmail(
                name = name,
                email = email,
                phone = phone,
                password = password,
                onSuccess = {
                    startActivity(HomeActivity.newIntent(this))
                }, onFailure = {
                    showErr(it)
                }
            )
        }

    }

    private fun isValidLoginWithEmail(email: String, password: String) : Boolean{
        return if (email.isEmpty() || password.isEmpty()) {
            showAndReturnFalse("Please fill Email and password!")
        } else true
    }

    private fun isValidSignInWithEmail(name: String, email: String, phone: String, password: String) : Boolean{
        return when {
            name.isEmpty() -> showAndReturnFalse("Please fill Name!")
            email.isEmpty() -> showAndReturnFalse("Please fill Email!")
            phone.isEmpty() -> showAndReturnFalse("Please fill Phone No.!")
            password.isEmpty() -> showAndReturnFalse("Please fill Password!")
            else -> true
        }
    }

    private fun showAndReturnFalse(msg : String) : Boolean{
        this.showErr(msg)
        return false
    }

    private fun showErr(message : String){
        Snackbar.make(window.decorView,message,Snackbar.LENGTH_LONG).show()
    }

}