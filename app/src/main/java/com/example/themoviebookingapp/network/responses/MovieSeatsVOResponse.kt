package com.example.themoviebookingapp.network.responses

import com.example.themoviebookingapp.data.vos.MovieSeatVO
import com.google.gson.annotations.SerializedName

data class MovieSeatsVOResponse (
    @SerializedName("code")
    val code : Int?,
    @SerializedName("message")
    val message : String?,
    @SerializedName("data")
    val data : List<List<MovieSeatVO>>?,
){
    fun getMovieSeatList() : List<MovieSeatVO>{
        var mMovieSeatList = listOf<MovieSeatVO>()
        val  let = data?.let {
            mMovieSeatList = data.flatten()
        }
        return mMovieSeatList
    }
}