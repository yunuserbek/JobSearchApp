package com.example.jobsearch.models


import com.example.jobsearch.model.Job
import com.google.gson.annotations.SerializedName

data class JobResponse(
    @SerializedName("jobs")
    val jobs: List<Job?>?
)