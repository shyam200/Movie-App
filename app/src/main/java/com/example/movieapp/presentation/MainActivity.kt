package com.example.movieapp.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.presentation.di.Injector
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var movieViewModel: MovieViewModel

    @Inject
    lateinit var factory: MovieViewModelFactory
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        (application as Injector).createMovieSubComponent().inject(this)

        movieViewModel = ViewModelProvider(this, factory).get(MovieViewModel::class.java)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        movieAdapter = MovieAdapter()
        binding.recyclerView.adapter = movieAdapter
        displayPopuplarMovies()
    }

    private fun displayPopuplarMovies() {
        binding.movieProgressBar.visibility = View.VISIBLE
        val responseLiveData = movieViewModel.getMovies()
        responseLiveData.observe(this, Observer {
            if (it != null) {
                movieAdapter.submitList(it)
//                movieAdapter.setMovieList(it)
//                movieAdapter.notifyDataSetChanged()
                binding.movieProgressBar.visibility = View.GONE
            } else {
                binding.movieProgressBar.visibility = View.GONE
                Toast.makeText(applicationContext, "No Data available", Toast.LENGTH_SHORT).show()
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.update, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_update -> {
                updateMovies()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateMovies(): Boolean {
        binding.movieProgressBar.visibility = View.VISIBLE
        val response = movieViewModel.updateMovieUseCase()
        response.observe(this, Observer {
            if (it != null) {
                movieAdapter.submitList(it)
//                movieAdapter.notifyDataSetChanged()
                binding.movieProgressBar.visibility = View.GONE
            } else {
                binding.movieProgressBar.visibility = View.GONE
            }
        })
        return true
    }
}