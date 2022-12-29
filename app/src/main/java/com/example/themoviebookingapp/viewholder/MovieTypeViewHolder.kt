package com.example.themoviebookingapp.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themoviebookingapp.data.vos.GenreVO
import com.example.themoviebookingapp.data.vos.MovieVO
import com.example.themoviebookingapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_holder_movie.view.*
import kotlinx.android.synthetic.main.view_holder_movie.view.tvMovieType
import kotlinx.android.synthetic.main.view_holder_movie_type.view.*

class MovieTypeViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var mGenreList: GenreVO? = null

    fun bindData(genreList: GenreVO){

        mGenreList = genreList
        mGenreList?.let {
            itemView.tvMovieType.text = it.name ?: ""
        }

    }

}