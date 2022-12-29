package com.example.themoviebookingapp.persistance.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themoviebookingapp.data.vos.PaymentMethodVO

@Dao
interface PaymentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPaymentMethodList(paymentList : List<PaymentMethodVO>)

    @Query("SELECT * FROM payment_method")
    fun getPaymentMethodListByToken() : List<PaymentMethodVO>
}