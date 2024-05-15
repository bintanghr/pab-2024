package com.example.popularmovielist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.popularmovielist.R
import com.example.popularmovielist.data.retrofit.ApiConfig
import com.example.popularmovielist.response.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularMovieList : AppCompatActivity() {
    private lateinit var adapter: MovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popular_movie_list)

        val recyclerView : RecyclerView = findViewById(R.id.rvMovie)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MovieAdapter(this, arrayListOf())
        recyclerView.adapter = adapter
        getPopularMovies()
    }
    private fun getPopularMovies() {
        val client = ApiConfig.apiService().getPopularMovies()
        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    val movieResponse = response.body()
                    movieResponse?.results?.let {
                        adapter.setData(it)
                    }
                }else {
                    Log.e("MainActivity", "Response failed with code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("MainActivity", "onFailure: ${t.message}")
            }
        })
    }
}