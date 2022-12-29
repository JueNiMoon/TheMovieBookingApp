package com.example.themoviebookingapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebookingapp.R
import com.example.themoviebookingapp.data.vos.PaymentMethodVO
import com.example.themoviebookingapp.data.vos.SnackDataVO
import com.example.themoviebookingapp.delegate.PaymentMethodDelegate
import com.example.themoviebookingapp.delegate.SnackDelegate
import com.example.themoviebookingapp.viewholder.PaymentMethodViewHolder

class PaymentMethodAdapter(val mDelegate: PaymentMethodDelegate) : RecyclerView.Adapter<PaymentMethodViewHolder>(){

    private lateinit var mPaymentMethodList : List<PaymentMethodVO>
    init {
        mPaymentMethodList = listOf<PaymentMethodVO>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentMethodViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_payment_method,parent,false)
        return PaymentMethodViewHolder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: PaymentMethodViewHolder, position: Int) {
        mPaymentMethodList?.let {
            holder.bindData(mPaymentMethodList[position],position)
        }
    }

    override fun getItemCount(): Int {
        return mPaymentMethodList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(PaymentMethodList: List<PaymentMethodVO>){
        mPaymentMethodList = PaymentMethodList
        notifyDataSetChanged()
    }
}