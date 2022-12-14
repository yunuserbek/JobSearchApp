package com.example.jobsearch.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jobsearch.repo.JobRepository

class RemoteJobViewModelFactory (val app:Application,
private val remoteJobRepository: JobRepository
):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    return RemoteJobViewModel(app,remoteJobRepository) as T
    }
}