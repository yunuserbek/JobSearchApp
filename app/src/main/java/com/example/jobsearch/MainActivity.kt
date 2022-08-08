package com.example.jobsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.jobsearch.databinding.ActivityMainBinding
import com.example.jobsearch.db.FavoriteJobDatabase
import com.example.jobsearch.repo.JobRepository
import com.example.jobsearch.viewmodel.RemoteJobViewModel
import com.example.jobsearch.viewmodel.RemoteJobViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: RemoteJobViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title ="Job search"
        setUpViewModel()
    }

    private fun setUpViewModel() {
        val remotejobrepository = JobRepository(
            FavoriteJobDatabase(this)
        )
        val viewModelProviderFactory = RemoteJobViewModelFactory(application,remotejobrepository)
        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(RemoteJobViewModel::class.java)
    }


}