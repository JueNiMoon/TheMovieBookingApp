package com.example.themoviebookingapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebookingapp.R
import com.example.themoviebookingapp.data.vos.GenreVO
import com.example.themoviebookingapp.data.vos.MovieVO
import com.example.themoviebookingapp.delegate.MovieListDelegate
import com.example.themoviebookingapp.viewholder.MovieListViewHolder

class MovieListAdapter (val mDelegate: MovieListDelegate) :  RecyclerView.Adapter<MovieListViewHolder>() {

    private var mMovieList: List<MovieVO> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :MovieListViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie,parent,false)
        return MovieListViewHolder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        if (mMovieList.isNotEmpty()){
            holder.bindData(mMovieList[position])
        }
    }

    override fun getItemCount(): Int {
        return mMovieList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(movieList: List<MovieVO>){
        mMovieList = movieList
        notifyDataSetChanged()
    }
}