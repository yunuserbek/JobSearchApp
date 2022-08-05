package com.example.jobsearch.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.jobsearch.models.Job

@Dao
interface FavoriteJabDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteJob(job: Job)

    @Query("SELECT *FROM fav_job ORDER BY idd DESC")
    fun getAllFavJob(): LiveData<Job>

    @Delete
    suspend fun deleteFavJob(job: Job)

}