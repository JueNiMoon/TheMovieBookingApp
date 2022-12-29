package com.example.themoviebookingapp.data.vos

import com.google.gson.annotations.SerializedName

data class OrderSnackVO (

    @SerializedName("id")
    val id : Int?,
    @SerializedName("quantity")
    val quantity : Int?,
)