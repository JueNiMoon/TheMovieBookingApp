package com.example.themoviebookingapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebookingapp.R
import com.example.themoviebookingapp.data.vos.TimeSlotDataVO
import com.example.themoviebookingapp.delegate.TimeSlotDelegate
import com.example.themoviebookingapp.viewholder.BookingTimeViewHolder
import kotlinx.android.synthetic.main.view_holder_booking_time.view.*

class BookingTimeAdapter(val mDelegate: TimeSlotDelegate): RecyclerView.Adapter<BookingTimeViewHolder>()  {

    private lateinit var mTimeSlotList : List<TimeSlotDataVO>

    init {
        mTimeSlotList = listOf<TimeSlotDataVO>()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingTimeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_booking_time,parent,false)

        return BookingTimeViewHolder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: BookingTimeViewHolder, position: Int) {
        mTimeSlotList?.let {

            holder.bindData(mTimeSlotList[position])


        }
    }

    override fun getItemCount(): Int {
        return mTimeSlotList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(timeSlotList: List<TimeSlotDataVO>){
        mTimeSlotList = timeSlotList
        notifyDataSetChanged()
    }

}