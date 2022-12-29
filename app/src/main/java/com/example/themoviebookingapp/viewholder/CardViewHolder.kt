package com.example.themoviebookingapp.viewholder

import android.view.View
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebookingapp.R
import com.example.themoviebookingapp.data.vos.CardVO
import com.example.themoviebookingapp.data.vos.PaymentMethodVO
import com.example.themoviebookingapp.data.vos.TimeSlotVO
import com.example.themoviebookingapp.delegate.CardDelegate
import com.example.themoviebookingapp.delegate.TimeSlotDelegate
import kotlinx.android.synthetic.main.view_holder_booking_time_item.view.*
import kotlinx.android.synthetic.main.view_holder_card.view.*
import kotlinx.android.synthetic.main.view_holder_payment_method.view.*

class CardViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    lateinit var mcardVO: CardVO

    init {

    }


    fun bindData(cardVO : CardVO){

        mcardVO = cardVO

        itemView.tvStar1.text = cardVO.cardNumber
        itemView.tvNo.text = cardVO.id.toString()
        itemView.tvCardHolderName.text = cardVO.cardHolder
        itemView.tvExpireValue.text = cardVO.expirationDate

    }
}