package com.example.themoviebookingapp.data.vos

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SnackParcelize (
    val id : Int?,
    val quantity : Int?,
) : Parcelable
