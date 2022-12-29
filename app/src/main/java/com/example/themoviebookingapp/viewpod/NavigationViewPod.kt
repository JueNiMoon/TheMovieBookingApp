package com.example.themoviebookingapp.viewpod

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.themoviebookingapp.adapter.MovieListAdapter
import com.example.themoviebookingapp.delegate.MovieListDelegate
import com.example.themoviebookingapp.delegate.NavigationDelegate
import kotlinx.android.synthetic.main.view_pod_movie_list.view.*
import kotlinx.android.synthetic.main.view_pod_navigation.view.*

class NavigationViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {

    lateinit var mDelegate: NavigationDelegate

    companion object{
        const val ARG_NAME = "name"


    }

    override fun onFinishInflate() {

        tvLogout.setOnClickListener {
            mDelegate.onTabNavigationItemLogout()
        }
        super.onFinishInflate()
    }



    fun setUpNavigationViewPod(delegate: NavigationDelegate){
        setDelegate(delegate)
    }

    private fun setDelegate(delegate: NavigationDelegate){
        this.mDelegate = delegate

    }


}