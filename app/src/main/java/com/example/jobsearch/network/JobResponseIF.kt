package com.example.jobsearch.network


import com.example.jobsearch.models.JobResponse
import retrofit2.Call
import retrofit2.http.GET

interface JobResponseIF {
    @GET("remote-jobs")
    fun getRemoteJobResponse(): Call<JobResponse>
}