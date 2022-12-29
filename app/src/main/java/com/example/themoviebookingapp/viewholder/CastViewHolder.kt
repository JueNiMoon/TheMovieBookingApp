package com.example.themoviebookingapp.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themoviebookingapp.R
import com.example.themoviebookingapp.data.vos.ActorVO
import com.example.themoviebookingapp.data.vos.GenreVO
import com.example.themoviebookingapp.utils.CAST_IMAGE_BASE_URL
import com.example.themoviebookingapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.view_holder_cast.view.*
import kotlinx.android.synthetic.main.view_holder_movie.view.*

class CastViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var mActorVO: ActorVO? = null

    fun bindData(actorVO: ActorVO){

        mActorVO = actorVO
        mActorVO?.let {
            Glide.with(itemView.ivProfile.context)
                .load("$CAST_IMAGE_BASE_URL${actorVO.profilePath}")
                .placeholder(R.mipmap.default_profile)
                .into(itemView.ivProfile)
        }

    }
}