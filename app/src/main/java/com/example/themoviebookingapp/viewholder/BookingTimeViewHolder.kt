package com.example.themoviebookingapp.viewholder

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebookingapp.adapter.BookingTimeAdapter
import com.example.themoviebookingapp.adapter.BookingTimeItemAdapter
import com.example.themoviebookingapp.data.vos.MovieBookingDaysVO
import com.example.themoviebookingapp.data.vos.TimeSlotDataVO
import com.example.themoviebookingapp.data.vos.TimeSlotVO
import com.example.themoviebookingapp.delegate.MovieListDelegate
import com.example.themoviebookingapp.delegate.TimeSlotDelegate
import kotlinx.android.synthetic.main.activity_booking_ticket.*
import kotlinx.android.synthetic.main.view_holder_booking_time.view.*
import kotlinx.android.synthetic.main.view_holder_day.view.*

class BookingTimeViewHolder(itemView: View,private val mDelegate: TimeSlotDelegate) : RecyclerView.ViewHolder(itemView) {

   lateinit var mTimeSlot: TimeSlotDataVO
   lateinit var mBookingTimeItemAdapter: BookingTimeItemAdapter

    fun bindData(timeSlot: TimeSlotDataVO){

        mBookingTimeItemAdapter = BookingTimeItemAdapter(mDelegate)
        itemView.rvGoldenCity.adapter = mBookingTimeItemAdapter

        timeSlot.timeslots?.let { mBookingTimeItemAdapter.setNewData(it) }

        mTimeSlot = timeSlot
        itemView.tvCinema.text = mTimeSlot.cinema

    }
}