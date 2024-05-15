package com.example.movielist.data.retrofit

import com.example.movielist.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    fun getUsers(): Call<ArrayList<UserResponse>>
}