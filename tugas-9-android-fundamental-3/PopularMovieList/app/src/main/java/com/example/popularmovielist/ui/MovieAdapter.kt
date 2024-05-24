package com.example.popularmovielist.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.popularmovielist.R

class MovieAdapter(private val context: Context, private var data: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = data[position]
        val textRating = "⭐ " + movie.voteAverage.toString()

        holder.tvTitle.text = movie.title
        holder.tvOverview.text = movie.overview
        holder.tvRating.text = textRating
        holder.tvReleaseDate.text = movie.releaseDate

        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
            .into(holder.posterImage)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(newData: List<Movie>) {
        data = newData
        notifyDataSetChanged()
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvOverview: TextView = itemView.findViewById(R.id.tvOverview)
        val tvRating: TextView = itemView.findViewById(R.id.tvRating)
        val tvReleaseDate: TextView = itemView.findViewById(R.id.tvReleaseDate)
        val posterImage: ImageView = itemView.findViewById(R.id.posterImage)
    }
}