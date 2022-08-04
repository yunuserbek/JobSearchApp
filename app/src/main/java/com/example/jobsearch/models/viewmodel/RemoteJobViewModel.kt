package com.example.jobsearch.models.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.jobsearch.models.repository.JobRepository

class RemoteJobViewModel(app:Application,
private val remoteJobRepository:JobRepository):AndroidViewModel(app) {

    fun remoteJobResult() = remoteJobRepository.remoteJobResult()

}