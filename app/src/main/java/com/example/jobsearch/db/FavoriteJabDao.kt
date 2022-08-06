package com.example.jobsearch.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.jobsearch.model.Job

@Dao
interface FavoriteJabDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteJob(job: FavoriteJabDao)

    @Query("SELECT *FROM fav_job ORDER BY id DESC")
    fun getAllFavJob(): LiveData<List<FavoriteJabDao>>

    @Delete
    suspend fun deleteFavJob(job: Job)

}