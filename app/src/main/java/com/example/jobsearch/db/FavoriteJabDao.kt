package com.example.jobsearch.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.jobsearch.model.FavoriteJob

@Dao
interface FavoriteJabDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteJob(job: FavoriteJob)

    @Query("SELECT *FROM fav_job ORDER BY id DESC")
    fun getAllFavJob(): LiveData<List<FavoriteJob>>

    @Delete
    suspend fun deleteFavJob(job: FavoriteJob)

}