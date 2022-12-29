package com.example.themoviebookingapp.viewholder

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebookingapp.R
import com.example.themoviebookingapp.data.vos.MovieSeatVO
import com.example.themoviebookingapp.delegate.BookingSeatDelegate
import kotlinx.android.synthetic.main.view_holder_movie_seat.view.*

class MovieSeatViewHolder(itemView: View,private val mDelegate: BookingSeatDelegate) : RecyclerView.ViewHolder(itemView) {

    private var mSeatIndex : Int = 0
    init {
        itemView.setOnClickListener {
           mDelegate.onTabBookingSeat(mSeatIndex)
        }
    }


    fun bindData(data: MovieSeatVO, seatIndex : Int){
        mSeatIndex = seatIndex
        when (data.type) {
            "available" -> {
                itemView.tvMovieSeatTitle.visibility = View.GONE
                itemView.tvMovieSeatTitle.text = data.seatName
                itemView.tvMovieSeatTitle.textSize= 8.0F

                if(data.isSelected){
                    itemView.tvMovieSeatTitle.visibility = View.VISIBLE
                    itemView.background = ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.background_movie_seat_selected
                    )
                    itemView.tvMovieSeatTitle.setTextColor(ContextCompat.getColor(itemView.context,R.color.white))
                }else {
                    itemView.background = ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.background_movie_seat_available
                    )
                    itemView.tvMovieSeatTitle.setTextColor(ContextCompat.getColor(itemView.context,R.color.black))
                }
            }
            "taken" -> {
                itemView.tvMovieSeatTitle.visibility = View.VISIBLE
                itemView.tvMovieSeatTitle.text = data.seatName
                itemView.tvMovieSeatTitle.textSize= 8.0F

                itemView.background = ContextCompat.getDrawable(
                    itemView.context,
                    R.drawable.background_movie_seat_taken
                )

            }

            "text" -> {
                itemView.tvMovieSeatTitle.visibility = View.VISIBLE
                itemView.tvMovieSeatTitle.text = data.symbol
                itemView.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,R.color.white,
                    ))
            }
            else -> {
                itemView.tvMovieSeatTitle.visibility = View.GONE
                itemView.setBackgroundColor(
                    ContextCompat.getColor(itemView.context,R.color.white)
                )
            }
        }
    }
}