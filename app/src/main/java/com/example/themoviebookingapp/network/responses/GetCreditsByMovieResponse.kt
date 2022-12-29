package com.example.themoviebookingapp.network.responses

import com.example.themoviebookingapp.data.vos.ActorVO
import com.google.gson.annotations.SerializedName

data class GetCreditsByMovieResponse(
    @SerializedName("cast")
    val cast: List<ActorVO>?,
    @SerializedName("crew")
    val crew: List<ActorVO>?,
)