package com.example.themoviebookingapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebookingapp.R
import com.example.themoviebookingapp.data.vos.MovieSeatVO
import com.example.themoviebookingapp.delegate.BookingSeatDelegate
import com.example.themoviebookingapp.delegate.MovieListDelegate
import com.example.themoviebookingapp.viewholder.MovieSeatViewHolder

//class MovieSeatAdapter(private var mMovieSeats: List<MovieSeatVO> = listOf(),val mDelegate: BookingSeatDelegate) : RecyclerView.Adapter<MovieSeatViewHolder>() {
class MovieSeatAdapter(val mDelegate: BookingSeatDelegate) : RecyclerView.Adapter<MovieSeatViewHolder>() {

    private var mMovieSeats: List<MovieSeatVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSeatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie_seat,parent,false)
        return MovieSeatViewHolder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: MovieSeatViewHolder, position: Int) {
//        if (mMovieSeats.isNotEmpty()){
//            holder.bindData(mMovieSeats[position],position)
//        }
        mMovieSeats?.let {
           // holder.bindData(it[position],it[position].id?:0)
            holder.bindData(it[position],position)
        }
    }

    override fun getItemCount(): Int {
        return mMovieSeats.count()
    }

    fun setNewData(movieSeats: List<MovieSeatVO>){
        this.mMovieSeats = movieSeats
        notifyDataSetChanged()
    }
}