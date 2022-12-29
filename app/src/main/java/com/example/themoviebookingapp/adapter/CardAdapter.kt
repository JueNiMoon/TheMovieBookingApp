package com.example.themoviebookingapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebookingapp.R
import com.example.themoviebookingapp.data.vos.CardVO
import com.example.themoviebookingapp.delegate.CardDelegate
import com.example.themoviebookingapp.delegate.TimeSlotDelegate
import com.example.themoviebookingapp.viewholder.BookingTimeViewHolder
import com.example.themoviebookingapp.viewholder.CardViewHolder

class CardAdapter(): RecyclerView.Adapter<CardViewHolder>() {

    private lateinit var mcardVOList : List<CardVO>
    init {
        mcardVOList = listOf<CardVO>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_card,parent,false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        mcardVOList.let {
            holder.bindData(it[position])
        }
    }

    override fun getItemCount(): Int {
       return mcardVOList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(cardVOList: List<CardVO>){
        mcardVOList = cardVOList
        notifyDataSetChanged()
    }
}