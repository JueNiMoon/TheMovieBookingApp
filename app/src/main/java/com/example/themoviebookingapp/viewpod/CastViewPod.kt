package com.example.themoviebookingapp.viewpod

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themoviebookingapp.adapter.CastAdapter
import com.example.themoviebookingapp.data.vos.ActorVO
import com.example.themoviebookingapp.data.vos.MovieVO
import kotlinx.android.synthetic.main.view_pod_cast.view.*

class CastViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {

    lateinit var mCastAdapter: CastAdapter

    override fun onFinishInflate() {
        setUpCommingSoonMovieRecyclerView()
        super.onFinishInflate()
    }

    private fun setUpCommingSoonMovieRecyclerView(){
        mCastAdapter = CastAdapter()
        rvCast.adapter = mCastAdapter
        rvCast.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL,false)
    }

    fun setData(actorList: List<ActorVO>){
        mCastAdapter.setNewData(actorList)
    }
}