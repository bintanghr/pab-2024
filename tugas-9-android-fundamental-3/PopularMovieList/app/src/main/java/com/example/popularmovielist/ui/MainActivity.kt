package com.example.popularmovielist.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.popularmovielist.R
import com.example.popularmovielist.data.retrofit.ApiConfig
import com.example.popularmovielist.response.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var tvTitle : TextView
    private lateinit var tvRating : TextView
    private lateinit var tvOverview : TextView
    private lateinit var tvReleaseDate : TextView
    private lateinit var btnShowMore: Button
    private lateinit var imgPoster: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTitle = findViewById(R.id.mostPopularTitle)
        tvRating = findViewById(R.id.mostPopularRating)
        tvOverview = findViewById(R.id.mostPopularOverview)
        tvReleaseDate = findViewById(R.id.mostPopularReleaseDate)
        imgPoster = findViewById(R.id.mostPopularPoster)
        btnShowMore = findViewById(R.id.btnShowMore)
        btnShowMore.setOnClickListener(this)

        getPopularMovies()
    }

    private fun getPopularMovies() {
        val client = ApiConfig.apiService().getPopularMovies()
        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    val movieResponse = response.body()
                    movieResponse?.results?.let {
                        val mostPopularMovie = it[0]
                        displayMovieData(mostPopularMovie)
                    }
                    Log.d("MainActivity", "Response failed with code: $movieResponse")
                }else {
                    Log.e("MainActivity", "Response failed with code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("MainActivity", "onFailure: ${t.message}")
            }
        })
    }

    fun displayMovieData(movie: Movie) {
        val ratingText = "⭐ " + movie.voteAverage.toString()
        tvTitle.text = movie.title
        tvRating.text = ratingText
        tvOverview.text = movie.overview
        tvReleaseDate.text = movie.releaseDate
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
            .into(imgPoster)
    }
    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btnShowMore -> {
                val showMoreIntent = Intent(this, PopularMovieList::class.java)
                startActivity(showMoreIntent)
            }
        }
    }
}