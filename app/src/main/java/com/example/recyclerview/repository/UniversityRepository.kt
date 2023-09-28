package com.example.recyclerview.repository

import com.example.recyclerview.database.dao.UniversityDao
import com.example.recyclerview.extensions.toUniversity
import com.example.recyclerview.extensions.toUniversityEntity
import com.example.recyclerview.models.University
import com.example.recyclerview.network.UniversityApi

class UniversityRepository(
    private val api: UniversityApi,
    private val universityDao: UniversityDao
    ) {

    suspend fun searchUniversities(country: String): List<University> {
        val cachedData = universityDao.getUniversitiesByCountry(country)
        return if (cachedData.isNotEmpty()) {
            // Data found in the local database, return it
            cachedData.map { it.toUniversity() }
        } else {
            // Data not found, fetch from the API
            val freshData = api.searchUniversities(country)

            // Save the fresh data to the local database
            universityDao.insertUniversities(freshData.map { it.toUniversityEntity() })
            freshData
        }
    }
}

