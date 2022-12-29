package com.example.themoviebookingapp.activity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.themoviebookingapp.R
import com.example.themoviebookingapp.models.MovieBookingModel
import com.example.themoviebookingapp.models.MovieBookingModelImpl
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_add_new_card.*
import java.util.*


class AddNewCardActivity : AppCompatActivity() {

    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl
    lateinit var userToken:String
    private lateinit var mMovieBookingModelImpl : MovieBookingModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_card)

        setUpListeners()
        getUserToken()
    }

    private fun getUserToken(){
        mMovieBookingModelImpl = MovieBookingModelImpl
        this.userToken = mMovieBookingModelImpl.getToken()
    }

    private fun isValid(): Boolean{
        return when {
            textInputCardNumber.text.isNullOrEmpty() -> showAndReturnFalse("Please Fill Card Number!")
            textInputCardHolder.text.isNullOrEmpty() -> showAndReturnFalse("Please Fill Card Holder!")
            textInputCVC.text.isNullOrEmpty() -> showAndReturnFalse("Please Fill CVC!")
            textInputExpDate.text.isNullOrEmpty() -> showAndReturnFalse("Please Fill Expire Date!")
            else -> true
        }

    }

    private fun showAndReturnFalse(msg : String) : Boolean{
        this.showErrMessage(msg)
        return false
    }

    private fun isValidDate(): Boolean{

        //check date format DD/MM
        var expDate : List<String> = listOf()
        expDate = textInputExpDate.text.toString().split('/')

        when {
            expDate.count() !=2 -> {
                return showAndReturnFalse("Invalid Expire Date!")
            }
            else -> {
                val month = expDate.getOrNull(1)?.toInt() ?:0
                val day = expDate.getOrNull(0)?.toInt() ?:0
                when {
                    month <= 0 || month>12 -> return  showAndReturnFalse("Invalid Month!")
                    day<= 0 || day> getLastDayOfMonth(month) -> return  showAndReturnFalse("Invalid Day!")
                }
            }
        }
        return  true
    }

    private fun getLastDayOfMonth(month:Int) : Int{
        return when (month) {
            2 -> 28
            4, 6, 9, 11 ->  30
            else ->  31
        }
    }

    private fun showErrMessage(message : String){
        Snackbar.make(window.decorView,message, Snackbar.LENGTH_LONG).show()
    }


    private fun setUpListeners(){

        btnAddNewCard.setOnClickListener {

            if(isValid() && isValidDate()){
                mMovieBookingModel.createNewCard(
                    card_number = textInputCardNumber.text.toString(),
                    card_holder = textInputCardHolder.text.toString(),
                    expiration_date = textInputExpDate.text.toString(),
                    cvc = textInputCVC.text.toString(),
                    authorization = this.userToken,
                    onSuccess = {
                        val returnIntent = Intent()
                        setResult(RESULT_OK, returnIntent)
                        finish()

                    },
                    onFailure = {
                        showErrMessage(it)
                    }
                )
            }
        }

        btnBackToPayment.setOnClickListener {
            onBackPressed()
        }

    }


}