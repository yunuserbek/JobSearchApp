package com.example.jobsearch.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "fav_job")
data class FavoriteJob(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @SerializedName("candidate_required_location")
    val candidateRequiredLocation: String?,
    @SerializedName("category")
    val category: String?,
    @SerializedName("company_logo")
    val companyLogo: String?,
    @SerializedName("company_logo_url")
    val companyLogoUrl: String?,
    @SerializedName("company_name")
    val companyName: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val jobId: Int?,
    @SerializedName("job_type")
    val jobType: String?,
    @SerializedName("publication_date")
    val publicationDate: String?,
    @SerializedName("salary")
    val salary: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("url")
    val url: String?
)