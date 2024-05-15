package com.example.popularmovielist.data.retrofit

import com.example.popularmovielist.response.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("movie/popular?api_key=5a89cae6a766d9e4328f259850cbc0d7")
    fun getPopularMovies(): Call<MovieResponse>
}