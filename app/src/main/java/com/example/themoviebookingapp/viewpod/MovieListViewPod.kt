package com.example.themoviebookingapp.viewpod

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themoviebookingapp.adapter.MovieListAdapter
import com.example.themoviebookingapp.data.vos.GenreVO
import com.example.themoviebookingapp.data.vos.MovieVO
import com.example.themoviebookingapp.delegate.MovieListDelegate
import kotlinx.android.synthetic.main.view_pod_movie_list.view.*


class MovieListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {

    lateinit var mMovieListAdapter: MovieListAdapter
    lateinit var mDelegate: MovieListDelegate

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    fun setUpMovieListViewPod(delegate: MovieListDelegate, title: String){
        tvNowShowing.text = title
        setDelegate(delegate)
        setupMovieRecyclerView()
    }

    private fun setDelegate(delegate: MovieListDelegate){
        this.mDelegate = delegate
    }

    private fun setupMovieRecyclerView()
    {
        mMovieListAdapter = MovieListAdapter(mDelegate)
        rvNowShowing.adapter = mMovieListAdapter
        rvNowShowing.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
    }

    fun setData(movieList: List<MovieVO>){
        mMovieListAdapter.setNewData(movieList)
    }
}