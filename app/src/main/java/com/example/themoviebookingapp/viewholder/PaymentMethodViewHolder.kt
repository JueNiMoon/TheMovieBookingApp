package com.example.themoviebookingapp.viewholder

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebookingapp.R
import com.example.themoviebookingapp.data.vos.PaymentMethodVO
import com.example.themoviebookingapp.delegate.PaymentMethodDelegate
import kotlinx.android.synthetic.main.view_holder_payment_method.view.*

class PaymentMethodViewHolder(itemView: View,private val mDelegate: PaymentMethodDelegate) : RecyclerView.ViewHolder(itemView) {

    var mIndex : Int = 0

    init {
        itemView.setOnClickListener {
            mDelegate.onTabPaymentMethod(mIndex)
        }
    }

    fun bindData(paymentMethodVO : PaymentMethodVO, index : Int){
        mIndex = index
        itemView.tvPaymentType.text = paymentMethodVO.description
        itemView.tvcardtype.text = paymentMethodVO.name

        if(paymentMethodVO.isSelected){
            itemView.tvPaymentType.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorPrimary))
            itemView.tvcardtype.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorPrimary))
            itemView.iconCard.setImageResource(R.drawable.ic_baseline_credit_card_color_primary_24dp)
        }
        else{
            itemView.tvPaymentType.setTextColor(ContextCompat.getColor(itemView.context, R.color.black))
            itemView.tvcardtype.setTextColor(ContextCompat.getColor(itemView.context, R.color.black))
            itemView.iconCard.setImageResource(R.drawable.ic_baseline_credit_card_black_24dp)
        }
    }
}