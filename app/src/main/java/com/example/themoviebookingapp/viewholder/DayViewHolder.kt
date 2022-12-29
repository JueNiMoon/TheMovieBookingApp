package com.example.themoviebookingapp.viewholder

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themoviebookingapp.data.vos.GenreVO
import com.example.themoviebookingapp.data.vos.MovieBookingDaysVO
import com.example.themoviebookingapp.data.vos.MovieVO
import com.example.themoviebookingapp.delegate.DayDelegate
import com.example.themoviebookingapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_holder_day.view.*
import kotlinx.android.synthetic.main.view_holder_movie.view.*
import java.time.LocalDateTime

class DayViewHolder(itemView : View,private val mDelegate: DayDelegate) : RecyclerView.ViewHolder(itemView) {

    lateinit var mDay: MovieBookingDaysVO
    lateinit var showDayInDigit : String
    lateinit var showDayInText : String
    var mIndex = 0
    val whiteColor = "#FFFFFFFF"
    val darkerGreyColor = "#AAAAAA"


    init {
        itemView.setOnClickListener {

            mDay.bookingDays?.let {
                var returnDate = ""

                if(it.monthValue<10){
                    returnDate = it.year.toString()+"-0"+it.monthValue.toString()+"-"+it.dayOfMonth.toString()
                }else{
                    returnDate =it.year.toString()+"-"+it.monthValue.toString()+"-"+it.dayOfMonth.toString()
                }


                mDelegate.daySelect(returnDate, mIndex)
            }

        }
    }

    fun bindData(day: MovieBookingDaysVO, indexPosition : Int){
        mDay = day
        mIndex = indexPosition
        mDay.let {
            it.bookingDays?.let { mDate->
                showDayInDigit = mDate.dayOfMonth.toString() ?: ""
                showDayInText = mDate.dayOfWeek.toString().subSequence(0,3).toString()

                itemView.tvWeekDay.text = showDayInText
                itemView.tvDay.text = showDayInDigit
            }

            it.isSelected?.let { isSelected ->
                if(isSelected){
                    itemView.tvWeekDay.setTextColor(Color.parseColor(whiteColor))
                    itemView.tvDay.setTextColor(Color.parseColor(whiteColor))
                }else{
                    itemView.tvWeekDay.setTextColor(Color.parseColor(darkerGreyColor))
                    itemView.tvDay.setTextColor(Color.parseColor(darkerGreyColor))
                }
            }
        }
    }
}