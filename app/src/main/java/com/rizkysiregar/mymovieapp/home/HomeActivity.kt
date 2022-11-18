package com.rizkysiregar.mymovieapp.home

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager

import com.rizkysiregar.mymovieapp.R
import com.rizkysiregar.mymovieapp.about.AboutActivity
import com.rizkysiregar.mymovieapp.core.data.Resource
import com.rizkysiregar.mymovieapp.core.ui.MovieAdapter
import com.rizkysiregar.mymovieapp.databinding.ActivityHomeBinding
import com.rizkysiregar.mymovieapp.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    private val homeViewModel : HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Home"

        // adapter
        val movieAdapter = MovieAdapter()

        movieAdapter.onItemClick = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.KEY, it)
            startActivity(intent)
        }

        homeViewModel.movie.observe(this) { movie ->
            if (movie != null) {
                when(movie){
                    is Resource.Loading -> {
                        showLoading(true)
                        binding.tvError.visibility = View.GONE
                    }
                    is Resource.Success -> {
                        movieAdapter.setData(movie.data)
                        showLoading(false)
                        binding.tvError.visibility = View.GONE
                    }
                    is Resource.Error -> {
                        showLoading(false)
                        binding.tvError.visibility = View.VISIBLE
                    }
                }
            }
        }
        binding.rvMovie.apply {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu_favorite -> {
                val uri = Uri.parse("movieapp://favorite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
                true
            }
            R.id.menu_about -> {
                val intent = Intent(this,AboutActivity::class.java)
                startActivity(intent)
                true
            }

            else -> true
        }
    }

    private fun showLoading(isLoading: Boolean) {
        with(binding) {
            if (isLoading){
                homePb.visibility = View.VISIBLE
            }else{
                homePb.visibility = View.GONE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.rvMovie.adapter = null
    }

}