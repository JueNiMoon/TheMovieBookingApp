package com.example.themoviebookingapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebookingapp.R
import com.example.themoviebookingapp.data.vos.TimeSlotDataVO
import com.example.themoviebookingapp.data.vos.TimeSlotVO
import com.example.themoviebookingapp.delegate.DayDelegate
import com.example.themoviebookingapp.delegate.MovieListDelegate
import com.example.themoviebookingapp.delegate.TimeSlotDelegate
import com.example.themoviebookingapp.viewholder.BookingTimeItemViewHolder

class BookingTimeItemAdapter(val mDelegate: TimeSlotDelegate) : RecyclerView.Adapter<BookingTimeItemViewHolder>() {

    lateinit var mTimeSlotList : List<TimeSlotVO>

    init {
        mTimeSlotList = listOf<TimeSlotVO>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingTimeItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_booking_time_item,parent,false)
        return BookingTimeItemViewHolder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: BookingTimeItemViewHolder, position: Int) {
        mTimeSlotList?.let {
            holder.bindData(mTimeSlotList[position], mTimeSlotList[position].cinemaDayTimeslotId?:0)
        }
    }

    override fun getItemCount(): Int {
        return mTimeSlotList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(timeSlotList: List<TimeSlotVO>){
        mTimeSlotList = timeSlotList
        notifyDataSetChanged()
    }


}