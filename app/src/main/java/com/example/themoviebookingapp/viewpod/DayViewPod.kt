package com.example.themoviebookingapp.viewpod

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themoviebookingapp.adapter.DayAdapter
import com.example.themoviebookingapp.adapter.MovieListAdapter
import com.example.themoviebookingapp.delegate.DayDelegate
import com.example.themoviebookingapp.delegate.MovieListDelegate
import kotlinx.android.synthetic.main.view_pod_movie_list.view.*

class DayViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {

    lateinit var mDayAdapter: DayAdapter
    lateinit var mDelegate: DayDelegate


    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    fun setUpMovieListViewPod(delegate: DayDelegate){
        setDelegate(delegate)
        setupMovieRecyclerView()
    }

    private fun setDelegate(delegate: DayDelegate){
        this.mDelegate = delegate
    }

    private fun setupMovieRecyclerView()
    {
        mDayAdapter = DayAdapter(mDelegate)
        rvNowShowing.adapter = mDayAdapter
        rvNowShowing.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
    }
}