package com.example.recyclerview.network

import com.example.recyclerview.models.CountryResponse
import retrofit2.http.GET

interface CountryApi {
    @GET("all?fields=name,flags")
    suspend fun getCountries(): List<CountryResponse>
}