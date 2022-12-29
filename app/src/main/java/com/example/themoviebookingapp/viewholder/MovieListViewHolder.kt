package com.example.themoviebookingapp.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themoviebookingapp.data.vos.GenreVO
import com.example.themoviebookingapp.data.vos.MovieVO
import com.example.themoviebookingapp.delegate.MovieListDelegate
import com.example.themoviebookingapp.models.MovieBookingModelImpl
import com.example.themoviebookingapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_holder_movie.view.*

class MovieListViewHolder(itemView: View, private val mDelegate: MovieListDelegate) : RecyclerView.ViewHolder(itemView) {

    private var mMovieVO: MovieVO? = null
    lateinit var mMovieBookingModelImpl : MovieBookingModelImpl
    lateinit var mGenreList:List<GenreVO>

    init {

        mMovieBookingModelImpl = MovieBookingModelImpl

        itemView.setOnClickListener {
            mMovieVO?.let {
                mDelegate.onTabMovie(it.id)
            }

        }
    }

    fun getGenreTypeWithSlashSeparated(genIdsList : List<Int>) : String{
        var genreType = ""
        var genrecount = genIdsList.count()-1
        mMovieBookingModelImpl.genreList?.let {
            mGenreList = it
            for(id in 0..genrecount){
                for (item in mGenreList)
                {
                    if(genIdsList[id] == item.id){
                        if(id < genrecount){genreType = "$genreType" + item.name+"/"}
                        else {genreType = "$genreType" + item.name}

                        break
                    }
                }
            }
        }

        return genreType
    }

    fun bindData(movie: MovieVO){

        var genreType = movie.genreIds?.let { getGenreTypeWithSlashSeparated(it) } ?: ""

        mMovieVO = movie
        Glide.with(itemView.context)
            .load("${IMAGE_BASE_URL}${movie.posterPath}")
            .into(itemView.ivMovieItem)

        itemView.tvMovieName.text = movie.title
        itemView.tvMovieType.text = genreType
    }
}