package com.rizkysiregar.mymovieapp.detail

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.rizkysiregar.mymovieapp.R
import com.rizkysiregar.mymovieapp.core.domain.model.Movie
import com.rizkysiregar.mymovieapp.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailActivity : AppCompatActivity() {
    companion object {
        const val KEY = "key"
        const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w500"
    }

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel : DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        @Suppress("DEPRECATION")
        val movieData = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(KEY, Movie::class.java)
        } else {
            intent.getParcelableExtra<Movie>(KEY)
        }

        showDetailMovie(movieData)
    }

    private fun showDetailMovie(movie: Movie?) {
        movie?.let { movie ->
            supportActionBar?.title = movie.title

            Glide.with(this@DetailActivity)
                .load(BASE_URL_IMAGE + movie.posterPath)
                .into(binding.ivPosterDetail)

            binding.tvTitleDetail.text = resources.getString(R.string.title_movie, movie.title)
            binding.tvOriginalTitle.text = resources.getString(R.string.original_title, movie.originalTitle)
            binding.tvOriginalLanguage.text = resources.getString(R.string.original_lang,movie.originalLanguage)
            binding.tvReleaseDate.text = resources.getString(R.string.release_date, movie.releaseDate)
            binding.tvPopularity.text = resources.getString(R.string.popularity, movie.popularity)
            binding.tvOverview.text = movie.overview

            var isFavorite = movie.favorite
            setFavorite(isFavorite)
            binding.fabFavorite.setOnClickListener {
                isFavorite = !isFavorite
                detailViewModel.setFavoriteMovie(movie, isFavorite)
                setFavorite(isFavorite)
            }
        }
    }

    private fun setFavorite(state: Boolean) {
        if (state){
            binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.ic_baseline_favorite_red_24))
        }else{
            binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.ic_baseline_favorite_border_24))
        }
    }
}