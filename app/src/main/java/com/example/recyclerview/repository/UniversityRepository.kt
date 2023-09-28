package com.example.recyclerview.repository

import com.example.recyclerview.models.University
import com.example.recyclerview.network.UniversityApi

class UniversityRepository(private val api: UniversityApi) {

    suspend fun searchUniversities(country: String): List<University> {
        // Make Retrofit API call and return the result
        return api.searchUniversities(country)
    }
}

