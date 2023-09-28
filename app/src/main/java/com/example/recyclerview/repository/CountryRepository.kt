package com.example.recyclerview.repository

import com.example.recyclerview.models.CountryResponse
import com.example.recyclerview.network.CountryApi

class CountryRepository(private val api: CountryApi) {

    suspend fun getCountries(): List<CountryResponse> {
        return api.getCountries()
    }
}