package com.devmeist3r.tmdbclient.presentation.artist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devmeist3r.tmdbclient.R
import com.devmeist3r.tmdbclient.data.model.artist.Artist
import com.devmeist3r.tmdbclient.databinding.ListItemBinding

class ArtistAdapter() : RecyclerView.Adapter<MyViewHolder>() {

  private val artistsList = ArrayList<Artist>()

  fun setList(artists: List<Artist>) {
    artistsList.clear()
    artistsList.addAll(artists)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    val binding: ListItemBinding = DataBindingUtil.inflate(
      layoutInflater,
      R.layout.list_item,
      parent,
      false
    )

    return MyViewHolder(binding)
  }

  override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    holder.bind(artistsList[position])
  }

  override fun getItemCount(): Int {
    return artistsList.size
  }

}

class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

  fun bind(artist: Artist) {
    binding.titleTextView.text = artist.name
    binding.descriptionTextView.text = artist.popularity.toString()

    val posterUrl = "https://image.tmdb.org/t/p/w500" + artist.profilePath

    Glide.with(binding.imageView.context)
      .load(posterUrl)
      .into(binding.imageView)
  }

}
