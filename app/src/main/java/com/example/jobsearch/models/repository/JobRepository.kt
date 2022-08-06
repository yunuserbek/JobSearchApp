package com.example.jobsearch.models.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jobsearch.db.FavoriteJabDao
import com.example.jobsearch.db.FavoriteJobDatabase
import com.example.jobsearch.model.Job
import com.example.jobsearch.models.JobResponse
import com.example.jobsearch.models.RetrofitInstance
import com.example.jobsearch.utils.Constants.TAG
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class JobRepository (private var db :FavoriteJobDatabase){
    private val jobservice = RetrofitInstance.apiService
    private val jobResponseLiveData:MutableLiveData<JobResponse> = MutableLiveData()
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
    fun remoteJobResult():LiveData<JobResponse>{
        return jobResponseLiveData
    }
    suspend fun addFavoriteJob(job: FavoriteJabDao) = db.getFavJobDao().addFavoriteJob(job)
    suspend fun deleteJob(job: FavoriteJabDao) =db.getFavJobDao().deleteFavJob(job)
    fun getAllFavJobs() = db.getFavJobDao()

}