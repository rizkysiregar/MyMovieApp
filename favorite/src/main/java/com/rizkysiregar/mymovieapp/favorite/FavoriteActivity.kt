package com.rizkysiregar.mymovieapp.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.rizkysiregar.mymovieapp.core.domain.model.Movie
import com.rizkysiregar.mymovieapp.core.ui.MovieAdapter
import com.rizkysiregar.mymovieapp.detail.DetailActivity
import com.rizkysiregar.mymovieapp.favorite.databinding.ActivityFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFavoriteBinding
    private val favoriteViewModel : FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "My Favorite"
        loadKoinModules(favoriteModule)

       val movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = { movie ->
            val intent = Intent(this,DetailActivity::class.java)
            intent.putExtra(DetailActivity.KEY, movie)
            startActivity(intent)
        }

        favoriteViewModel.movie.observe(this){ data ->
            movieAdapter.setData(data)
            showEmptyData(data)
        }

        with(binding.rvMovie) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    private fun showEmptyData(data: List<Movie>){
        if  ( data.isNotEmpty() ){
            binding.tvEmpty.visibility = View.GONE
        }else{
            binding.tvEmpty.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.rvMovie.adapter = null
    }
}