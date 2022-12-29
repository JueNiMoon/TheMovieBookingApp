package com.example.themoviebookingapp.viewholder

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebookingapp.R
import com.example.themoviebookingapp.data.vos.TimeSlotVO
import com.example.themoviebookingapp.delegate.TimeSlotDelegate
import kotlinx.android.synthetic.main.view_holder_booking_time_item.view.*

class BookingTimeItemViewHolder(itemView: View,private val mDelegate: TimeSlotDelegate) : RecyclerView.ViewHolder(itemView) {

    lateinit var mTimeSlot: TimeSlotVO
    var mTimeSlotIndex : Int = 0

    init {
        itemView.setOnClickListener {
            mTimeSlot?.let {
                mDelegate.onTapTimeSlot(mTimeSlotIndex)
            }
        }
    }

    fun bindData(timeSlot: TimeSlotVO,timeSlotID: Int){
        mTimeSlot = timeSlot
        mTimeSlotIndex = timeSlotID

        itemView.tvTimeSlot.text = mTimeSlot.startTime
        if(mTimeSlot.isSelected){
            itemView.tvTimeSlot.setBackgroundResource(R.drawable.background_movie_item_colorprimary)
            itemView.tvTimeSlot.setTextColor(ContextCompat.getColor(itemView.context,R.color.white))
        }else{
            itemView.tvTimeSlot.setBackgroundResource(R.drawable.background_movie_time_white)
            itemView.tvTimeSlot.setTextColor(ContextCompat.getColor(itemView.context,R.color.black))
        }
    }
}