package com.example.themoviebookingapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebookingapp.R
import com.example.themoviebookingapp.data.vos.ActorVO
import com.example.themoviebookingapp.data.vos.GenreVO
import com.example.themoviebookingapp.viewholder.CastViewHolder

class CastAdapter : RecyclerView.Adapter<CastViewHolder>() {
    private var mActorList: List<ActorVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_cast,parent,false)
        return CastViewHolder(view)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        if (mActorList.isNotEmpty()){
            holder.bindData(mActorList[position])
        }
    }

    override fun getItemCount(): Int {
       return mActorList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(actorList: List<ActorVO>){
        mActorList = actorList
        notifyDataSetChanged()
    }
}