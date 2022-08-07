package com.example.jobsearch.models.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobsearch.model.FavoriteJob
import com.example.jobsearch.model.Job
import com.example.jobsearch.models.repository.JobRepository
import kotlinx.coroutines.launch

class RemoteJobViewModel(app:Application,
private val remoteJobRepository:JobRepository):AndroidViewModel(app) {

    fun remoteJobResult() = remoteJobRepository.remoteJobResult()
    fun addFavJob(job: FavoriteJob) = viewModelScope.launch {
        remoteJobRepository.addFavoriteJob(job)
    }
    fun deleteJob(job: FavoriteJob) = viewModelScope.launch {
        remoteJobRepository.deleteJob(job)
    }
    fun getAllFavJobs() = remoteJobRepository.getAllFavJobs()

}