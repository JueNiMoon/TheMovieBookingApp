package com.example.themoviebookingapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class CardVO (

    @SerializedName("id")
    val id: Int?,

    @SerializedName("card_holder")
    val cardHolder: String?,

    @SerializedName("card_number")
    val cardNumber: String?,

    @SerializedName("expiration_date")
    val expirationDate: String?,

    @SerializedName("card_type")
    val cardType: String?,
)