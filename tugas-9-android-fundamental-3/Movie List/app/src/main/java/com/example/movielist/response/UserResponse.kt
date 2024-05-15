package com.example.movielist.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @field:SerializedName("original_title")
    val title: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("poster_path")
    val img: String,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("original_title")
    val releaseDate: String
)
