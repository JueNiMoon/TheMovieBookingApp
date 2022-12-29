package com.example.themoviebookingapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebookingapp.R
import com.example.themoviebookingapp.data.vos.MovieBookingDaysVO
import com.example.themoviebookingapp.data.vos.MovieVO
import com.example.themoviebookingapp.delegate.DayDelegate
import com.example.themoviebookingapp.delegate.MovieListDelegate
import com.example.themoviebookingapp.viewholder.DayViewHolder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DayAdapter(val mDelegate:DayDelegate) : RecyclerView.Adapter<DayViewHolder>(){

    lateinit var mDayList: MutableList<MovieBookingDaysVO>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_day,parent,false)
        return DayViewHolder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        mDayList?.let {
            holder.bindData(mDayList[position],position)
        }
    }

    override fun getItemCount(): Int {
        return mDayList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(dayList: MutableList<MovieBookingDaysVO>){
        mDayList = dayList
        notifyDataSetChanged()
    }
}