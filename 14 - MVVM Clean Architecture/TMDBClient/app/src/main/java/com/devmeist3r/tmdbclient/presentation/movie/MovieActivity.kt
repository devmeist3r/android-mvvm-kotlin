package com.devmeist3r.tmdbclient.presentation.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.devmeist3r.tmdbclient.R
import com.devmeist3r.tmdbclient.databinding.ActivityMovieBinding
import com.devmeist3r.tmdbclient.presentation.di.Injector
import javax.inject.Inject

class MovieActivity : AppCompatActivity() {

  @Inject
  private lateinit var factory: MovieViewModelFactory
  private lateinit var movieViewModel: MovieViewModel
  private lateinit var binding: ActivityMovieBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)

    (application as Injector).createMovieSubComponent()
      .inject(this)

    movieViewModel = ViewModelProvider(this, factory)
      .get(MovieViewModel::class.java)

    val responseLiveData = movieViewModel.getMovies()
    responseLiveData.observe(this, Observer {
      Log.i("MYTAG", it.toString())
    })
  }


}
