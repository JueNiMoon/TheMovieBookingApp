package com.example.themoviebookingapp.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebookingapp.data.vos.SnackDataVO
import com.example.themoviebookingapp.delegate.SnackDelegate
import com.example.themoviebookingapp.delegate.TimeSlotDelegate
import kotlinx.android.synthetic.main.view_holder_snack.view.*

class SnackViewHolder(itemView: View, private val mDelegate: SnackDelegate) : RecyclerView.ViewHolder(itemView) {

    var mIndex : Int = 0

    init {
        itemView.tvPlusBtn.setOnClickListener {
            mDelegate.onTabPlusBtn(index = mIndex)
        }

        itemView.tvMinusBtn.setOnClickListener {
            mDelegate.onTabMinusBtn(index = mIndex)
        }
    }
    fun bindData(sncakDataVO : SnackDataVO, index : Int){
        mIndex = index
        itemView.tvComboSet.text = sncakDataVO.name
        itemView.tvSetDescription.text = sncakDataVO.description
        itemView.tvPrice.text = sncakDataVO.price.toString()+"\$"
        itemView.tvQtyBtn.text = sncakDataVO.count.toString()
    }
}