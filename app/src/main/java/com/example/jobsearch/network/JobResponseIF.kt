package com.example.jobsearch.network


import com.example.jobsearch.model.JobResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface JobResponseIF {
    @GET("remote-jobs?limit=200")
    fun getRemoteJobResponse(): Call<JobResponse>

    @GET("remote-jobs")
    fun searchJob(@Query("Search")query: String?):Call<JobResponse>
}