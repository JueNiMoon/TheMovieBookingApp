package com.example.themoviebookingapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.themoviebookingapp.persistance.typeconverters.CardListTypeConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user_data")
@TypeConverters(
        CardListTypeConverter::class,
)
data class UserDataVO (
        @SerializedName("id")
        @ColumnInfo(name = "id")
        @PrimaryKey
        val id: Int?,

        @SerializedName("name")
        @ColumnInfo(name = "name")
        val name: String?,

        @SerializedName("email")
        @ColumnInfo(name = "email")
        val email: String?,

        @SerializedName("phone")
        @ColumnInfo(name = "phone")
        val phone: String?,

        @SerializedName("total_expense")
        @ColumnInfo(name = "total_expense")
        val total_expense: Int?,

        @SerializedName("profile_image")
        @ColumnInfo(name = "profile_image")
        val profile_image: String?,

        @SerializedName("cards")
        @ColumnInfo(name = "cards")
        val cards: List<CardVO>?,

        @ColumnInfo(name = "token")
        var token : String?
)