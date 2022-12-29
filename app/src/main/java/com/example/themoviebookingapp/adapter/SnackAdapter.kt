package com.example.themoviebookingapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebookingapp.R
import com.example.themoviebookingapp.data.vos.SnackDataVO
import com.example.themoviebookingapp.delegate.SnackDelegate
import com.example.themoviebookingapp.viewholder.SnackViewHolder

class SnackAdapter(val mDelegate: SnackDelegate) : RecyclerView.Adapter<SnackViewHolder>() {

    private lateinit var mSnackList : List<SnackDataVO>


    init {
        mSnackList = listOf<SnackDataVO>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SnackViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_snack,parent,false)
        return SnackViewHolder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: SnackViewHolder, position: Int) {
        mSnackList?.let {
            holder.bindData(mSnackList[position],position)
        }
    }

    override fun getItemCount(): Int {
        return mSnackList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(dayList: List<SnackDataVO>){
        mSnackList = dayList
        notifyDataSetChanged()
    }
}