package com.example.popularmovielist.response

import com.example.popularmovielist.ui.Movie

data class MovieResponse(
    val results: List<Movie>

//    @field:SerializedName("original_title")
//    val title: String,
//
//    @field:SerializedName("overview")
//    val overview: String,
//
//    @field:SerializedName("poster_path")
//    val img: String,
//
//    @field:SerializedName("vote_average")
//    val voteAverage: Double,
//
//    @field:SerializedName("release_date")
//    val releaseDate: String
)
