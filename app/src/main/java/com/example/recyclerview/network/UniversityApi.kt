package com.example.recyclerview.network

import com.example.recyclerview.models.University
import retrofit2.http.GET
import retrofit2.http.Query

interface UniversityApi {
    @GET("search")
    suspend fun searchUniversities(@Query("country") country: String): List<University>
}

