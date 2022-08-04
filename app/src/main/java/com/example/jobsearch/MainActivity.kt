package com.example.jobsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.jobsearch.databinding.ActivityMainBinding
import com.example.jobsearch.models.repository.JobRepository
import com.example.jobsearch.models.viewmodel.RemoteJobViewModel
import com.example.jobsearch.models.viewmodel.RemoteJobViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel:RemoteJobViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title ="Job search"
        setUpViewModel()
    }

    private fun setUpViewModel() {
        val remotejobrepository = JobRepository()
        val viewModelProviderFactory = RemoteJobViewModelFactory(application,remotejobrepository)
        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(RemoteJobViewModel::class.java)
    }


}