package com.example.movieapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.data.model.Movie
import com.example.movieapp.databinding.MovieListItemBinding

class MovieAdapter : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: MovieListItemBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.movie_list_item,
            parent,
            false,
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: MovieViewHolder, position: Int) {
        viewHolder.bind(getItem(position))
    }

    class MovieViewHolder(val binding: MovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.movieTitle.text = movie.title
            binding.movieDescription.text = movie.overview

            val imageURL = "https://image.tmdb.org/t/p/w500" + movie.posterPath

            Glide.with(binding.movieImage.context).load(imageURL).into(binding.movieImage)
        }
    }

    private class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}