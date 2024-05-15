package com.example.popularmovielist.ui

import com.google.gson.annotations.SerializedName

data class Movie(
    @field:SerializedName("original_title")
    val title: String,

    val overview: String,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("release_date")
    val releaseDate: String,

    @SerializedName("poster_path")
    val posterPath: String
)
