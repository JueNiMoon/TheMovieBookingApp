package com.example.themoviebookingapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebookingapp.R
import com.example.themoviebookingapp.data.vos.GenreVO
import com.example.themoviebookingapp.data.vos.MovieVO
import com.example.themoviebookingapp.viewholder.MovieTypeViewHolder

class MovieTypeAdapter : RecyclerView.Adapter<MovieTypeViewHolder>() {

    private var mGenreList: List<GenreVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieTypeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie_type,parent,false)
        return MovieTypeViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieTypeViewHolder, position: Int) {
        if (mGenreList.isNotEmpty()){
            holder.bindData(mGenreList[position])
        }
    }

    override fun getItemCount(): Int {
        return mGenreList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(genreList: List<GenreVO>){
        mGenreList = genreList
        notifyDataSetChanged()
    }
}