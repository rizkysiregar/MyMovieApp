package com.rizkysiregar.mymovieapp.core.ui


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rizkysiregar.mymovieapp.core.R
import com.rizkysiregar.mymovieapp.core.databinding.ItemMovieListBinding
import com.rizkysiregar.mymovieapp.core.domain.model.Movie



class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ListViewHolder>(){

    companion object {
        const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w500"
    }

    private var listMovie = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: List<Movie>?) {
        if (newData == null) return
        listMovie.clear()
        listMovie.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie_list, parent,false))

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listMovie[position]
        holder.bind(data)
    }

    override fun getItemCount() = listMovie.size

    inner class ListViewHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = ItemMovieListBinding.bind(item)
        fun bind(data: Movie) {
            with(binding){
                Glide.with(itemView.context)
                    .load(BASE_URL_IMAGE + data.posterPath)
                    .into(ivPosterPath)
                titleMovie.text = data.title
                dateRelease.text = data.releaseDate

                if (data.adult){
                    ivIsAdult.visibility = View.VISIBLE
                }else{
                    ivIsAdult.visibility = View.GONE
                }
            }
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listMovie[bindingAdapterPosition])
            }
        }
    }
}