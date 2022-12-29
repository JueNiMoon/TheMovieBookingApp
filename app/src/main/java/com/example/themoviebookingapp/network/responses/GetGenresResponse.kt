package com.example.themoviebookingapp.network.responses

import com.example.themoviebookingapp.data.vos.GenreVO
import com.google.gson.annotations.SerializedName

data class GetGenresResponse (
    @SerializedName("genres")
    val genres: List<GenreVO>?
)