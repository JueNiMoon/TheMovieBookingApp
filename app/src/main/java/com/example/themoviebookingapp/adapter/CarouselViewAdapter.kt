package com.example.themoviebookingapp.adapter

import alirezat775.lib.carouselview.Carousel
import alirezat775.lib.carouselview.CarouselAdapter
import alirezat775.lib.carouselview.CarouselAdapter.CarouselViewHolder
import alirezat775.lib.carouselview.helper.ViewHelper
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebookingapp.R
import com.example.themoviebookingapp.data.vos.CardVO
import com.example.themoviebookingapp.data.vos.PaymentMethodVO
import com.example.themoviebookingapp.viewholder.CardViewHolder
import kotlin.math.roundToInt

class CarouselViewAdapter : CarouselAdapter()  {

    private lateinit var mcardVOList : List<CardVO>

    init {
        mcardVOList = listOf<CardVO>()

    }


    override fun getItemCount(): Int {
        return 0
    }

    override fun onBindViewHolder(holder: CarouselAdapter.CarouselViewHolder, position: Int) {
        mcardVOList?.let {

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CarouselAdapter.CarouselViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_card,parent,false)
        return CarouselViewHolder(view)
    }
//
//    @SuppressLint("NotifyDataSetChanged")
//    fun setNewData(cardVOList: List<CardVO>){
//        mcardVOList = cardVOList
//        notifyDataSetChanged()
//    }
}