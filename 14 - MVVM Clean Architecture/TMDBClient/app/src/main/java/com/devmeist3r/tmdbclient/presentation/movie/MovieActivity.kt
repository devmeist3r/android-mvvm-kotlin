package com.devmeist3r.tmdbclient.presentation.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.devmeist3r.tmdbclient.R
import com.devmeist3r.tmdbclient.databinding.ActivityMovieBinding
import com.devmeist3r.tmdbclient.presentation.di.Injector
import javax.inject.Inject

class MovieActivity : AppCompatActivity() {

  @Inject
  lateinit var factory: MovieViewModelFactory

  private lateinit var movieViewModel: MovieViewModel
  private lateinit var binding: ActivityMovieBinding
  private lateinit var adapter: MovieAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)

    (application as Injector).createMovieSubComponent()
      .inject(this)

    movieViewModel = ViewModelProvider(this, factory)
      .get(MovieViewModel::class.java)

    initRecyclerView()
  }

  private fun initRecyclerView() {
    binding.movieRecyclerView.layoutManager = LinearLayoutManager(this)
    adapter = MovieAdapter()
    binding.movieRecyclerView.adapter = adapter
    displayPopularMovies()
  }

  private fun displayPopularMovies() {
    binding.movieProgressBar.visibility = View.VISIBLE
    val responseLiveData = movieViewModel.getMovies()

    responseLiveData.observe(this, Observer {
//      Log.i("MYTAG", it.toString())
      if (it != null) {
        adapter.setList(it)
        adapter.notifyDataSetChanged()
        binding.movieProgressBar.visibility = View.GONE
      } else {
        binding.movieProgressBar.visibility = View.VISIBLE
        Toast.makeText(this, "No avaliable data", Toast.LENGTH_LONG).show()
      }
    })
  }

}
