package com.example.jobsearch.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jobsearch.db.FavoriteJobDatabase
import com.example.jobsearch.model.FavoriteJob
import com.example.jobsearch.model.JobResponse
import com.example.jobsearch.network.RetrofitInstance
import com.example.jobsearch.utils.Constants.TAG
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Query


class JobRepository (private var db :FavoriteJobDatabase){
    private val jobservice = RetrofitInstance.apiService
    private val jobResponseLiveData:MutableLiveData<JobResponse> = MutableLiveData()
    private val searchResponseLiveData:MutableLiveData<JobResponse> = MutableLiveData()
    init {
        getRemoteResponse()
    }
    private fun getRemoteResponse(){

        jobservice.getRemoteJobResponse().enqueue(
            object :Callback<JobResponse>{
                override fun onResponse(call: Call<JobResponse>, response: Response<JobResponse>) {
                   jobResponseLiveData.postValue(response.body())
                }

                override fun onFailure(call: Call<JobResponse>, t: Throwable) {
                    jobResponseLiveData.postValue(null)
                    Log.e(TAG,"onFailure:${t.message}")
                }

            }
        )
    }
    fun searchJobResponse(query: String?){
        jobservice.searchJob(query).enqueue(
            object :Callback<JobResponse>{
                override fun onResponse(call: Call<JobResponse>, response: Response<JobResponse>) {
                    searchResponseLiveData.postValue(response.body())
                }

                override fun onFailure(call: Call<JobResponse>, t: Throwable) {
                    searchResponseLiveData.postValue(null)
                }

            }
        )
    }
    fun searchJobResult():LiveData<JobResponse>{
        return searchResponseLiveData
    }
    fun remoteJobResult():LiveData<JobResponse>{
        return jobResponseLiveData
    }
    suspend fun addFavoriteJob(job: FavoriteJob) = db.getFavJobDao().addFavoriteJob(job)
    suspend fun deleteJob(job: FavoriteJob) =db.getFavJobDao().deleteFavJob(job)
    fun getAllFavJobs() = db.getFavJobDao().getAllFavJob()

}